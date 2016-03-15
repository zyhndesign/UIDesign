package com.cidic.design.model;
// Generated 2016-3-14 16:01:31 by Hibernate Tools 4.3.1.Final


public class VideoCourse implements java.io.Serializable {

	private Integer id;
	private String title;
	private String abstract_;
	private String duration;
	private String thumbnail;
	private String content;

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

}
