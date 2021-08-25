package com.qidi.crm.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qidi.crm.bean.PageBean;
import com.qidi.crm.bean.SaleVisit;
import com.qidi.crm.service.SaleVisitService;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	
	// 模型驱动使用的对象
	private SaleVisit saleVisit = new SaleVisit();
	
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;

	/**
	 *  接收分页的参数
	 */
	private Integer currPage = 1;
	private Integer pageSize = 3;
	public void setCurrPage(Integer currPage) {
		if(currPage==null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	
	
	// 接收数据
	private Date visit_end_time;
	public Date getVisit_end_time() {
		return visit_end_time;
	}
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}
	/**
	 *  带条件的分页查询的方法
	 */
	public String findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		// 设置条件
		// 按业务员名称查询的条件
		if(saleVisit.getVisit_time()!=null) {
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if(visit_end_time!=null) {
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		
		// 调用业务层
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
		// 将集合压入值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * 跳转到添加页面的方法
	 */
	public String saveUI() {
		return "saveUI";
	}
	
	/**
	 * 客户拜访记录管理：添加拜访记录的方法
	 */
	public String save() {
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
	
	/**
	 * 客户拜访记录管理：删除拜访记录的方法
	 */
	public String delete() {
		// 先查询再删除
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		// 删除拜访记录
		saleVisitService.delete(saleVisit);
		return "deleteSuccess";
	}
	
	/**
	 *  跳转到修改页面的方法
	 */
	public String edit() {
		// 根据id查询，跳转页面，回显数据
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		// 将saleVisit传递到页面
		return "editSuccess";
	}
	
	/**
	 * 客户拜访记录管理：修改拜访记录的方法
	 */
	public String update() {
		saleVisitService.update(saleVisit);
		return "updateSuccess";
	}
	
}
