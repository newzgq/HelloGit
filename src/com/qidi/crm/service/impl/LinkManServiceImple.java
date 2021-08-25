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

	// ע��Dao
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
		// ���õ�ǰҳ
		pageBean.setCurrPage(currPage);
		// ����ÿҳ�ļ�¼��
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		Double num = Math.ceil(totalCount.doubleValue()/pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ������
		Integer begin = (currPage-1)*pageSize;
		List<LinkMan> linkMans = linkManDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(linkMans);
		return pageBean;
	}

	@Override
	// ҵ������id��ѯ��ϵ�˵ķ���
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
