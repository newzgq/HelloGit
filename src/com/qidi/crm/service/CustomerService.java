package com.qidi.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.qidi.crm.bean.Customer;
import com.qidi.crm.bean.PageBean;
import com.qidi.crm.dao.CustomerDao;

public interface CustomerService {
	
	public void setCustomerDao(CustomerDao customerDao);
	
	public void save(Customer customer);
	
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);
	
	public Customer findById(Long cust_id);
	
	public void delete(Customer customer);
	
	public void update(Customer customer);
	
	public List<Customer> findAll();
	
}
