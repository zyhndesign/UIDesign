package com.cidic.design.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.CoursewareTagDao;
import com.cidic.design.model.CoursewareTag;

@Repository
@Component
@Qualifier(value = "coursewareTagDaoImpl")
public class CoursewareTagDaoImpl implements CoursewareTagDao {

	private static final Logger logger = LoggerFactory.getLogger(CoursewareTagDaoImpl.class);
	
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
	public void insertCoursewareTagDao(List<CoursewareTag> list) {
		
	}

	@Override
	public void deleteCoursewareTag(int coursewareId, int tagId) {
		
	}

	@Override
	public void updateCoursewareTag(CoursewareTag coursewareTag, int coursewareTagId) {
		
	}

}
