/**
 * @author 李永宁 2018年10月18日下午4:21:46
 */
package common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 李永宁 2018年10月18日下午4:21:46
 *
 */
public interface IBaseService<T> extends IService<T> {
	public T first(QueryWrapper<T> wrapper);
}
