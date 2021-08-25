package com.qidi.crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qidi.crm.bean.User;
import com.qidi.crm.dao.UserDao;
import com.qidi.crm.service.UserService;
import com.qidi.crm.util.MD5Utils;

@Transactional
public class UserServiceImple implements UserService{

	// 注入 UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void regist(User user) {
		
		// 对密码进行加密处理
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		// 设置用户状态
		user.setUser_state("1");
		userDao.save(user);
	}

	@Override
	public User login(User user) {
		// 对密码进行加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		
		return userDao.login(user);
	}

	// 修改密码
	@Override
	public void update(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		userDao.update(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	

}
