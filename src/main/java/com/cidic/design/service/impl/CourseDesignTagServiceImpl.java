package com.cidic.design.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.CourseDesignTagDao;
import com.cidic.design.model.CourseDesignTag;
import com.cidic.design.service.CourseDesignTagService;

@Service
@Component
@Qualifier(value = "courseDesignTagServiceImpl")
@Transactional
public class CourseDesignTagServiceImpl implements CourseDesignTagService {

	private static final Logger logger = LoggerFactory.getLogger(CourseDesignServiceImpl.class);
	
	@Autowired
	@Qualifier(value="courseDesignTagDaoImpl")
	private CourseDesignTagDao courseDesignTagDao;
	
	@Override
	public void insertCourseDesignTagDao(List<CourseDesignTag> list) {
		
	}

	@Override
	public void deleteCourseDesignTag(int courseDesignId, int tagId) {
		courseDesignTagDao.deleteCourseDesignTag(courseDesignId, tagId);
	}

	@Override
	public void updateCourseDesignTag(CourseDesignTag courseDesignTag, int courseDesignTagId) {
		courseDesignTagDao.updateCourseDesignTag(courseDesignTag, courseDesignTagId);
	}

}
