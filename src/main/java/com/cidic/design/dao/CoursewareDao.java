package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.Courseware;

public interface CoursewareDao {
	public void insertCourseware(Courseware courseware);
	
	public Courseware selectCourseware(int id);
	
	public List<Courseware> getTopCourseware();
	
	public void updateCourseware(Courseware courseware);
	
	public void deleteCourseware(Courseware courseware);
}
