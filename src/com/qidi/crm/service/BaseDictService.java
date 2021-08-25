package com.qidi.crm.service;

import java.util.List;

import com.qidi.crm.bean.BaseDict;
import com.qidi.crm.dao.BaseDictDao;

public interface BaseDictService {

	public void setBaseDictDao(BaseDictDao baseDictDao);

	public List<BaseDict> findByTypeCode(String dict_type_code);
}
