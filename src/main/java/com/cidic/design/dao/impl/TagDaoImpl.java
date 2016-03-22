package com.cidic.design.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.TagDao;
import com.cidic.design.model.Tag;

@Repository
@Component
@Qualifier(value = "tagDaoImpl")
public class TagDaoImpl implements TagDao {

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
	public void insertTag(Tag tag) {
		
	}

	@Override
	public Tag selectTagById(int id) {
		
		return null;
	}

}
