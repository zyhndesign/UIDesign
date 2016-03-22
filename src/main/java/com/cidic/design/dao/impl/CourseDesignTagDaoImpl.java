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

import com.cidic.design.dao.CourseDesignTagDao;
import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.CourseDesignTag;
import com.cidic.design.model.Tag;

@Repository
@Component
@Qualifier(value = "courseDesignTagDaoImpl")
public class CourseDesignTagDaoImpl implements CourseDesignTagDao {

	private static final Logger logger = LoggerFactory.getLogger(CourseDesignTagDaoImpl.class);
	
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
	public void insertCourseDesignTagDao(List<CourseDesignTag> list) {
		
	}

	@Override
	public void deleteCourseDesignTag(int courseDesignId, int tagId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hqlDelete = "delete CourseDesignTag c where c.courseDesign = :courseDesign and c.tag = :tag";
		CourseDesign courseDesign = new CourseDesign();
		courseDesign.setId(courseDesignId);
		Tag tag = new Tag();
		tag.setId(tagId);
		int deletedEntities = session.createQuery(hqlDelete)
		        .setEntity("courseDesign", courseDesign)
		        .setEntity("tag",tag)
		        .executeUpdate();
		logger.info("delete course design tag result is :"+deletedEntities);
	}

	@Override
	public void updateCourseDesignTag(CourseDesignTag courseDesignTag, int courseDesignTagId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hqlVersionedUpdate = "update CourseDesignTag set tag_id = :tag_id, courseDesign_id = :courseDesign_id where id = :id";
		int updatedEntities = session.createQuery( hqlVersionedUpdate )
		        .setInteger( "tag_id", courseDesignTag.getTag().getId() )
		        .setInteger( "courseDesign_id", courseDesignTag.getCourseDesign().getId() )
		        .setInteger("id",courseDesignTagId)
		        .executeUpdate();
		logger.info("update course design tag result is :"+updatedEntities);
	}
}
