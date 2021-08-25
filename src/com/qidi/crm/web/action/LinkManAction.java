package com.qidi.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qidi.crm.bean.Customer;
import com.qidi.crm.bean.LinkMan;
import com.qidi.crm.bean.PageBean;
import com.qidi.crm.service.CustomerService;
import com.qidi.crm.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

	// 模型驱动使用的对象
	private LinkMan linkMan = new LinkMan();
	
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	// 注入Service
	private LinkManService linkManService;
	
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}

	// 新增联系人
	// 注入客户管理的Service
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	/**
	 * 跳转至添加页面
	 */
	public String saveUI() {
		// 查询所有客户
		List<Customer> list = customerService.findAll();
		// 将所有客户存入值栈
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	/**
	 * 联系人管理：保存联系人
	 */
	public String save() {
		linkManService.save(linkMan);
		return "saveSuccess";
	}
	
	private Integer currPage=1;
	private Integer pageSize=1;
	
	public void setCurrPage(Integer currPage) {
		if(currPage==null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize==null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	
	/**
	 * 待条件分页查询联系人
	 */
	public String findAll() {
		// 创建离线条件查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		// 设置条件
		if(linkMan.getLkm_name()!=null) {
			if(!linkMan.getLkm_name().trim().equals("")) {
				// 设置按名称查询的条件
				detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
			}
		}
		if(linkMan.getLkm_gender()!=null) {
			if(!linkMan.getLkm_gender().trim().equals("")) {
				//设置按性别查询的条件
				detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
			}
		}
		
		// 调用业务层
		PageBean<LinkMan> pageBean = linkManService.findByPage(detachedCriteria,currPage,pageSize);
		// 将pageBean压入值栈中
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * 跳转到编辑页面
	 */
	public String edit() {
		// 查询所有客户
		List<Customer> list = customerService.findAll();
		// 根据ID查询某个联系人
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// 将所有客户和联系人带回到页面
		ActionContext.getContext().getValueStack().set("list", list);
		// 将对象的值也存入值栈中，方便自动回显
		ActionContext.getContext().getValueStack().push(linkMan);
		return "editSuccess";
	}
	/**
	 * 修改联系人的方法
	 */
	public String update() {
		// 调用业务层
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	/**
	 * 删除联系人的方法
	 */
	public String delete() {
		// 先查询再删除
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// 删除联系人
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
	
}
