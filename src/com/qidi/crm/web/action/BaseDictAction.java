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

	// 模型驱动使用的对象
	private BaseDict baseDict = new BaseDict();
	
	@Override
	public BaseDict getModel() {
		return baseDict;
	}
	
	// 注入 Service
	private BaseDictService baseDictService;
	
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	/**
	 * 根据类型名称查询字典的方法
	 */
	public String findByTypeCode() throws IOException{
		System.out.println("根据类型名称查询字典的方法执行了");
		// 调用业务层查询
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		
		// 将list转换为 json ----jsonlib(jar包可能比较多) fastjson(相对于jsonlib来说jar包较少)
		/**
		 * JSONConfig:转json的配置对象
		 * JSONArray:将数组和List转换为json
		 * JSONObject:将对象和Map转换为json
		 */
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
		
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		System.out.println(jsonArray);
		
		// 将json数据打印到页面
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(jsonArray.toString());
		
		return NONE;
	}
	
}
