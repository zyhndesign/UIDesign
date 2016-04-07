package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.CourseDesign;

public interface CourseDesignDao {

	public void insertCourseDesign(CourseDesign courseDesign);
	
	public CourseDesign selectCourseDesign(int id);
	
	public void updateCourseDesign(CourseDesign courseDesign);
	
	public void deleteCourseDesign(CourseDesign courseDesign);
	
	public List<CourseDesign> getTopCourseware();
	
	public List<CourseDesign> getDataByPage(int limit, int offset, String sEcho);
}
