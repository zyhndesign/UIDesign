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

import com.cidic.design.dao.VideoCourseTagDao;
import com.cidic.design.model.Tag;
import com.cidic.design.model.VideoCourse;
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
		Session session = this.getSessionFactory().getCurrentSession();
		String hqlDelete = "delete VideoCourseTag c where c.videoCourse = :videoCourse and c.tag = :tag";
		VideoCourse videoCourse = new VideoCourse();
		videoCourse.setId(videoCourseId);
		Tag tag = new Tag();
		tag.setId(tagId);
		int deletedEntities = session.createQuery(hqlDelete)
		        .setEntity("videoCourse", videoCourse)
		        .setEntity("tag",tag)
		        .executeUpdate();
		logger.info("delete video course tag result is :"+deletedEntities);
	}

	@Override
	public void updateVideoCourseTag(VideoCourseTag videoCourseTag, int videoCourseTagId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hqlVersionedUpdate = "update VideoCourseTag set tag = :tag, videoCourse = :videoCourse where id = :id";
		int updatedEntities = session.createQuery( hqlVersionedUpdate )
		        .setEntity( "tag", videoCourseTag.getTag())
		        .setEntity( "videoCourse", videoCourseTag.getVideoCourse() )
		        .setInteger("id",videoCourseTagId)
		        .executeUpdate();
		logger.info("update video course tag result is :"+updatedEntities);
	}

}
