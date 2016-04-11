package com.cidic.design.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.cidic.design.service.CourseDesignTagService;
import com.cidic.design.service.CoursewareService;
import com.cidic.design.service.CoursewareTagService;
import com.cidic.design.service.HomeService;
import com.cidic.design.service.VideoCourseService;
import com.cidic.design.service.VideoCourseTagService;

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
	
	@Autowired
	@Qualifier(value="courseDesignTagServiceImpl")
	private CourseDesignTagService courseDesignTagServiceImpl;
	
	@Autowired
	@Qualifier(value="coursewareTagServiceImpl")
	private CoursewareTagService coursewareTagServiceImpl;
	
	@Autowired
	@Qualifier(value="videoCourseTagServiceImpl")
	private VideoCourseTagService videoCourseTagServiceImpl;
	
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

	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<Object> getSearchResultByKeywards(List<String> keywords) {
		
		List<Object> list = new ArrayList<Object>();
		
		List<CourseDesign> courseDesignList = courseDesignTagServiceImpl.getCourseDesignByTagName(keywords);
		list.add(courseDesignList);
		List<Courseware> coursewareList = coursewareTagServiceImpl.getCoursewareByTagName(keywords);
		list.add(coursewareList);
		List<VideoCourse> videoCourseList = videoCourseTagServiceImpl.getVideoCourseByTagName(keywords);
		list.add(videoCourseList);
		
		return list;
	}

	
}
