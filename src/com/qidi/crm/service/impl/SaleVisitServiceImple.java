package com.qidi.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.qidi.crm.bean.PageBean;
import com.qidi.crm.bean.SaleVisit;
import com.qidi.crm.dao.SaleVisitDao;
import com.qidi.crm.service.SaleVisitService;

@Transactional
public class SaleVisitServiceImple implements SaleVisitService {

	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	public PageBean findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		// 准备PageBean
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		// 设置当前页
		pageBean.setCurrPage(currPage);
		// 设置每页的记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer totalCount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		Double num = Math.ceil(totalCount.doubleValue()/pageSize);
		pageBean.setTotalPage(num.intValue());
		
		// 设置每页要显示的数据的集合
		Integer begin = (currPage-1)*pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	@Override
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
	}

	@Override
	public SaleVisit findById(String visit_id) {
		return saleVisitDao.findById(visit_id);
	}

	@Override
	public void update(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
	}
	
	
}
