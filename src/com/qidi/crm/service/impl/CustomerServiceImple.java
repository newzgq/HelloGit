package com.qidi.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.qidi.crm.bean.Customer;
import com.qidi.crm.bean.PageBean;
import com.qidi.crm.dao.CustomerDao;
import com.qidi.crm.service.CustomerService;

/**
 * �ͻ������Service��
 * @author
 */
@Transactional
public class CustomerServiceImple implements CustomerService{

	private CustomerDao customerDao;
	@Override
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		// ��װ��ǰҳ��
		pageBean.setCurrPage(currPage);
		// ��װÿҳ��ʾ��¼��
		pageBean.setPageSize(pageSize);
		// ��װ�ܼ�¼��
		// ����Dao
		Integer totalCount = customerDao.findCount(detachedCriteria); 
		pageBean.setTotalCount(totalCount);
		// ��װ��ҳ��		
		Double num = Math.ceil(totalCount.doubleValue()/pageSize);
		pageBean.setTotalPage(num.intValue());
		// ��װÿҳ��ʾ���ݵļ���
		Integer begin=(currPage-1)*pageSize;
		List<Customer> list=customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
