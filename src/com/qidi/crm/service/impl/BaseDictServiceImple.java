package com.qidi.crm.service.impl;

import java.util.List;

import com.qidi.crm.bean.BaseDict;
import com.qidi.crm.dao.BaseDictDao;
import com.qidi.crm.service.BaseDictService;

public class BaseDictServiceImple implements BaseDictService{

	// ×¢ÈëDao
	private BaseDictDao baseDictDao;
	
	@Override
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}

}
