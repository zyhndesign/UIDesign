package com.cidic.design.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.CourseDesignDao;
import com.cidic.design.model.CourseDesign;

@Repository
@Component
@Qualifier(value = "courseDesignDaoImpl")
public class CourseDesignDaoImpl implements CourseDesignDao {

private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
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
	public void insertCourseDesign(CourseDesign courseDesign) {
		Session session = this.getSessionFactory().getCurrentSession();
        logger.info("save course design :" +courseDesign.getTeacher());
        session.save(courseDesign);
	}

	@Override
	public CourseDesign selectCourseDesign(int id) {
		
		Session session = this.getSessionFactory().getCurrentSession();
		CourseDesign courseDesign = (CourseDesign)session.get(CourseDesign.class, id);
		return courseDesign;
		/*
		Session session = sessionFactory.openSession();  
        session.beginTransaction();  
        CourseDesign courseDesign = (CourseDesign) session.load(CourseDesign.class, id);  
       
        session.getTransaction().commit();  
        session.close();   
        return courseDesign;
        */
	}

}
