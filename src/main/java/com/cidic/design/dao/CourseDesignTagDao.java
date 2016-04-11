package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.CourseDesignTag;

public interface CourseDesignTagDao {
	
	public void insertCourseDesignTagDao(List<CourseDesignTag> list);
	
	public void deleteCourseDesignTag(int courseDesignId, int tagId);
	
	public void updateCourseDesignTag(CourseDesignTag courseDesignTag, int courseDesignTagId);
	
	public List<CourseDesign> getCourseDesignByTagName(List<String> tagName);
}
