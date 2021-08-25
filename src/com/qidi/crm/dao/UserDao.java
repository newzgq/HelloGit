package com.qidi.crm.dao;

import com.qidi.crm.bean.User;

public interface UserDao extends BaseDao<User> {

//	public void save(User user);
	
	public User login(User user);
	
//	public void update(User user);
}
