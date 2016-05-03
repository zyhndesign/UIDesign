package com.cidic.design.dao.impl;

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

import com.cidic.design.dao.CourseDesignDao;
import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.Courseware;

@Repository
@Component
@Qualifier(value = "courseDesignDaoImpl")
public class CourseDesignDaoImpl implements CourseDesignDao {

	private static final Logger logger = LoggerFactory.getLogger(CourseDesignDaoImpl.class);
	
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

	@Override
	public void updateCourseDesign(CourseDesign courseDesign) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(courseDesign);
	}

	@Override
	public void deleteCourseDesign(CourseDesign courseDesign) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.delete(courseDesign);
	}
	
	@Override
	public List<CourseDesign> getTopCourseware() {
		
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "from CourseDesign order by createTime desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		return query.list();
	}

	@Override
	public List<CourseDesign> getDataByPage(int limit, int offset, String sEcho) {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " from CourseDesign order by createTime desc"; 
        final Query query = session.createQuery(hql);   
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        final List<CourseDesign> list = query.list();  
		return list;
	}

	@Override
	public List<CourseDesign> getFrontDataByPage(int limit, int offset, int choice) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = null; 
		if (choice == 0){
			hql = "from CourseDesign order by createTime desc";
		}
		else{
			hql = "from CourseDesign where topTag=1 order by createTime desc";
		}
	 
        final Query query = session.createQuery(hql);   
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        
        final List<CourseDesign> list = query.list();  
		return list;
	}

	@Override
	public int getCountData() {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "select count(*) from CourseDesign";  
		Query query =  session.createQuery(hql);  
		return ((Number)query.uniqueResult()).intValue();
	}
	
	
}
