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
@Table(name="video_course")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VideoCourse implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String title;
	
	@Column(name="abstract")
	private String abstract_;
	
	@Column
	private String duration;
	
	@Column
	private String thumbnail;
	
	@Column
	private String content;

	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="top_tag")
	private int topTag;
	
	@OneToMany(mappedBy = "videoCourse",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<VideoCourseTag> videoCourseTagList;
	
	public VideoCourse() {
	}

	public VideoCourse(String title, String abstract_, String duration, String thumbnail, String content) {
		this.title = title;
		this.abstract_ = abstract_;
		this.duration = duration;
		this.thumbnail = thumbnail;
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

	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getTopTag() {
		return topTag;
	}

	public void setTopTag(int topTag) {
		this.topTag = topTag;
	}

	public List<VideoCourseTag> getVideoCourseTagList() {
		return videoCourseTagList;
	}

	public void setVideoCourseTagList(List<VideoCourseTag> videoCourseTagList) {
		this.videoCourseTagList = videoCourseTagList;
	}
	
}
