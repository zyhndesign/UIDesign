package com.cidic.design.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.CoursewareTagDao;
import com.cidic.design.model.CoursewareTag;
import com.cidic.design.service.CoursewareTagService;

@Service
@Component
@Qualifier(value = "coursewareTagServiceImpl")
@Transactional
public class CoursewareTagServiceImpl implements CoursewareTagService {

	private static final Logger logger = LoggerFactory.getLogger(CoursewareTagServiceImpl.class);
	
	@Autowired
	@Qualifier(value="coursewareTagDaoImpl")
	private CoursewareTagDao coursewareTagDao;
	
	@Override
	public void insertCoursewareTagDao(List<CoursewareTag> list) {
		
	}

	@Override
	public void deleteCoursewareTag(int coursewareId, int tagId) {
		coursewareTagDao.deleteCoursewareTag(coursewareId, tagId);
	}

	@Override
	public void updateCoursewareTag(CoursewareTag coursewareTag, int coursewareTagId) {
		coursewareTagDao.updateCoursewareTag(coursewareTag, coursewareTagId);
	}

}
