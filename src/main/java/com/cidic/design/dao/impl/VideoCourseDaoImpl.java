package com.cidic.design.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.VideoCourseDao;
import com.cidic.design.model.VideoCourse;

@Repository
@Component
@Qualifier(value = "videoCourseDaoImpl")
public class VideoCourseDaoImpl implements VideoCourseDao {

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
	public void insertVideoCourse(VideoCourse videoCourse) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(videoCourse);
	}

	@Override
	public VideoCourse selectVideoCourse(int id) {
		Session session = this.getSessionFactory().getCurrentSession();
		VideoCourse videoCourse = (VideoCourse)session.get(VideoCourse.class, id);
		return videoCourse;
	}

	@Override
	public void updateVideoCourse(VideoCourse videoCourse) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.update(videoCourse);
	}

	@Override
	public void deleteVideoCourse(VideoCourse videoCourse) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.delete(videoCourse);
	}

	@Override
	public List<VideoCourse> getTopVideoCourse() {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "from VideoCourse order by createTime desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		return query.list();
	}

}
