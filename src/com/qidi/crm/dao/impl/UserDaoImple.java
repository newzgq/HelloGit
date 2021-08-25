package com.qidi.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.qidi.crm.bean.User;
import com.qidi.crm.dao.UserDao;

public class UserDaoImple extends BaseDaoImple<User> implements UserDao{

	public UserDaoImple() {
		super();
	}
	
//	@Override
//	public void save(User user) {
//		this.getHibernateTemplate().save(user);
//	}

	@Override
	public User login(User user) {
//		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(), user.getUser_password());
	
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ? and user_password = ?", new String[] {user.getUser_code(),user.getUser_password()} );
		
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

//	@Override
//	public void update(User user) {
//		getHibernateTemplate().update(user);
//	}
	
	

}
