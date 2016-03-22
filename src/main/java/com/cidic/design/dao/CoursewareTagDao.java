package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.CoursewareTag;

public interface CoursewareTagDao {
	
	public void insertCoursewareTagDao(List<CoursewareTag> list);
	
	public void deleteCoursewareTag(int coursewareId, int tagId);
	
	public void updateCoursewareTag(CoursewareTag coursewareTag, int coursewareTagId);
}
