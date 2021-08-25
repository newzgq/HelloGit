package com.qidi.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.qidi.crm.bean.Customer;
import com.qidi.crm.dao.CustomerDao;

public class CustomerDaoImple extends BaseDaoImple<Customer> implements CustomerDao{

	public CustomerDaoImple() {
		super();
	}
	
//	@Override
//	public void save(Customer customer) {
//		this.getHibernateTemplate().save(customer);
//	}

//	@Override
//	public Integer findCount(DetachedCriteria detachedCriteria) {
//		detachedCriteria.setProjection(Projections.rowCount());
//		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
//		if (list!=null && list.size() > 0) {
//			return list.get(0).intValue();
//		}
//		return null;
//	}

//	@Override
//	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
//		detachedCriteria.setProjection(null);
//		return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
//	}

//	@Override
//	public Customer findById(Long cust_id) {
//		return this.getHibernateTemplate().get(Customer.class, cust_id);
//	}

//	@Override
//	public void delete(Customer customer) {
//		this.getHibernateTemplate().delete(customer);
//	}

//	@Override
//	public void update(Customer customer) {
//		this.getHibernateTemplate().update(customer);
//	}

//	@Override
//	public List<Customer> findAll() {
//		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
//	}

}
