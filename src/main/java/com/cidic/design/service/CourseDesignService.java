package com.cidic.design.service;

import com.cidic.design.model.CourseDesign;

public interface CourseDesignService {

	public void insertCourseDesign(CourseDesign courseDesign);
	
	public CourseDesign selectCourseDesign(int id);
}

