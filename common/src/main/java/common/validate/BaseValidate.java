/**
 * @author 李永宁 2018年10月25日下午2:45:14
 */
package common.validate;

import java.util.regex.Pattern;

import common.controller._Controller;
import common.utils.StrKit;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 李永宁 2018年10月25日下午2:45:14
 *
 */
@Slf4j
@SuppressWarnings("unused")
public class BaseValidate {

	protected _Controller controller;

	// 是否需要继续验证
	private boolean isProceed = true;
	private String msg = "";

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		if (!this.isProceed) {
			this.msg = msg;
		}
	}

	public boolean isProceed() {
		return isProceed;
	}

	public BaseValidate setProceed(boolean isProceed) {
		this.isProceed = isProceed;
		return this;
	}

	public _Controller getController() {
		return controller;
	}

	public void setController(_Controller controller) {
		this.controller = controller;
	}

	public String require(String name, String msg) {
		String value = controller.getPara(name);
		if (isProceed) {
			this.isProceed = StrKit.notBlank(value);
			this.setMsg(msg);
		}
		return value;
	}

	public String requireId() {
		String value = controller.getPara("id");
		if (isProceed) {
			this.isProceed = StrKit.notBlank(value);
			this.setMsg("id 不能为空。");
		}
		return value;
	}

	/**
	 * 判断是否是整数
	 * 
	 * @author 李永宁 2018年10月25日下午4:53:46
	 *
	 * @param name
	 */
	public String isInt(String name, String msg) {
		String value = controller.getPara(name);
		if (isProceed) {
			this.isProceed = Pattern.matches("^\\d+$", value);
			this.setMsg(msg);
		}
		return value;
	}

	/**
	 * 判断是否是数字
	 * 
	 * @author 李永宁 2018年10月25日下午4:53:35
	 *
	 * @param name
	 */
	public String isNumber(String name, String msg) {
		String value = controller.getPara(name);
		if (isProceed) {
			this.isProceed = Pattern.matches("^(-?\\d+)(\\.\\d+)?$", value);
			this.setMsg(msg);
		}
		return value;
	}

	/**
	 * 判断长度
	 * 
	 * @author 李永宁 2018年10月25日下午4:55:13
	 *
	 * @param geMin
	 * @param ltMax
	 * @param name
	 * @return
	 */
	public String len(int geMin, int ltMax, String name, String msg) {
		String value = this.require(name, msg);
		if (isProceed) {
			int len = value.length();
			this.isProceed = len >= geMin && len < ltMax;
			this.setMsg(msg);
		}
		return value;
	}

	/**
	 * 判断范围值
	 * 
	 * @author 李永宁 2018年10月25日下午4:55:51
	 *
	 * @param geMin
	 * @param leMax
	 * @param name
	 * @return
	 */
	public String range(int geMin, int leMax, String name, String msg) {
		String value = this.isNumber(name, msg);
		if (isProceed) {
			double d = Double.parseDouble(value);
			// 大于等于最小值 小于等于最大值
			this.isProceed = d >= geMin && d <= leMax;
			this.setMsg(msg);
		}
		return value;
	}

	/**
	 * 正则匹配
	 * 
	 * @author 李永宁 2018年10月25日下午5:08:20
	 *
	 * @param name
	 * @param pattern
	 * @return
	 */
	public String regx(String name, String pattern, String msg) {
		String value = controller.getPara(name);
		if (isProceed) {
			this.isProceed = Pattern.matches(pattern, value);
			this.setMsg(msg);
		}
		return value;
	}

}
