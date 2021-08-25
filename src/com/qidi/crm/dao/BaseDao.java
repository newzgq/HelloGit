package com.qidi.crm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	public void save(T t);
	public void update(T t);
	public void delete(T t);
	
	// 根据ID查询单个的方法
	public T findById(Serializable id);
	
	// 查询所有
	public List<T> findAll();
	
	// 统计查询
	public Integer findCount(DetachedCriteria detachedCriteria);
	
	// 根据条件分页查询
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);
	
}
