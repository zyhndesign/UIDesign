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

import com.cidic.design.dao.CoursewareTagDao;
import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.CourseDesignTag;
import com.cidic.design.model.Courseware;
import com.cidic.design.model.CoursewareTag;
import com.cidic.design.model.Tag;
import com.cidic.design.util.DateUtil;

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

	@Override
	public List<Courseware> getCoursewareByTagName(List<String> tagName) {
		Session session = this.getSessionFactory().getCurrentSession();
		String sqlSelected = "select c.id as courseware_tag_id, t.id as tagId, d.id as courseware_id, t.tag_name, d.title, d.author,"
				+ " d.thumbnail, d.create_time, d.content, d.top_tag  from courseware_tag c cross  join tag t cross  join"
				+ " courseware d  where d.id=c.courseware_id  and c.tag_id=t.id  and ( t.tag_name in ( :tagNames ))";
        
		Query query=session.createSQLQuery(sqlSelected);
		query.setParameterList("tagNames", tagName);
		List<Object[]> roles = query.list();   
		List<Courseware> coursewareList = new ArrayList<Courseware>();
		List<CoursewareTag> coursewareTagList = null;
		
		Courseware courseware = null;
		CoursewareTag coursewareTag = null;
		
		for(Object[] role : roles){  
			courseware = new Courseware();
			courseware.setId(Integer.parseInt(String.valueOf(role[2])));
			courseware.setAuthor(String.valueOf(role[5]));
			courseware.setContent(String.valueOf(role[8]));
			courseware.setCreateTime(DateUtil.stringToDate(String.valueOf(role[7])));
			courseware.setTitle(String.valueOf(role[4]));
			courseware.setThumbnail(String.valueOf(role[6]));
			
			courseware.setTopTag(Integer.parseInt(String.valueOf(role[9])));
		    Tag tag = new Tag();
		    tag.setId(Integer.parseInt(String.valueOf(role[1])));
		    tag.setTagName(String.valueOf(role[3]));
		    
		    coursewareTag = new  CoursewareTag();
		    coursewareTag.setTag(tag);
		    coursewareTag.setId(Integer.parseInt(String.valueOf(role[0])));
		    coursewareTag.setCourseware(courseware);
		    coursewareTagList = new ArrayList<CoursewareTag>();
		    coursewareTagList.add(coursewareTag);
		    courseware.setCoursewareTagList(coursewareTagList);
		    coursewareList.add(courseware);
		}
		
		return coursewareList;
	}

}
