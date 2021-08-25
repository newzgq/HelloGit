package com.qidi.crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qidi.crm.bean.User;
import com.qidi.crm.dao.UserDao;
import com.qidi.crm.service.UserService;
import com.qidi.crm.util.MD5Utils;

@Transactional
public class UserServiceImple implements UserService{

	// ע�� UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void regist(User user) {
		
		// ��������м��ܴ���
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		// �����û�״̬
		user.setUser_state("1");
		userDao.save(user);
	}

	@Override
	public User login(User user) {
		// ��������м���
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		
		return userDao.login(user);
	}

	// �޸�����
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
