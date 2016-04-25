package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.Courseware;

public interface CourseDesignDao {

	public void insertCourseDesign(CourseDesign courseDesign);
	
	public CourseDesign selectCourseDesign(int id);
	
	public void updateCourseDesign(CourseDesign courseDesign);
	
	public void deleteCourseDesign(CourseDesign courseDesign);
	
	public List<CourseDesign> getTopCourseware();
	
	public List<CourseDesign> getDataByPage(int limit, int offset, String sEcho);
	
	public List<CourseDesign> getFrontDataByPage(int limit, int offset, int choice);
	
	public int getCountData();
}
