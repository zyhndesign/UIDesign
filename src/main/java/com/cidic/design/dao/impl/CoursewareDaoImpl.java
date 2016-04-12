package com.cidic.design.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.CoursewareDao;
import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.Courseware;

@Repository
@Component
@Qualifier(value = "coursewareDaoImpl")
public class CoursewareDaoImpl implements CoursewareDao {

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
	public void insertCourseware(Courseware courseware) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(courseware);
	}

	@Override
	public Courseware selectCourseware(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		Courseware courseware = (Courseware)session.get(Courseware.class, id);
		return courseware;
	}

	@Override
	public void updateCourseware(Courseware courseware) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(courseware);
	}

	@Override
	public void deleteCourseware(Courseware courseware) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.delete(courseware);
	}

	@Override
	public List<Courseware> getTopCourseware() {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "from Courseware order by createTime desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		return query.list();
	}

	@Override
	public List<Courseware> getDataByPage(int limit, int offset, String sEcho) {
		Session session = this.getSessionFactory().getCurrentSession();
		final String hql = " from Courseware  order by createTime desc"; 
        final Query query = session.createQuery(hql);   
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        final List<Courseware> list = query.list();  
		return list;
	}
	
	@Override
	public List<Courseware> getFrontDataByPage(int limit, int offset, int choice) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = null; 
		if (choice == 0){
			hql = "from Courseware order by createTime desc";
		}
		else{
			hql = "from Courseware where topTag=1 order by createTime desc";
		}
	 
        final Query query = session.createQuery(hql);   
        query.setFirstResult(offset);    
        query.setMaxResults(limit); 
        
        final List<Courseware> list = query.list();  
		return list;
	}

}
