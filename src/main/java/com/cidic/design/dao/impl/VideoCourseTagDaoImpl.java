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

import com.cidic.design.dao.VideoCourseTagDao;
import com.cidic.design.model.Courseware;
import com.cidic.design.model.CoursewareTag;
import com.cidic.design.model.Tag;
import com.cidic.design.model.VideoCourse;
import com.cidic.design.model.VideoCourseTag;
import com.cidic.design.util.DateUtil;

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

	@Override
	public List<VideoCourse> getVideoCourseByTagName(List<String> tagName) {
		Session session = this.getSessionFactory().getCurrentSession();
		String sqlSelected = "select  c.id as video_course_tag_id, t.id as tagId, d.id as video_course_id, t.tag_name, d.title,"
				+ " d.abstract, d.duration, d.thumbnail, d.create_time, d.content, d.top_tag  from  video_course_tag c cross "
				+ " join tag t cross  join video_course d  where  d.id=c.videoCourse_id  and c.tag_id=t.id  and (  t.tag_name in (:tagNames ) )";
        
		Query query=session.createSQLQuery(sqlSelected);
		query.setParameterList("tagNames", tagName);
		List<Object[]> roles = query.list();   
		List<VideoCourse> videoCourseList = new ArrayList<VideoCourse>();
		List<VideoCourseTag> videoCourseTagList = null;
		
		VideoCourse videoCourse = null;
		VideoCourseTag videoCourseTag = null;
		
		for(Object[] role : roles){  
			videoCourse = new VideoCourse();
			videoCourse.setId(Integer.parseInt(String.valueOf(role[2])));
			videoCourse.setAbstract_(String.valueOf(role[5]));
			videoCourse.setContent(String.valueOf(role[9]));
			videoCourse.setCreateTime(DateUtil.stringToDate(String.valueOf(role[8])));
			videoCourse.setTitle(String.valueOf(role[4]));
			videoCourse.setDuration(String.valueOf(role[6]));
			videoCourse.setThumbnail(String.valueOf(role[7]));
			videoCourse.setTopTag(Integer.parseInt(String.valueOf(role[10])));
		    Tag tag = new Tag();
		    tag.setId(Integer.parseInt(String.valueOf(role[1])));
		    tag.setTagName(String.valueOf(role[3]));
		    
		    videoCourseTag = new  VideoCourseTag();
		    videoCourseTag.setTag(tag);
		    videoCourseTag.setId(Integer.parseInt(String.valueOf(role[0])));
		    videoCourseTag.setVideoCourse(videoCourse);
		    videoCourseTagList = new ArrayList<VideoCourseTag>();
		    videoCourseTagList.add(videoCourseTag);
		    videoCourse.setVideoCourseTagList(videoCourseTagList);
		    videoCourseList.add(videoCourse);
		}
		
		return videoCourseList;
	}

}
