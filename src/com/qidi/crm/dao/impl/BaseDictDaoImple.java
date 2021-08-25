package com.qidi.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.qidi.crm.bean.BaseDict;
import com.qidi.crm.dao.BaseDictDao;

public class BaseDictDaoImple extends BaseDaoImple<BaseDict> implements BaseDictDao {

	public BaseDictDaoImple() {
		super();
	}
	
	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code = ?", dict_type_code);
	}

}
