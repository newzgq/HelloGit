package com.qidi.crm.service;

import java.util.List;

import com.qidi.crm.bean.User;

public interface UserService {
	
	public void regist(User user);
	
	public User login(User user);
	
	public void update(User user);
	
	public List<User> findAll();
}
