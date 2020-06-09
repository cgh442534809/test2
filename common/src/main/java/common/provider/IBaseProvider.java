/**
 * @author 李永宁 2018年10月23日下午4:20:00
 */
package common.provider;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author 李永宁 2018年10月23日下午4:20:00
 *
 */
public interface IBaseProvider<T> {

	/**
	 * <p>
	 * 插入一条记录（选择字段，策略插入）
	 * </p>
	 *
	 * @param model
	 *            实体对象
	 */
	// @PostMapping(value = "/save")
	boolean save(@RequestBody T model);

	/**
	 * 插入（批量）
	 *
	 * @param entityList
	 *            实体对象集合
	 * @param batchSize
	 *            插入批次数量
	 */
	// @PostMapping(value = "/saveBatch")
	boolean saveBatch(@RequestBody Collection<T> entityList);

	/**
	 * 插入（批量）
	 * 
	 * @param entityList
	 *            实体对象集合
	 * @param batchSize
	 *            插入批次数量
	 */
	// @PostMapping(value = "/saveBatch/{batchSize}")
	boolean saveBatch(@PathVariable(value = "batchSize") int batchSize, @RequestBody Collection<T> entityList);

	/**
	 * <p>
	 * 批量修改插入
	 * </p>
	 *
	 * @param entityList
	 *            实体对象集合
	 */
	// @PostMapping(value = "/saveOrUpdateBatch")
	boolean saveOrUpdateBatch(@RequestBody Collection<T> entityList);

	/**
	 * <p>
	 * 批量修改插入
	 * </p>
	 *
	 * @param entityList
	 *            实体对象集合
	 * @param batchSize
	 *            每次的数量
	 */
	// @PostMapping(value = "/saveOrUpdateBatch/{batchSize}")
	boolean saveOrUpdateBatch(@PathVariable(value = "batchSize") int batchSize, @RequestBody Collection<T> entityList);

	/**
	 * <p>
	 * 根据 ID 删除
	 * </p>
	 *
	 * @param id
	 *            主键ID
	 */
	// @GetMapping(value = "/removeById/{id}")
	boolean removeById(@PathVariable(value = "id") Serializable id);

	/**
	 * <p>
	 * 根据 columnMap 条件，删除记录
	 * </p>
	 *
	 * @param columnMap
	 *            表字段 map 对象
	 */
	// @PostMapping(value = "/removeByMap")
	boolean removeByMap(@RequestBody Map<String, Object> columnMap);

	/**
	 * <p>
	 * 根据 entity 条件，删除记录
	 * </p>
	 *
	 * @param queryWrapper
	 *            实体包装类
	 *            {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
	 */
	// @PostMapping(value = "/remove")
	boolean remove(@RequestBody QueryWrapper<T> queryWrapper);

	/**
	 * <p>
	 * 删除（根据ID 批量删除）
	 * </p>
	 *
	 * @param idList
	 *            主键ID列表
	 */
	// @PostMapping(value = "/removeByIds")
	boolean removeByIds(@RequestBody Collection<? extends Serializable> idList);

	/**
	 * <p>
	 * 根据 ID 选择修改
	 * </p>
	 *
	 * @param entity
	 *            实体对象
	 */
	// @PostMapping(value = "/updateById")
	boolean updateById(@RequestBody T entity);

	/**
	 * <p>
	 * 根据ID 批量更新
	 * </p>
	 *
	 * @param entityList
	 *            实体对象集合
	 * @param batchSize
	 *            更新批次数量
	 */
	// @PostMapping(value = "/updateBatchById/{batchSize}")
	boolean updateBatchById(@PathVariable(value = "batchSize") int batchSize, @RequestBody Collection<T> entityList);

	/**
	 * <p>
	 * TableId 注解存在更新记录，否插入一条记录
	 * </p>
	 *
	 * @param entity
	 *            实体对象
	 */
	// @PostMapping(value = "/saveOrUpdate")
	boolean saveOrUpdate(@RequestBody T entity);

	/**
	 * <p>
	 * 根据 ID 查询
	 * </p>
	 *
	 * @param id
	 *            主键ID
	 */
	// @GetMapping(value = "/getById/{id}")
	T getById(@PathVariable(value = "id") Serializable id);

	/**
	 * <p>
	 * 查询（根据ID 批量查询）
	 * </p>
	 *
	 * @param idList
	 *            主键ID列表
	 */
	// @PostMapping(value = "/listByIds")
	List<T> listByIds(@RequestBody Collection<? extends Serializable> idList);

	/**
	 * <p>
	 * 查询（根据 columnMap 条件）
	 * </p>
	 *
	 * @param columnMap
	 *            表字段 map 对象
	 */
	// @PostMapping(value = "/listByMap")
	List<T> listByMap(@RequestBody Map<String, Object> columnMap);

	/**
	 * <p>
	 * 根据 Wrapper，查询一条记录
	 * </p>
	 *
	 * @param queryWrapper
	 *            实体对象封装操作类
	 *            {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
	 * @param throwEx
	 *            有多个 result 是否抛出异常
	 */
	// @PostMapping(value = "/getOne")
	T getOne(@RequestParam("throwEx") boolean throwEx, @RequestBody QueryWrapper<T> queryWrapper);

	/**
	 * <p>
	 * 根据 Wrapper，查询一条记录
	 * </p>
	 *
	 * @param queryWrapper
	 *            实体对象封装操作类
	 *            {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
	 */
	// @PostMapping(value = "/getMap")
	Map<String, Object> getMap(@RequestBody QueryWrapper<T> queryWrapper);

	/**
	 * <p>
	 * 根据 Wrapper，查询一条记录
	 * </p>
	 *
	 * @param queryWrapper
	 *            实体对象封装操作类
	 *            {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
	 */
	// @PostMapping(value = "/getObj")
	Object getObj(@RequestBody QueryWrapper<T> queryWrapper);

	/**
	 * <p>
	 * 根据 Wrapper 条件，查询总记录数
	 * </p>
	 *
	 * @param queryWrapper
	 *            实体对象封装操作类
	 *            {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
	 */
	// @PostMapping(value = "/count")
	int count(@RequestBody QueryWrapper<T> queryWrapper);

	/**
	 * <p>
	 * 查询列表
	 * </p>
	 *
	 * @param queryWrapper
	 *            实体对象封装操作类
	 *            {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
	 */
	// @PostMapping(value = "/list")
	List<T> list(@RequestBody QueryWrapper<T> queryWrapper);

	/**
	 * <p>
	 * 查询列表
	 * </p>
	 *
	 * @param queryWrapper
	 *            实体对象封装操作类
	 *            {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
	 */
	// @PostMapping(value = "/listMaps")
	List<Map<String, Object>> listMaps(@RequestBody QueryWrapper<T> queryWrapper);

	/**
	 * <p>
	 * 根据 Wrapper 条件，查询全部记录
	 * </p>
	 *
	 * @param queryWrapper
	 *            实体对象封装操作类
	 *            {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
	 */
	// @PostMapping(value = "/listObjs")
	List<Object> listObjs(@RequestBody QueryWrapper<T> queryWrapper);

	/**
	 * <p>
	 * 翻页查询
	 * </p>
	 *
	 * @param page
	 *            翻页对象
	 * @param queryWrapper
	 *            实体对象封装操作类
	 *            {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
	 */
	// @PostMapping(value = "/page/{current}/{size}")
	Page<T> page(@PathVariable(value = "current") Integer current, @PathVariable(value = "size") Integer size,
			@RequestBody QueryWrapper<T> queryWrapper);

}
