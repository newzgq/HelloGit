package com.qidi.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.qidi.crm.bean.LinkMan;
import com.qidi.crm.bean.PageBean;

public interface LinkManService {

	public void save(LinkMan linkMan);
	
	public PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria,Integer currPage,Integer pageSize);
	
	public LinkMan findById(Long lkm_id);
	
	public void update(LinkMan linkMan);
	
	public void delete(LinkMan linkMan);
}
