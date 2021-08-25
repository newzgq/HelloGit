package com.qidi.crm.web.action;

import java.io.IOException;
import java.util.List;

import javax.imageio.IIOException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qidi.crm.bean.User;
import com.qidi.crm.service.UserService;

import net.sf.json.JSONArray;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	// ע��UserService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * ע��ķ���
	 */
	public String regist() {
		userService.regist(user);
		return LOGIN;
	}
	
	/**
	 *  �û���¼�ķ���
	 *  Title��login
	 */
	public String login() {
		User existUser = userService.login(user);
		if(existUser != null) {
			// ��½�ɹ�
			// ���û���Ϣ���浽session��
//			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			ActionContext.getContext().getSession().put("existUser", existUser);
			return SUCCESS;
		}else {
			// ��¼ʧ��
			// ���������Ϣ
			this.addActionError("�û����������!!");
			return LOGIN;
		}
	}
	
	/**
	 * �˳���¼
	 * @return 
	 */
	public String exit() {
		ActionContext.getContext().getSession().remove("existUser");
		return LOGIN;
	}
	
	/**
	 * �û��޸�����ķ���
	 * �����޸�
	 */
	public String editpass() {
//		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		User edituser = (User) ActionContext.getContext().getSession().get("existUser");
		// �洢ԭ����
		String password = edituser.getUser_password();
		// ��ȡ���յ��������뵽�µ�user������
		edituser.setUser_password(this.user.getUser_password());
		if(this.user.getUser_password().equals(password)) {
			ServletActionContext.getRequest().setAttribute("error","��������ԭ����һ�£��������޸�");
			return "editPswFail";
		}
		userService.update(edituser);
//		ServletActionContext.getRequest().getSession().setAttribute("user", this.user);
		return "editPswSuccess";
	}
	
	// ��ת���޸�����
	public String toEdit() {
		return "toEdit";
	}
	
	/**
	 * �ͻ��ݷü�¼�����첽���������û���Ϣ
	 * @throws IOException 
	 */
	public String findAllUser() throws IOException {
		List<User> list = userService.findAll();
		JSONArray jsonArray = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
	
}
