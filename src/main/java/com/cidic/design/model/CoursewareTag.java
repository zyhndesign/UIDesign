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
@Table(name="courseware_tag")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CoursewareTag implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	private Courseware courseware;
	
	@OneToOne
	private Tag tag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonBackReference
	public Courseware getCourseware() {
		return courseware;
	}

	@JsonBackReference
	public void setCourseware(Courseware courseware) {
		this.courseware = courseware;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	
}
