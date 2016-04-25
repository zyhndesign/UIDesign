package com.cidic.design.service;

import java.util.List;

import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.CoursePageModel;

public interface CourseDesignService {

	public void insertCourseDesign(CourseDesign courseDesign,String insertTag);
	
	public CourseDesign selectCourseDesign(int id);
	
	public void updateCourseDesign(CourseDesign courseDesign,String updateTag, String deleteTag);
	
	public void deleteCourseDesign(CourseDesign courseDesign);
	
	public List<CourseDesign> getTopCourseDesign();
	
	public CoursePageModel getDataByPage(int limit, int offset, String sEcho);
	
	public List<CourseDesign> getFrontDataByPage(int limit, int offset, int choice);
}

