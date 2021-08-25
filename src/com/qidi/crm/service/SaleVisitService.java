package com.qidi.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.qidi.crm.bean.PageBean;
import com.qidi.crm.bean.SaleVisit;

public interface SaleVisitService {
	
	public PageBean findByPage(DetachedCriteria detachedCriteria,Integer currPage, Integer pageSize);
	
	public void save(SaleVisit saleVisit);
	
	public void delete(SaleVisit saleVisit);
	
	public SaleVisit findById(String visit_id);
	
	public void update(SaleVisit saleVisit);
}
