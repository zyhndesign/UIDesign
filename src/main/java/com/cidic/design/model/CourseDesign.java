package com.cidic.design.model;
// Generated 2016-3-14 16:01:31 by Hibernate Tools 4.3.1.Final

import java.util.Date;


public class CourseDesign implements java.io.Serializable {

	private Integer id;
	private String title;
	private String abstract_;
	private String teacher;
	private Date createTime;
	private int courseDetailId;

	public CourseDesign() {
	}

	public CourseDesign(String title, String abstract_, String teacher, Date createTime, int courseDetailId) {
		this.title = title;
		this.abstract_ = abstract_;
		this.teacher = teacher;
		this.createTime = createTime;
		this.courseDetailId = courseDetailId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getTeacher() {
		return this.teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getCourseDetailId() {
		return this.courseDetailId;
	}

	public void setCourseDetailId(int courseDetailId) {
		this.courseDetailId = courseDetailId;
	}

}
