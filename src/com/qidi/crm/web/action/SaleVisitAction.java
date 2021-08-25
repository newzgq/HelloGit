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
	
	// ģ������ʹ�õĶ���
	private SaleVisit saleVisit = new SaleVisit();
	
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;

	/**
	 *  ���շ�ҳ�Ĳ���
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
	
	
	// ��������
	private Date visit_end_time;
	public Date getVisit_end_time() {
		return visit_end_time;
	}
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}
	/**
	 *  �������ķ�ҳ��ѯ�ķ���
	 */
	public String findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		// ��������
		// ��ҵ��Ա���Ʋ�ѯ������
		if(saleVisit.getVisit_time()!=null) {
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if(visit_end_time!=null) {
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		
		// ����ҵ���
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
		// ������ѹ��ֵջ
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * ��ת�����ҳ��ķ���
	 */
	public String saveUI() {
		return "saveUI";
	}
	
	/**
	 * �ͻ��ݷü�¼������Ӱݷü�¼�ķ���
	 */
	public String save() {
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
	
	/**
	 * �ͻ��ݷü�¼����ɾ���ݷü�¼�ķ���
	 */
	public String delete() {
		// �Ȳ�ѯ��ɾ��
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		// ɾ���ݷü�¼
		saleVisitService.delete(saleVisit);
		return "deleteSuccess";
	}
	
	/**
	 *  ��ת���޸�ҳ��ķ���
	 */
	public String edit() {
		// ����id��ѯ����תҳ�棬��������
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		// ��saleVisit���ݵ�ҳ��
		return "editSuccess";
	}
	
	/**
	 * �ͻ��ݷü�¼�����޸İݷü�¼�ķ���
	 */
	public String update() {
		saleVisitService.update(saleVisit);
		return "updateSuccess";
	}
	
}
