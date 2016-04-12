package com.cidic.design.service;

import java.util.List;

import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.Courseware;

public interface CoursewareService {
	
	public void insertCourseware(Courseware courseware,String insertTag);
	
	public Courseware selectCourseware(int id);
	
	public void updateCourseware(Courseware courseware,String updateTag, String deleteTag);
	
	public void deleteCourseware(Courseware courseware);
	
	public List<Courseware> getTopCourseware();
	
	public List<Courseware> getDataByPage(int limit, int offset, String sEcho);
	
	public List<Courseware> getFrontDataByPage(int limit, int offset, int choice);
}
