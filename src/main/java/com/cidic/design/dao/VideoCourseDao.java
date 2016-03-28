package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.VideoCourse;

public interface VideoCourseDao {
	
	public void insertVideoCourse(VideoCourse videoCourse);
	
	public VideoCourse selectVideoCourse(int id);
	
	public void updateVideoCourse(VideoCourse videoCourse);
	
	public void deleteVideoCourse(VideoCourse videoCourse);
	
	public List<VideoCourse> getTopVideoCourse();
}
