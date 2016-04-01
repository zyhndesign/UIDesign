package com.cidic.design.dao;

import com.cidic.design.model.Tag;

public interface TagDao {
	
	public int insertTag(Tag tag);
	
	public Tag selectTagById(int id);
	
	public Tag selectTagByTagName(String tagName);
}
