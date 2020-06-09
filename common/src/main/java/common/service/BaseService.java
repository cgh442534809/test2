/**
 * @author 李永宁 2018年10月18日下午4:31:30
 */
package common.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import common.dao.BaseDao;
import common.model.BaseModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 李永宁 2018年10月18日下午4:31:30
 *
 */
@Slf4j
@SuppressWarnings("unused")
public abstract class BaseService<T extends BaseModel> extends ServiceImpl<BaseMapper<T>, T>
		implements IBaseService<T> {

	public abstract BaseDao<T> getDao();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.baomidou.mybatisplus.extension.service.impl.ServiceImpl#getBaseMapper()
	 */
	@Override
	public BaseMapper<T> getBaseMapper() {
		// TODO Auto-generated method stub
		return this.getDao();
	}

	@Override
	public T first(QueryWrapper<T> wrapper) {
		Page<T> page = this.page(new Page<>(1, 1), wrapper);
		List<T> list = page.getRecords();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<T> listByIds(Collection<? extends Serializable> idList) {
		if (null != idList && idList.size() > 0) {
			return super.listByIds(idList);
		}
		return new ArrayList();
	}

}
