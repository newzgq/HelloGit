package com.qidi.crm.dao;

import java.util.List;

import com.qidi.crm.bean.BaseDict;

public interface BaseDictDao extends BaseDao<BaseDict> {

	/**
	 * Dao������������Ʋ�ѯ�ֵ�
	 */
	public List<BaseDict> findByTypeCode(String dict_type_code);
}
