package com.qidi.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.qidi.crm.bean.LinkMan;
import com.qidi.crm.bean.PageBean;
import com.qidi.crm.dao.LinkManDao;
import com.qidi.crm.service.LinkManService;

@Transactional
public class LinkManServiceImple implements LinkManService{

	// 注入Dao
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {

		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		// 设置当前页
		pageBean.setCurrPage(currPage);
		// 设置每页的记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		Double num = Math.ceil(totalCount.doubleValue()/pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示的数据
		Integer begin = (currPage-1)*pageSize;
		List<LinkMan> linkMans = linkManDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(linkMans);
		return pageBean;
	}

	@Override
	// 业务层根据id查询联系人的方法
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}
	
}
