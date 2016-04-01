package com.cidic.design.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.CoursewareDao;
import com.cidic.design.dao.TagDao;
import com.cidic.design.model.Courseware;
import com.cidic.design.service.CoursewareService;

@Service
@Component
@Qualifier(value = "courseWareServiceImpl")
@Transactional
public class CoursewareServiceImpl implements CoursewareService {

	private static final Logger logger = LoggerFactory.getLogger(CoursewareServiceImpl.class);
	
	@Autowired
	@Qualifier(value="coursewareDaoImpl")
	private CoursewareDao coursewareDao;
	
	@Autowired
	@Qualifier(value="tagDaoImpl")
	private TagDao tagDao;
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertCourseware(Courseware courseware) {
		coursewareDao.insertCourseware(courseware);
	}

	@Override
	@Transactional (readOnly = true)
	public Courseware selectCourseware(int id) {
		
		return coursewareDao.selectCourseware(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateCourseware(Courseware courseware) {
		coursewareDao.updateCourseware(courseware);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteCourseware(Courseware courseware) {
		coursewareDao.deleteCourseware(courseware);
	}

	@Override
	@Transactional (readOnly = true)
	public List<Courseware> getTopCourseware() {
		// TODO Auto-generated method stub
		return coursewareDao.getTopCourseware();
	}

}
