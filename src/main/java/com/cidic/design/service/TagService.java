package com.cidic.design.service;

import com.cidic.design.model.Tag;

public interface TagService {
	public void insertTag(Tag tag);
	
	public Tag selectTagById(int id);
	
	public Tag selectTagByTagName(String tagName);
}