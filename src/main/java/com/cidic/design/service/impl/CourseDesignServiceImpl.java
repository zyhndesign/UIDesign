package com.cidic.design.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.CourseDesignDao;
import com.cidic.design.model.CourseDesign;
import com.cidic.design.service.CourseDesignService;

@Service
@Component
@Qualifier(value = "courseDesignServiceImpl")
@Transactional
public class CourseDesignServiceImpl implements CourseDesignService {

	private static final Logger logger = LoggerFactory.getLogger(CourseDesignServiceImpl.class);
	
	@Autowired
	@Qualifier(value="courseDesignDaoImpl")
	private CourseDesignDao courseDesignDao;
	
	@Override
	public void insertCourseDesign(CourseDesign courseDesign) {
		courseDesignDao.insertCourseDesign(courseDesign);
	}

	@Override
	public CourseDesign selectCourseDesign(int id) {
		return courseDesignDao.selectCourseDesign(id);
	}

	@Override
	public void updateCourseDesign(CourseDesign courseDesign) {
		courseDesignDao.updateCourseDesign(courseDesign);
	}

	@Override
	public void deleteCourseDesign(CourseDesign courseDesign) {
		// TODO Auto-generated method stub
		courseDesignDao.deleteCourseDesign(courseDesign);
	}

	@Override
	public List<CourseDesign> getTopCourseware() {
		// TODO Auto-generated method stub
		return courseDesignDao.getTopCourseware();
	}

}
