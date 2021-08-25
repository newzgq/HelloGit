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
		// ׼��PageBean
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		// ���õ�ǰҳ
		pageBean.setCurrPage(currPage);
		// ����ÿҳ�ļ�¼��
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��
		Integer totalCount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		Double num = Math.ceil(totalCount.doubleValue()/pageSize);
		pageBean.setTotalPage(num.intValue());
		
		// ����ÿҳҪ��ʾ�����ݵļ���
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
