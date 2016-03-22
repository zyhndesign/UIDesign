package com.cidic.design.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.VideoCourseTagDao;
import com.cidic.design.model.VideoCourseTag;

@Repository
@Component
@Qualifier(value = "videoCourseTagDaoImpl")
public class VideoCourseTagDaoImpl implements VideoCourseTagDao {

	private static final Logger logger = LoggerFactory.getLogger(VideoCourseTagDaoImpl.class);
	
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
	public void insertVideoCourseTagDao(List<VideoCourseTag> list) {
		
	}

	@Override
	public void deleteVideoCourseTag(int videoCourseId, int tagId) {
		
	}

	@Override
	public void updateVideoCourseTag(VideoCourseTag videoCourseTag, int videoCourseTagId) {
		
	}

}
