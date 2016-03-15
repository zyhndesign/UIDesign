package com.cidic.design.model;
// Generated 2016-3-14 16:01:31 by Hibernate Tools 4.3.1.Final

import java.util.Date;


public class Courseware implements java.io.Serializable {

	private Integer id;
	private String title;
	private String author;
	private String thumbnail;
	private Date createTime;
	private String content;

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

}
