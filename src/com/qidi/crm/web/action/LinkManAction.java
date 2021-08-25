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

	// ģ������ʹ�õĶ���
	private LinkMan linkMan = new LinkMan();
	
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	// ע��Service
	private LinkManService linkManService;
	
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}

	// ������ϵ��
	// ע��ͻ������Service
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	/**
	 * ��ת�����ҳ��
	 */
	public String saveUI() {
		// ��ѯ���пͻ�
		List<Customer> list = customerService.findAll();
		// �����пͻ�����ֵջ
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	/**
	 * ��ϵ�˹���������ϵ��
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
	 * ��������ҳ��ѯ��ϵ��
	 */
	public String findAll() {
		// ��������������ѯ
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		// ��������
		if(linkMan.getLkm_name()!=null) {
			if(!linkMan.getLkm_name().trim().equals("")) {
				// ���ð����Ʋ�ѯ������
				detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
			}
		}
		if(linkMan.getLkm_gender()!=null) {
			if(!linkMan.getLkm_gender().trim().equals("")) {
				//���ð��Ա��ѯ������
				detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
			}
		}
		
		// ����ҵ���
		PageBean<LinkMan> pageBean = linkManService.findByPage(detachedCriteria,currPage,pageSize);
		// ��pageBeanѹ��ֵջ��
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * ��ת���༭ҳ��
	 */
	public String edit() {
		// ��ѯ���пͻ�
		List<Customer> list = customerService.findAll();
		// ����ID��ѯĳ����ϵ��
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// �����пͻ�����ϵ�˴��ص�ҳ��
		ActionContext.getContext().getValueStack().set("list", list);
		// �������ֵҲ����ֵջ�У������Զ�����
		ActionContext.getContext().getValueStack().push(linkMan);
		return "editSuccess";
	}
	/**
	 * �޸���ϵ�˵ķ���
	 */
	public String update() {
		// ����ҵ���
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	/**
	 * ɾ����ϵ�˵ķ���
	 */
	public String delete() {
		// �Ȳ�ѯ��ɾ��
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// ɾ����ϵ��
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
	
}
