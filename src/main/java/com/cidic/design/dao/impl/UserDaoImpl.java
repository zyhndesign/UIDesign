package com.cidic.design.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.UserDao;
import com.cidic.design.model.User;

@Repository
@Component
@Qualifier(value = "userDaoImpl")
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertUser() {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().getCurrentSession();
       
        User user = new User();
        user.setUsername("liling");
        user.setPassword("111111");
        logger.info("save user :" +user.getUsername());
        session.save(user);
	}

}
