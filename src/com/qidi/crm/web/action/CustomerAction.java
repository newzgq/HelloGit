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

	// ģ��������Ҫ�õ��Ķ���
	private Customer customer = new Customer();
	
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	// ע�� Service
	public CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService; 
	}
	
	/**
	 *  ��ת�����ҳ�棺saveUI
	 * @return
	 */
	public String saveUI() {
		return "saveUI";
	}

	/**
	 * �ļ��ϴ��ṩ����������
	 */
	private String uploadFileName;     // �ļ�����   
	private File upload;			   // �ϴ����ļ�
	private String uploadContextType;  // �ļ�����
	
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
	 *  �ͻ���������ͻ�
	 * @throws IOException 
	 */
	public String save() throws IOException {
		// �ļ��ϴ�
		
		if(upload!=null) {
			// �ϴ�����ͼƬ
			// �����ļ��ϴ���·��
			String path = "d:/upload";
			// һ��Ŀ¼�´�ŵ���ͬ�ļ������ļ������
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// һ��Ŀ¼�´�ŵ��ļ����ࣺĿ¼����
			String realPath = UploadUtils.getPath(uuidFileName);
			// ����Ŀ¼
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()) {
				// �����༶Ŀ¼
				file.mkdirs();
			}
			// �����ļ��ϴ�
			File destFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, destFile);
			// �����ļ��ϴ���·��
			customer.setCust_image(url+"/"+uuidFileName);
		}
		
		customerService.save(customer);
		return "saveSuccess";
	}
	
	/**
	 *  ��ҳ��ѯ�ͻ�
	 */
	// ע�뵱ǰҳ
	private Integer currPage = 1;
	
	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}
	
	// ע��ÿҳ��¼��
	private Integer pageSize = 1;
	
	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			pageSize = 1;
		}
		this.pageSize = pageSize;
	}
	
	public String findAll() {
		// ���շ�ҳ����
		// ������ѯ������ҳ
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// ��web����������
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
		// ����ҵ����ѯ
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currPage,pageSize);
		
		// ����ҳ���󱣴浽ֵջ
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * ɾ���ͻ��ķ���
	 */
	public String delete() {
		// �Ȳ�ѯ��ɾ��
		customer = customerService.findById(customer.getCust_id());
		// ɾ��ͼƬ
		if(customer.getCust_image() != null) {
			File file = new File(customer.getCust_image());
			if(file != null) {
				file.delete();
			}
		}
		// ɾ���ͻ�
		customerService.delete(customer);
		return "deleteSuccess";
	}
	
	/**
	 * �޸Ŀͻ��ķ���
	 */
	public String edit() {
		// ����Id��ѯ����תҳ�棬��������
		customer = customerService.findById(customer.getCust_id());
		// ��customer���ݵ�ҳ��
		// ���ַ�ʽ����һ�֣��ֶ�ѹջ�� �ڶ��֣���Ϊģ�������Ķ���Ĭ�Ͼ���ջ��
		// ���ʹ�õ�һ�ַ�ʽ���������ݣ� <s:property value="cust_name"/>
		// ���ʹ�õڶ��ַ�ʽ���������ݣ� <s:property value="model.cust_name"/>
		return "editSuccess";
	}
	
	public String update() throws IOException {
		if(upload!=null) {
			// ɾ��ԭ���ļ�
			String cust_image = customer.getCust_image();  // ���������л�ȡԭ��·��
			if(cust_image!=null || "".equals(cust_image)) {
				File file = new File(cust_image);
				if(file!=null) {
					file.delete();
				}
			}
			// �ļ��ϴ���
			// �����ļ��ϴ���·��
			String path = "d:/upload";
			// һ��Ŀ¼�´�ŵ���ͬ�ļ������ļ������
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// һ��Ŀ¼�´�ŵ��ļ����ࣺĿ¼����
			String realPath = UploadUtils.getPath(uuidFileName);
			// ����Ŀ¼
			String url = path + realPath;
			File file = new File(url);
			if(!file.exists()) {
				// �����༶Ŀ¼
				file.mkdirs();
			}
			// �����ļ��ϴ�
			File destFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, destFile);
			// �����ļ��ϴ���·��
			customer.setCust_image(url+"/"+uuidFileName);
		}
		// �޸Ŀͻ�
		customerService.update(customer);
		return "updateSuccess";
	}
	
	/**
	 * �ͻ��ݷü�¼�����첽�������пͻ���Ϣ
	 * @throws IOException 
	 */
	public String findAllCustomer() throws IOException {
		List<Customer> list = customerService.findAll();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"linkMans","baseDictSource","baseDictLevel","baseDictIndustry"});
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		// ��JSONд�뵽ҳ��
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
	
}
