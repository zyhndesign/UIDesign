package com.cidic.design.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.CoursewareTagDao;
import com.cidic.design.model.Courseware;
import com.cidic.design.model.CoursewareTag;
import com.cidic.design.model.Tag;

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
		Session session = this.getSessionFactory().getCurrentSession();
		String hqlDelete = "delete CoursewareTag c where c.courseware = :courseware and c.tag = :tag";
		Courseware courseware = new Courseware();
		courseware.setId(coursewareId);
		Tag tag = new Tag();
		tag.setId(tagId);
		int deletedEntities = session.createQuery(hqlDelete)
		        .setEntity("courseware", courseware)
		        .setEntity("tag",tag)
		        .executeUpdate();
		logger.info("delete courseware tag result is :"+deletedEntities);
	}

	@Override
	public void updateCoursewareTag(CoursewareTag coursewareTag, int coursewareTagId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hqlVersionedUpdate = "update CoursewareTag set tag = :tag, courseware = :courseware where id = :id";
		
		int updatedEntities = session.createQuery( hqlVersionedUpdate )
		        .setEntity( "tag", coursewareTag.getTag() )
		        .setEntity( "courseware", coursewareTag.getCourseware() )
		        .setInteger("id",coursewareTagId)
		        .executeUpdate();
		logger.info("update courseware tag result is :"+updatedEntities);
	}

}
