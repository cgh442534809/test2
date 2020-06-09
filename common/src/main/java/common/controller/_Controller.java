package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("unused")
@RestController
@Data
public abstract class _Controller {

	private HttpServletRequest request;

	private HttpServletResponse response;

	private HttpSession session;

	private JSONObject user;

	public String getPara(String name) {
		return this.request.getParameter(name);
	}

	public String getPara(Enum<?> prop) {
		return this.request.getParameter(prop.toString());
	}

	/**
	 * requet setAttribute
	 * 
	 * @author 李永宁 2018年11月11日下午1:05:15
	 * 
	 * @param key
	 * @param value
	 */
	public void setAttr(String key, Object value) {
		this.request.setAttribute(key, value);
	}

	/**
	 * requet getAttribute
	 * 
	 * @author 李永宁 2018年11月11日下午1:05:26
	 * 
	 * @param key
	 * @return
	 */
	public Object getAttr(String key) {
		return this.request.getAttribute(key);
	}

	/**
	 * session setAttribute
	 * 
	 * @author 李永宁 2018年11月11日下午1:05:43
	 * 
	 * @param key
	 * @param value
	 */
	public void setSAttr(String key, Object value) {
		this.session.setAttribute(key, value);
	}

	/**
	 * session getAttribute
	 * 
	 * @author 李永宁 2018年11月11日下午1:05:59
	 * 
	 * @param key
	 * @return
	 */
	public Object getSAttr(String key) {
		return this.session.getAttribute(key);
	}

}
