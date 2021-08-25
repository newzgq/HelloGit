package com.qidi.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qidi.crm.bean.BaseDict;
import com.qidi.crm.service.BaseDictService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict>{

	// ģ������ʹ�õĶ���
	private BaseDict baseDict = new BaseDict();
	
	@Override
	public BaseDict getModel() {
		return baseDict;
	}
	
	// ע�� Service
	private BaseDictService baseDictService;
	
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	/**
	 * �����������Ʋ�ѯ�ֵ�ķ���
	 */
	public String findByTypeCode() throws IOException{
		System.out.println("�����������Ʋ�ѯ�ֵ�ķ���ִ����");
		// ����ҵ����ѯ
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		
		// ��listת��Ϊ json ----jsonlib(jar�����ܱȽ϶�) fastjson(�����jsonlib��˵jar������)
		/**
		 * JSONConfig:תjson�����ö���
		 * JSONArray:�������Listת��Ϊjson
		 * JSONObject:�������Mapת��Ϊjson
		 */
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
		
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		System.out.println(jsonArray);
		
		// ��json���ݴ�ӡ��ҳ��
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(jsonArray.toString());
		
		return NONE;
	}
	
}
