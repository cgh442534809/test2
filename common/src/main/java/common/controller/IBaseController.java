/**
 * @author 李永宁 2018年10月25日下午2:30:57
 */
package common.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import common.bean.Result;

/**
 * @author 李永宁 2018年10月25日下午2:30:57
 *
 */
public interface IBaseController {

	@PostMapping(value = "/_page/{current}/{size}")
	Result page(@PathVariable Integer current, @PathVariable Integer size);

	@PostMapping(value = "/_pageMaps/{current}/{size}")
	Result pageMaps(@PathVariable Integer current, @PathVariable Integer size);

	@PostMapping(value = "/_listByMap")
	Result listByMap();

	@PostMapping(value = "/_list")
	Result list();

	@PostMapping(value = "/_save")
	Result save();

	@PostMapping(value = "/_delete/{id}")
	Result delete(@PathVariable String id);

	@PostMapping(value = "/_update/{id}")
	Result update(@PathVariable String id);

	@PostMapping(value = "/_get/{id}")
	Result get(@PathVariable String id);
}
