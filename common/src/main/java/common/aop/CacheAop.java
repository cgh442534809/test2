/**
 * 
 */
package common.aop;

import java.util.regex.Pattern;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import common.utils.MD5Kit;
import common.utils.RedisKit;
import common.utils.ReflectKit;
import lombok.extern.slf4j.Slf4j;

/**
 * 缓存切面
 * 
 * @author 李永宁 2018年11月8日下午5:44:41
 *
 */
@Slf4j
public abstract class CacheAop {

	@Autowired
	protected RedisKit redisKit;

	public abstract void cut();

	/**
	 * 缓存时长，默认60秒
	 * 
	 * @author 李永宁 2019年1月15日下午5:15:44
	 *
	 * @return
	 */
	public long seconds() {
		return 60;
	}

	@Around("cut()")
	public Object doAround(ProceedingJoinPoint pjp) {
		Signature signature = pjp.getSignature();
		String methodName = signature.getName();
		String typeName = ReflectKit.getSuperClassGenricType(pjp.getTarget().getClass(), 0).getName();
		if (this.isMatch(methodName)) {
			return this.clearCacheMethod(typeName, pjp);
		} else {
			return this.cacheMethod(typeName, methodName, pjp);
		}
	}

	/**
	 * 判断是否是增删改方法
	 * 
	 * @author 李永宁 2018年11月8日下午8:07:04
	 * 
	 * @param methodName
	 * @return
	 */
	private boolean isMatch(String methodName) {
		return Pattern.matches("^(update.*)|(save.*)|(insert.*)|(delete.*)|(remove.*)", methodName);
	}

	/**
	 * 清除缓存信息
	 * 
	 * @author 李永宁 2018年11月8日下午8:37:01
	 * 
	 * @param typeName
	 * @return
	 */
	protected Object clearCacheMethod(String typeName, ProceedingJoinPoint pjp) {
		String pattern = String.format("%s:*", typeName);
		Object obj = null;
		try {
			obj = pjp.proceed();
			this.redisKit.delRegxByScan(pattern);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 缓存数据
	 * 
	 * @author 李永宁 2018年11月8日下午8:46:15
	 * 
	 * @param typeName
	 * @return
	 */
	protected Object cacheMethod(String typeName, String methodName, ProceedingJoinPoint pjp) {
		String paramsMD5 = MD5Kit.encode(JSON.toJSONString(pjp.getArgs()));
		String key = String.format("%s:%s:%s", typeName, methodName, paramsMD5);
		Object obj = this.redisKit.get(key);
		if (obj == null) {
			try {
				obj = pjp.proceed();
				if (obj != null) {
					this.redisKit.set(key, obj, this.seconds());
					log.debug("##>设置缓存 键:{} ", key);
				}
				return obj;
			} catch (Throwable e) {
				e.printStackTrace();
				return null;
			}
		} else {
			log.debug("<##从缓存中取值 键:{} ", key);
			return obj;
		}
	}

}
