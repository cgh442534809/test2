/**
 * @author 李永宁 2018年10月25日下午2:30:57
 */
package common.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import common.bean.Result;
import common.model.BaseModel;
import common.utils.ReflectKit;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 李永宁 2018年10月25日下午2:30:57
 *
 */

@Slf4j
@SuppressWarnings({ "hiding", "unchecked", "unused" })
public abstract class BaseController<T extends BaseModel, P> extends _Controller implements IBaseController {

	public abstract P getProvider();

	/**
	 * 根据前缀分拣参数
	 * 
	 * @author 李永宁 2014年12月15日 上午11:01:56
	 * @param prefix
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getQuery() throws Exception {
		return this.getModel("query", "_", HashMap.class);
	}

	/**
	 * 默认前缀 model 分割 _
	 * 
	 * @author 李永宁 2018年11月23日下午3:45:14
	 *
	 * @return
	 * @throws Exception
	 */
	public BaseModel getModel() throws Exception {
		return this.getModel("model", "_");
	}

	public BaseModel getModel(String prefix, String splitStr) throws Exception {
		Class<?> clz = ReflectKit.getSuperClassGenricType(this.getClass(), 0);
		return (BaseModel) this.getModel(prefix, splitStr, clz);
	}

	/**
	 * 默认前缀 model
	 * 
	 * @author 李永宁 2018年11月19日上午11:56:56
	 *
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	public <T extends BaseModel> T getModel(Class<T> clz) {
		try {
			return this.getModel("model", "_", clz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public <T extends BaseModel> T getModel(String prefix, Class<T> clz) {
		try {
			return this.getModel(prefix, "_", clz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public <X> X getModel(String prefix, String splitStr, Class<X> clz) throws Exception {
		X model = null;
		try {
			model = clz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		if (this.getRequest() == null) {
			return model;
		}
		Enumeration<String> paramNames = this.getRequest().getParameterNames();
		boolean isHash = "HashMap".equals(clz.getSimpleName());
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			String value = this.getRequest().getParameter(name);
			if (name.startsWith(prefix) && !value.isEmpty()) {
				name = name.replaceFirst(splitStr, "@");
				String[] nameArr = name.split("@");
				if (nameArr.length > 0 && nameArr[0].equals(prefix)) {
					String key = nameArr[1].trim();
					if (null != value && !value.trim().equals("")) {
						if (isHash) {
							((Map<String, Object>) model).put(key, value.trim());
						} else {
							ReflectKit.setValue(model, key, value.trim());
						}
					}
				}
			}
		}
		return model;
	}

	/**
	 * query_condition_column
	 * 
	 * @param prefix
	 * @param splitStr
	 * @return
	 * @throws Exception
	 */
	public Wrapper<T> getQueryWrapper(String prefix, String splitStr) throws Exception {
		QueryWrapper<T> wrapper = new QueryWrapper<T>();
		if (this.getRequest() == null) {
			return wrapper;
		}
		Enumeration<String> paramNames = this.getRequest().getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			String value = this.getRequest().getParameter(name);
			if (name.startsWith(prefix) && !value.isEmpty()) {
				name = name.replaceFirst(splitStr, "@");
				String[] nameArr = name.split("@");
				// 匹配前缀
				if (nameArr.length > 0 && nameArr[0].equals(prefix)) {
					String conditionColumn = nameArr[1].replaceFirst(splitStr, "@");
					String[] arr = conditionColumn.split("@");
					String condition = arr[0].trim();
					String column = arr[1].trim();
					if (!value.trim().equals("")) {
						ReflectKit.callMethod(wrapper, condition, column, value);
					}
				}
			}
		}
		return wrapper;
	}

	// =============================================================================

	@Override
	public Result listByMap() {
		Map<String, Object> columnMap = null;
		try {
			columnMap = this.getQuery();
		} catch (Exception e) {
			return new Result(0, "参数异常");
		}
		P p = this.getProvider();
		List<T> list = (List<T>) ReflectKit.callMethod(p, "listByMap", columnMap);
		return new Result(list);
	}

	@Override
	public Result list() {
		QueryWrapper<T> wrapper = null;
		try {
			wrapper = (QueryWrapper<T>) this.getQueryWrapper("query", "_");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(0, "参数异常");
		}
		P p = this.getProvider();
		List<T> list = (List<T>) ReflectKit.callMethod(p, "list", wrapper);
		return new Result(list);
	}

	@Override
	public Result page(Integer current, Integer size) {
		QueryWrapper<T> wrapper = null;
		try {
			wrapper = (QueryWrapper<T>) this.getQueryWrapper("query", "_");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(0, "参数异常");
		}
		IPage<T> rtPage = (IPage<T>) ReflectKit.callMethod(this.getProvider(), "page", current, size, wrapper);
		return new Result(rtPage);
	}

	@Override
	public Result pageMaps(Integer current, Integer size) {
		Map<String, Object> query = null;
		try {
			query = this.getQuery();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(0, "参数异常");
		}
		Wrapper<T> wrapper = new QueryWrapper<T>().allEq(query);
		IPage<Map<String, Object>> rtPage = (IPage<Map<String, Object>>) ReflectKit.callMethod(this.getProvider(),
				"pageMaps", current, size, wrapper);
		return new Result(rtPage);
	}

	@Override
	public Result save() {
		T model = null;
		try {
			model = (T) this.getModel();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(0, "参数异常");
		}
		boolean f = (boolean) ReflectKit.callMethod(this.getProvider(), "save", model);
		return f ? new Result(model) : new Result(0, "保存失败。", "");
	}

	@Override
	public Result delete(String id) {
		boolean f = (boolean) ReflectKit.callMethod(this.getProvider(), "removeById", id);
		return f ? new Result(id) : new Result(0, "删除失败。", "");
	}

	@Override
	public Result update(String id) {
		T model = null;
		try {
			model = (T) this.getModel();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(0, "参数异常");
		}
		boolean f = (boolean) ReflectKit.callMethod(this.getProvider(), "updateById", id, model);
		return f ? new Result(model) : new Result(0, "修改失败。", "");
	}

	@Override
	public Result get(String id) {
		T model = (T) ReflectKit.callMethod(this.getProvider(), "getById", id);
		return new Result(model);
	}

}
