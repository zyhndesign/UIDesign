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
import com.cidic.design.util.DateUtil;

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
	public List<CourseDesign> getCourseDesignByTagName(List<String> tagName) {
		Session session = this.getSessionFactory().getCurrentSession();
		String sqlSelected = "select c.id as course_design_tag_id, t.id as tagId, d.id as course_design_id,"
				+ "t.tag_name,d.abstract,d.course_detail_id,d.create_time,d.teacher,d.title, d.top_tag,d.thumbnail,d.bg from"
				+ "  course_design_tag c cross  join  tag t cross join  course_design d  where"
				+ "  d.id=c.courseDesign_id  and c.tag_id=t.id  and ( t.tag_name in (:tagNames ) )";
        
		Query query=session.createSQLQuery(sqlSelected);
		query.setParameterList("tagNames", tagName);
		List<Object[]> roles = query.list();   
		List<CourseDesign> courseDesignList = new ArrayList<CourseDesign>();
		List<CourseDesignTag> courseDesignTagList = null;
		
		CourseDesign courseDesign = null;
		CourseDesignTag courseDesignTag = null;
		
		for(Object[] role : roles){  
			courseDesign = new CourseDesign();
		    courseDesign.setId(Integer.parseInt(String.valueOf(role[2])));
		    courseDesign.setAbstract_(String.valueOf(role[4]));
		    courseDesign.setCourseDetailId(Integer.parseInt(String.valueOf(role[5])));
		    courseDesign.setCreateTime(DateUtil.stringToDate(String.valueOf(role[6])));
		    courseDesign.setTeacher(String.valueOf(role[7]));
		    courseDesign.setTitle(String.valueOf(role[8]));
		    courseDesign.setThumbnail(String.valueOf(role[10]));
		    courseDesign.setBg(String.valueOf(role[11]));
		    
		    courseDesign.setTopTag(Integer.parseInt(String.valueOf(role[9])));
		    Tag tag = new Tag();
		    tag.setId(Integer.parseInt(String.valueOf(role[1])));
		    tag.setTagName(String.valueOf(role[3]));
		    
		    courseDesignTag = new  CourseDesignTag();
		    courseDesignTag.setTag(tag);
		    courseDesignTag.setId(Integer.parseInt(String.valueOf(role[0])));
		    courseDesignTag.setCourseDesign(courseDesign);
		    courseDesignTagList = new ArrayList<CourseDesignTag>();
		    courseDesignTagList.add(courseDesignTag);
		    courseDesign.setCourseTagList(courseDesignTagList);
		    courseDesignList.add(courseDesign);
		}
		
		return courseDesignList;
	}
}
