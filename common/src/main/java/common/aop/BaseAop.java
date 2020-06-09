/**
 * @author 李永宁 2018年10月25日上午11:26:08
 */
package common.aop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import common.annotation.Validate;
import common.bean.Result;
import common.controller.BaseController;
import common.controller._Controller;
import common.provider.BaseProvider;
import common.utils.ReflectKit;
import common.validate.BaseValidate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 李永宁 2018年10月25日上午11:26:08
 *
 */
@Slf4j
public abstract class BaseAop {

	protected _Controller targetController = null;
	protected BaseValidate targetValidate = null;

	protected String prefix = null;
	protected String method = null;
	protected Object[] args = null;

	public abstract void cut();

	protected String getMsgPrefix() {
		return "";
	}

	/**
	 * 初始化request respose 后，方法执行前
	 * 
	 * @author 李永宁 2018年11月10日上午3:13:57
	 * 
	 * @param pjp
	 * @return
	 */
	protected Object doAroundBefore(ProceedingJoinPoint pjp) {
		return null;
	}

	protected void initLogParams(ProceedingJoinPoint pjp) {
		this.prefix = getMsgPrefix();
		this.method = pjp.getTarget().getClass().getSimpleName() + "." + pjp.getSignature().getName();
		this.args = pjp.getArgs();
		log.debug("{}>方法:{},参数:{}", prefix, method, args);
	}

	@Around("cut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		this.initRequestAndResponse(pjp);
		Object obj = this.doAroundBefore(pjp);
		if (obj != null) {
			return obj;
		}
		this.initRequestAndResponse(pjp);
		this.initLogParams(pjp);
		// if (this.invalidArguments() == 0) {
		// return new Result(0, "参数验证异常", "");
		// }
		boolean isProceed = this.initValidate(pjp, args);
		// 验证为true,继续下一步
		if (isProceed) {
			return this.txProceed(pjp);
		} else {
			return this.returnNoPassValite();
		}
	}

	private Object txProceed(ProceedingJoinPoint pjp) throws Throwable {
		try {
			Object obj = pjp.proceed();
			if (method.contains("page") || method.contains("list")) {
				log.debug("{}<方法:{},参数:{},结果:{}", prefix, method, args, "集合类型结果不显示");
			} else {
				log.debug("{}<方法:{},参数:{},结果:{}", prefix, method, args, JSON.toJSONString(obj));
			}
			return obj;
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("{}<方法:{},参数:{},出现异常:{}", prefix, method, args, e.getMessage());
			throw e;
		}
	}

	/**
	 * 验证不通过 返回值
	 * 
	 * @author 李永宁 2018年12月27日下午3:06:36
	 *
	 * @return
	 */
	protected Object returnNoPassValite() {
		log.error("{}<方法:{},参数:{},验证不通过", prefix, method, args);
		return new Result(0, targetValidate.getMsg(), "");
	}

	/**
	 * 初始化 request 和 response对象
	 * 
	 * @author 李永宁 2018年10月25日上午11:50:17
	 *
	 * @param JoinPoint
	 */
	protected void initRequestAndResponse(JoinPoint jp) {

		RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
		if (attrs != null) {
			ServletRequestAttributes attributes = (ServletRequestAttributes) attrs;
			Object obj = jp.getTarget();
			Class<?> superClz = obj.getClass().getSuperclass();
			if (superClz == BaseController.class || superClz == BaseProvider.class) {
				targetController = (_Controller) superClz.cast(obj);
				targetController.setRequest(attributes.getRequest());
				targetController.setResponse(attributes.getResponse());
				targetController.setSession(attributes.getRequest().getSession());
			}
		}
	}

	/**
	 * 初始化验证方法参数
	 * 
	 * @author 李永宁 2018年11月6日下午9:39:54
	 * 
	 * @param JoinPoint
	 * @return 没有验证类，则返回true
	 */
	protected boolean initValidate(JoinPoint jp, Object[] args) {
		Class<?> clz = jp.getTarget().getClass();
		Validate validate = clz.getAnnotation(Validate.class);
		if (validate != null) {
			Class<?> valiClz = validate.value();
			String targetMethod = jp.getSignature().getName();
			try {
				targetValidate = (BaseValidate) valiClz.newInstance();
				targetValidate.setController(targetController);
				ReflectKit.callMethod(targetValidate, targetMethod, args);
				// 获取验证后的结果
				return targetValidate.isProceed();
			} catch (InstantiationException | IllegalAccessException e) {
				log.error("BaseAop targetMethod: {} , args: {}", targetMethod, JSONObject.toJSONString(args));
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * 参数中不允许出现 特殊字符
	 * 
	 * @author 李永宁 2020年1月22日 下午4:53:10
	 *
	 * @param value
	 */
	protected int invalidArguments() {
		if (targetController != null && args != null && args.length > 0) {
			for (Object obj : args) {
				String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(obj.toString());
				if (m.find()) {
					log.error("参数有特殊字符。");
					return 0;
				}
			}
		}
		return 1;
	}

}
