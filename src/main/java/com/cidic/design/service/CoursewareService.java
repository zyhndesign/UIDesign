package com.cidic.design.service;

import java.util.List;

import com.cidic.design.model.Courseware;

public interface CoursewareService {
	
	public void insertCourseware(Courseware courseware);
	
	public Courseware selectCourseware(int id);
	
	public void updateCourseware(Courseware courseware);
	
	public void deleteCourseware(Courseware courseware);
	
	public List<Courseware> getTopCourseware();
}
