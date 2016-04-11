package com.cidic.design.service;

import java.util.List;

import com.cidic.design.model.Courseware;
import com.cidic.design.model.CoursewareTag;

public interface CoursewareTagService {
	public void insertCoursewareTagDao(List<CoursewareTag> list);
	
	public void deleteCoursewareTag(int coursewareId, int tagId);
	
	public void updateCoursewareTag(CoursewareTag coursewareTag, int coursewareTagId);
	
	public List<Courseware> getCoursewareByTagName(List<String> tagName);
}
