package com.cidic.design.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.UserDao;
import com.cidic.design.service.UserService;

@Service
@Component
@Qualifier(value = "userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	@Qualifier(value="userDaoImpl")
	private UserDao userDaoImpl;
	
	@Override
	public void insertUser() {
		logger.info("service insert user...");
		this.userDaoImpl.insertUser();
	}

}
