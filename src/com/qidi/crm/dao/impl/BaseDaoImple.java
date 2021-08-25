package com.qidi.crm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.qidi.crm.dao.BaseDao;

public class BaseDaoImple<T> extends HibernateDaoSupport implements BaseDao<T>{

	private Class clazz;
	
	public BaseDaoImple() {
		// 反射：第一步，需要获取到Class
		Class clazz = this.getClass();  // 正在被调用的那个类的class（子类）
		
		// 查看JKD的API
		Type type = clazz.getGenericSuperclass();  // 获取参数化类型
		System.out.println(type);
		
		// 得到的 这个type就是一个参数化类型，将type强转为参数化类型
		ParameterizedType pType = (ParameterizedType)type;
		
		// 通过参数化类型获得实际类型参数：得到一个实力类型参数的数组
		Type[] types = pType.getActualTypeArguments();
		
		// 只获得第一个实际类型参数即可
		this.clazz = (Class) types[0];
	}
	
	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list!=null && list.size()>0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
	}
	

}
