/**
 * @author 李永宁 2018年10月23日下午4:21:00
 */
package common.provider;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import common.controller._Controller;
import common.model.BaseModel;
import common.service.IBaseService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 李永宁 2018年10月23日下午4:21:00
 *
 */
@Slf4j
@SuppressWarnings("unused")
public abstract class BaseProvider<T extends BaseModel> extends _Controller implements IBaseProvider<T> {

	public abstract IBaseService<T> getService();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tean.common.provider.BaseProviderInf#save(com.tean.common.bean.Json)
	 */
	@Override
	public boolean save(@RequestBody T model) {
		return this.getService().save(model);
	}

	@Override
	public boolean saveBatch(@RequestBody Collection<T> entityList) {
		return this.getService().saveBatch(entityList);
	}

	@Override
	public boolean saveBatch(int batchSize, @RequestBody Collection<T> entityList) {
		// TODO Auto-generated method stub
		return this.getService().saveBatch(entityList, batchSize);
	}

	@Override
	public boolean saveOrUpdateBatch(@RequestBody Collection<T> entityList) {
		// TODO Auto-generated method stub
		return this.getService().saveOrUpdateBatch(entityList);
	}

	@Override
	public boolean saveOrUpdateBatch(int batchSize, @RequestBody Collection<T> entityList) {
		// TODO Auto-generated method stub
		return this.getService().saveOrUpdateBatch(entityList, batchSize);
	}

	@Override
	public boolean removeById(Serializable id) {
		// TODO Auto-generated method stub
		return this.getService().removeById(id);
	}

	@Override
	public boolean removeByMap(@RequestBody Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return this.getService().removeByMap(columnMap);
	}

	@Override
	public boolean remove(@RequestBody QueryWrapper<T> queryWrapper) {
		// TODO Auto-generated method stub
		return this.getService().remove(queryWrapper);
	}

	@Override
	public boolean removeByIds(@RequestBody Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return this.getService().removeByIds(idList);
	}

	@Override
	public boolean updateById(@RequestBody T entity) {
		// TODO Auto-generated method stub
		return this.getService().updateById(entity);
	}

	@Override
	public boolean updateBatchById(int batchSize, @RequestBody Collection<T> entityList) {
		// TODO Auto-generated method stub
		return this.getService().updateBatchById(entityList, batchSize);
	}

	@Override
	public boolean saveOrUpdate(@RequestBody T entity) {
		// TODO Auto-generated method stub
		return this.getService().saveOrUpdate(entity);
	}

	@Override
	public T getById(Serializable id) {
		// TODO Auto-generated method stub
		return this.getService().getById(id);
	}

	@Override
	public List<T> listByIds(@RequestBody Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return this.getService().listByIds(idList);
	}

	@Override
	public List<T> listByMap(@RequestBody Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return this.getService().listByMap(columnMap);
	}

	@Override
	public T getOne(boolean throwEx, @RequestBody QueryWrapper<T> queryWrapper) {
		// TODO Auto-generated method stub
		return this.getService().getOne(queryWrapper, throwEx);
	}

	@Override
	public Map<String, Object> getMap(@RequestBody QueryWrapper<T> queryWrapper) {
		// TODO Auto-generated method stub
		return this.getService().getMap(queryWrapper);
	}

	@Override
	public Object getObj(@RequestBody QueryWrapper<T> queryWrapper) {
		// TODO Auto-generated method stub
		return this.getService().getOne(queryWrapper);
	}

	@Override
	public int count(@RequestBody QueryWrapper<T> queryWrapper) {
		// TODO Auto-generated method stub
		return this.getService().count(queryWrapper);
	}

	@Override
	public List<T> list(@RequestBody QueryWrapper<T> queryWrapper) {
		// TODO Auto-generated method stub
		return this.getService().list(queryWrapper);
	}

	@Override
	public List<Map<String, Object>> listMaps(@RequestBody QueryWrapper<T> queryWrapper) {
		// TODO Auto-generated method stub
		return this.getService().listMaps(queryWrapper);
	}

	@Override
	public List<Object> listObjs(@RequestBody QueryWrapper<T> queryWrapper) {
		// TODO Auto-generated method stub
		return this.getService().listObjs(queryWrapper);
	}

	@Override
	public Page<T> page(Integer current, Integer size, @RequestBody QueryWrapper<T> queryWrapper) {
		// TODO Auto-generated method stub
		Page<T> page = new Page<>(current, size);
		return this.getService().page(page, queryWrapper);
	}

}
