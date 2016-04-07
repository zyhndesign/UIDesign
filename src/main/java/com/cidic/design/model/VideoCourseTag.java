package com.cidic.design.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="video_course_tag")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VideoCourseTag implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	private VideoCourse videoCourse;
	
	@OneToOne
	private Tag tag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonBackReference
	public VideoCourse getVideoCourse() {
		return videoCourse;
	}

	@JsonBackReference
	public void setVideoCourse(VideoCourse videoCourse) {
		this.videoCourse = videoCourse;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	
}
