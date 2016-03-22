package com.cidic.design.dao;

import com.cidic.design.model.CourseDesign;

public interface CourseDesignDao {

	public void insertCourseDesign(CourseDesign courseDesign);
	
	public CourseDesign selectCourseDesign(int id);
}
