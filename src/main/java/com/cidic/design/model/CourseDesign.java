package com.cidic.design.model;
// Generated 2016-3-14 16:01:31 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="course_design")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseDesign implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String title;
	
	@Column(name="abstract") 
	private String abstract_;
	
	@Column
	private String teacher;
	       
    @Column(name="create_time")
	private Date createTime;
	
	@Column(name="course_detail_id")
	private int courseDetailId;

	@Column(name="top_tag")
	private int topTag;
	
	@Column(name="bg")
	private String bg;
	
	@Column(name="thumbnail")
	private String thumbnail;
	
	@OneToMany(mappedBy = "courseDesign",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<CourseDesignTag> courseTagList;
	 
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

	public List<CourseDesignTag> getCourseTagList() {
		return courseTagList;
	}

	public void setCourseTagList(List<CourseDesignTag> courseTagList) {
		this.courseTagList = courseTagList;
	}

	public int getTopTag() {
		return topTag;
	}

	public void setTopTag(int topTag) {
		this.topTag = topTag;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	
}
