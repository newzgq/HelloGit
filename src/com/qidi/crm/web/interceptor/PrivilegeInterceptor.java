package com.qidi.crm.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.qidi.crm.bean.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		// 判断session中是否有登录用户的信息
		User existUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser==null) {
			// 存错误信息，页面跳转到登录界面
			ActionSupport actionSupport = (ActionSupport) arg0.getAction();
			actionSupport.addActionError("您还没有登录！没有权限访问");
			return "login";
		}else {
			// 已经登录
			// arg0.invoke(); 是调用我们的action方法，其返回值就是action的返回值，相当于放行。
			return arg0.invoke();
		}
	}
	
	
}
