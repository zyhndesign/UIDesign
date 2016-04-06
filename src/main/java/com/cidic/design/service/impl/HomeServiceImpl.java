package com.cidic.design.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.Courseware;
import com.cidic.design.model.VideoCourse;
import com.cidic.design.service.CourseDesignService;
import com.cidic.design.service.CoursewareService;
import com.cidic.design.service.HomeService;
import com.cidic.design.service.VideoCourseService;

@Service
@Component
@Qualifier(value = "homeServiceImpl")
@Transactional
public class HomeServiceImpl implements HomeService {

	@Autowired
	@Qualifier(value="coursewareServiceImpl")
	private CoursewareService coursewareServiceImpl;
	
	@Autowired
	@Qualifier(value="courseDesignServiceImpl")
	private CourseDesignService courseDesignServiceImpl;
	
	@Autowired
	@Qualifier(value="videoCourseServiceImpl")
	private VideoCourseService videoCourseServiceImpl;
	
	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<Object> getHomeContentData() {
		
		List<Object> list = new ArrayList<Object>();
		
		List<Courseware> courseWareList = coursewareServiceImpl.getTopCourseware();
		List<CourseDesign> courseDesignList = courseDesignServiceImpl.getTopCourseDesign();
		List<VideoCourse> videoCourseList = videoCourseServiceImpl.getTopVideoCourse();
		list.add(courseWareList);
		list.add(courseDesignList);
		list.add(videoCourseList);
		
		return list;
	}

}
