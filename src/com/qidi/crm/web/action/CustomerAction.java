package com.qidi.crm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qidi.crm.bean.Customer;
import com.qidi.crm.bean.PageBean;
import com.qidi.crm.service.CustomerService;
import com.qidi.crm.util.UploadUtils;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	// 模型驱动需要用到的对象
	private Customer customer = new Customer();
	
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	// 注入 Service
	public CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService; 
	}
	
	/**
	 *  跳转到添加页面：saveUI
	 * @return
	 */
	public String saveUI() {
		return "saveUI";
	}

	/**
	 * 文件上传提供的三个属性
	 */
	private String uploadFileName;     // 文件名称   
	private File upload;			   // 上传的文件
	private String uploadContextType;  // 文件类型
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	/**
	 *  客户管理：保存客户
	 * @throws IOException 
	 */
	public String save() throws IOException {
		// 文件上传
		
		if(upload!=null) {
			// 上传资质图片
			// 设置文件上传的路径
			String path = "d:/upload";
			// 一个目录下存放的相同文件名：文件名随机
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// 一个目录下存放的文件过多：目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			// 创建目录
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()) {
				// 创建多级目录
				file.mkdirs();
			}
			// 进行文件上传
			File destFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, destFile);
			// 设置文件上传的路径
			customer.setCust_image(url+"/"+uuidFileName);
		}
		
		customerService.save(customer);
		return "saveSuccess";
	}
	
	/**
	 *  分页查询客户
	 */
	// 注入当前页
	private Integer currPage = 1;
	
	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}
	
	// 注入每页记录数
	private Integer pageSize = 1;
	
	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			pageSize = 1;
		}
		this.pageSize = pageSize;
	}
	
	public String findAll() {
		// 接收分页参数
		// 条件查询，带分页
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// 在web层设置条件
		if(customer.getCust_name()!=null) {
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		
		if(customer.getBaseDictSource()!=null) {
			if(customer.getBaseDictSource().getDict_id()!=null && !"".equals(customer.getBaseDictSource().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
		}
		
		if(customer.getBaseDictLevel()!=null) {
			if(customer.getBaseDictLevel().getDict_id()!=null && !"".equals(customer.getBaseDictLevel().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
		}
		
		if(customer.getBaseDictIndustry()!=null) {
			if(customer.getBaseDictIndustry().getDict_id()!=null && !"".equals(customer.getBaseDictIndustry().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
		}
		
		System.out.println(pageSize);
		// 调用业务层查询
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currPage,pageSize);
		
		// 将分页对象保存到值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * 删除客户的方法
	 */
	public String delete() {
		// 先查询在删除
		customer = customerService.findById(customer.getCust_id());
		// 删除图片
		if(customer.getCust_image() != null) {
			File file = new File(customer.getCust_image());
			if(file != null) {
				file.delete();
			}
		}
		// 删除客户
		customerService.delete(customer);
		return "deleteSuccess";
	}
	
	/**
	 * 修改客户的方法
	 */
	public String edit() {
		// 根据Id查询，跳转页面，回显数据
		customer = customerService.findById(customer.getCust_id());
		// 将customer传递到页面
		// 两种方式：第一种：手动压栈， 第二种：因为模型驱动的对象，默认就在栈顶
		// 如果使用第一种方式：回显数据： <s:property value="cust_name"/>
		// 如果使用第二种方式：回显数据： <s:property value="model.cust_name"/>
		return "editSuccess";
	}
	
	public String update() throws IOException {
		if(upload!=null) {
			// 删除原有文件
			String cust_image = customer.getCust_image();  // 从隐藏域中获取原有路径
			if(cust_image!=null || "".equals(cust_image)) {
				File file = new File(cust_image);
				if(file!=null) {
					file.delete();
				}
			}
			// 文件上传：
			// 设置文件上传的路径
			String path = "d:/upload";
			// 一个目录下存放的相同文件名：文件名随机
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// 一个目录下存放的文件过多：目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			// 创建目录
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()) {
				// 创建多级目录
				file.mkdirs();
			}
			// 进行文件上传
			File destFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, destFile);
			// 设置文件上传的路径
			customer.setCust_image(url+"/"+uuidFileName);
		}
		// 修改客户
		customerService.update(customer);
		return "updateSuccess";
	}
	
	/**
	 * 客户拜访记录管理：异步加载所有客户信息
	 * @throws IOException 
	 */
	public String findAllCustomer() throws IOException {
		List<Customer> list = customerService.findAll();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"linkMans","baseDictSource","baseDictLevel","baseDictIndustry"});
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		// 将JSON写入到页面
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
	
}
