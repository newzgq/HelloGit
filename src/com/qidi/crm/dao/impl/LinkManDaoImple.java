package com.qidi.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.qidi.crm.bean.LinkMan;
import com.qidi.crm.dao.LinkManDao;

public class LinkManDaoImple extends BaseDaoImple<LinkMan> implements LinkManDao {

	public LinkManDaoImple() {
		super();
	}
	
//	@Override
//	public void save(LinkMan linkMan) {
//		this.getHibernateTemplate().save(linkMan);
//	}

//	@Override
//	public Integer findCount(DetachedCriteria detachedCriteria) {
//		detachedCriteria.setProjection(Projections.rowCount());
//		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
//		if(list!=null && list.size()>0) {
//			return list.get(0).intValue();
//		}
//		return null;
//	}

//	@Override
//	public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
//		detachedCriteria.setProjection(null);
//		return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
//	}

//	@Override
//	// Dao层中根据id查询联系人的方法
//	public LinkMan findById(Long lkm_id) {
//		return this.getHibernateTemplate().get(LinkMan.class, lkm_id);
//	}

//	@Override
//	public void update(LinkMan linkMan) {
//		this.getHibernateTemplate().update(linkMan);
//	}

//	@Override
//	public void delete(LinkMan linkMan) {
//		this.getHibernateTemplate().delete(linkMan);
//	}
	
}
