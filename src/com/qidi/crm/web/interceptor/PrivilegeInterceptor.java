package com.qidi.crm.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.qidi.crm.bean.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		// �ж�session���Ƿ��е�¼�û�����Ϣ
		User existUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser==null) {
			// �������Ϣ��ҳ����ת����¼����
			ActionSupport actionSupport = (ActionSupport) arg0.getAction();
			actionSupport.addActionError("����û�е�¼��û��Ȩ�޷���");
			return "login";
		}else {
			// �Ѿ���¼
			// arg0.invoke(); �ǵ������ǵ�action�������䷵��ֵ����action�ķ���ֵ���൱�ڷ��С�
			return arg0.invoke();
		}
	}
	
	
}
