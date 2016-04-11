package com.cidic.design.service;

import java.util.List;

import com.cidic.design.model.VideoCourse;
import com.cidic.design.model.VideoCourseTag;

public interface VideoCourseTagService {
	public void insertVideoCourseTagDao(List<VideoCourseTag> list);
	
	public void deleteVideoCourseTag(int videoCourseId, int tagId);
	
	public void updateVideoCourseTag(VideoCourseTag videoCourseTag, int videoCourseTagId);
	
	public List<VideoCourse> getVideoCourseByTagName(List<String> tagName);
}
