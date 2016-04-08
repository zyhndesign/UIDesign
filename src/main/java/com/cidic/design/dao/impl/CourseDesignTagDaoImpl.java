package com.cidic.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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
		String hqlVersionedUpdate = "update CourseDesignTag set tag = :tag, courseDesign = :courseDesign where id = :id";
		int updatedEntities = session.createQuery( hqlVersionedUpdate )
		        .setEntity( "tag", courseDesignTag.getTag())
		        .setEntity( "courseDesign", courseDesignTag.getCourseDesign() )
		        .setInteger("id",courseDesignTagId)
		        .executeUpdate();
		logger.info("update course design tag result is :"+updatedEntities);
	}

	@Override
	public List<CourseDesignTag> getCourseDesignByTagName(List<String> tagName) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hqlSelected = "from CourseDesignTag c, Tag t, CourseDesign d where d = c.courseDesign and c.tag = t and t.tagName in (:tagNames)";
		Query query=session.createQuery(hqlSelected);
		query.setParameterList("tagNames", tagName);
		
		return query.list();
	}
}
