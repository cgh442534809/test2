/**
 * 
 */
package common.bean;

import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import common.utils.ReflectKit;

/**
 * @author 李永宁 2020年1月13日 下午4:14:41
 *
 */
public class Where<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<Object[]> where = new ArrayList<Object[]>();

	public void put(String key, String condition, Object value) {
		assertNotNull("key is null", key);
		assertNotNull("condition is null", condition);
		Object[] objs = new Object[] { condition, key, value };
		where.add(objs);
	}

	public QueryWrapper<T> toWrapper() {
		QueryWrapper<T> wrapper = new QueryWrapper<T>();
		for (Object[] objs : where) {
			ReflectKit.callMethod(wrapper, objs[0].toString(), objs[1], objs[2]);
		}
		return wrapper;
	}

}
