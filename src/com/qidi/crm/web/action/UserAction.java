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
	
	// 注入UserService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 注册的方法
	 */
	public String regist() {
		userService.regist(user);
		return LOGIN;
	}
	
	/**
	 *  用户登录的方法
	 *  Title：login
	 */
	public String login() {
		User existUser = userService.login(user);
		if(existUser != null) {
			// 登陆成功
			// 将用户信息保存到session中
//			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			ActionContext.getContext().getSession().put("existUser", existUser);
			return SUCCESS;
		}else {
			// 登录失败
			// 保存错误信息
			this.addActionError("用户或密码错误!!");
			return LOGIN;
		}
	}
	
	/**
	 * 退出登录
	 * @return 
	 */
	public String exit() {
		ActionContext.getContext().getSession().remove("existUser");
		return LOGIN;
	}
	
	/**
	 * 用户修改密码的方法
	 * 保存修改
	 */
	public String editpass() {
//		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		User edituser = (User) ActionContext.getContext().getSession().get("existUser");
		// 存储原密码
		String password = edituser.getUser_password();
		// 读取接收到的新密码到新的user对象里
		edituser.setUser_password(this.user.getUser_password());
		if(this.user.getUser_password().equals(password)) {
			ServletActionContext.getRequest().setAttribute("error","新密码与原密码一致，请重新修改");
			return "editPswFail";
		}
		userService.update(edituser);
//		ServletActionContext.getRequest().getSession().setAttribute("user", this.user);
		return "editPswSuccess";
	}
	
	// 跳转到修改密码
	public String toEdit() {
		return "toEdit";
	}
	
	/**
	 * 客户拜访记录管理：异步加载所有用户信息
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
