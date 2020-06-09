package common.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Json extends JSONObject {

	/**
	 * 
	 * @author 李永宁 2018年10月14日下午5:08:45
	 */
	private static final long serialVersionUID = 1L;

	public Json() {
		super();
	}

	public Json(String key, Object value) {
		super();
		super.put(key, value);
	}

	@Override
	public Json put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	@SuppressWarnings("unchecked")
	public static <T> T toJson(Map<String, Object> map, Class<?> clz) {
		return (T) JSON.parseObject(JSON.toJSONString(map), clz);
	}

	public static <T> List<T> toJson(List<Map<String, Object>> list, Class<?> clz) {
		List<T> newList = new ArrayList<>();
		for (Map<String, Object> map : list) {
			if (map != null) {
				newList.add(toJson(map, clz));
			}
		}
		return newList;
	}

	public <T> T toSubclass(Class<T> clz) {
		return JSON.parseObject(this.toJSONString(), clz);
	}
}
