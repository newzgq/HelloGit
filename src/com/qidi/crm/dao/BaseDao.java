package com.qidi.crm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	public void save(T t);
	public void update(T t);
	public void delete(T t);
	
	// ����ID��ѯ�����ķ���
	public T findById(Serializable id);
	
	// ��ѯ����
	public List<T> findAll();
	
	// ͳ�Ʋ�ѯ
	public Integer findCount(DetachedCriteria detachedCriteria);
	
	// ����������ҳ��ѯ
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);
	
}