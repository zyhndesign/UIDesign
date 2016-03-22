package com.cidic.design.dao;

import com.cidic.design.model.Tag;

public interface TagDao {
	
	public void insertTag(Tag tag);
	
	public Tag selectTagById(int id);
}
