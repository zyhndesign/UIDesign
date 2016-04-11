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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="courseware")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Courseware implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	@Column
	private String thumbnail;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column
	private String content;
	
	@Column(name="top_tag")
	private int topTag;
	
	@Column(name="abstract")
	private String abstract_;
	
	@OneToMany(mappedBy = "courseware",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<CoursewareTag> coursewareTagList;

	public Courseware() {
	}

	public Courseware(String title, String author, String thumbnail, Date createTime, String content) {
		this.title = title;
		this.author = author;
		this.thumbnail = thumbnail;
		this.createTime = createTime;
		this.content = content;
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

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<CoursewareTag> getCoursewareTagList() {
		return coursewareTagList;
	}

	public void setCoursewareTagList(List<CoursewareTag> coursewareTagList) {
		this.coursewareTagList = coursewareTagList;
	}

	public int getTopTag() {
		return topTag;
	}

	public void setTopTag(int topTag) {
		this.topTag = topTag;
	}

	public String getAbstract_() {
		return abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}
}
