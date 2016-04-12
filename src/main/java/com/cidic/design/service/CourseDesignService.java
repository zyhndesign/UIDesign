package com.cidic.design.service;

import java.util.List;

import com.cidic.design.model.CourseDesign;

public interface CourseDesignService {

	public void insertCourseDesign(CourseDesign courseDesign,String insertTag);
	
	public CourseDesign selectCourseDesign(int id);
	
	public void updateCourseDesign(CourseDesign courseDesign,String updateTag, String deleteTag);
	
	public void deleteCourseDesign(CourseDesign courseDesign);
	
	public List<CourseDesign> getTopCourseDesign();
	
	public List<CourseDesign> getDataByPage(int limit, int offset, String sEcho);
	
}

