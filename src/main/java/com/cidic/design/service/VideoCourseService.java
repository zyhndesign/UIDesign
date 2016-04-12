package com.cidic.design.service;

import java.util.List;

import com.cidic.design.model.VideoCourse;

public interface VideoCourseService {
	
	public void insertVideoCourse(VideoCourse videoCourse,String insertTag);
	
	public VideoCourse selectVideoCourse(int id);
	
	public void updateVideoCourse(VideoCourse videoCourse,String updateTag, String deleteTag);
	
	public void deleteVideoCourse(VideoCourse videoCourse);
	
	public List<VideoCourse> getTopVideoCourse();
	
	public List<VideoCourse> getDataByPage(int limit, int offset, String sEcho);
	
	public List<VideoCourse> getFrontDataByPage(int limit, int offset, int choice);
}
