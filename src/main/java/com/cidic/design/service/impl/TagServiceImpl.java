package com.cidic.design.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.TagDao;
import com.cidic.design.model.Tag;
import com.cidic.design.service.TagService;

@Service
@Component
@Qualifier(value = "tagServiceImpl")
@Transactional
public class TagServiceImpl implements TagService {

	private static final Logger logger = LoggerFactory.getLogger(CourseDesignServiceImpl.class);
	
	@Autowired
	@Qualifier(value="tagDaoImpl")
	private TagDao tagDao;
	
	@Override
	@Transactional (readOnly = false)
	public void insertTag(Tag tag) {
		tagDao.insertTag(tag);
	}

	@Override
	@Transactional (readOnly = true)
	public Tag selectTagById(int id) {
		
		return tagDao.selectTagById(id);
	}

	@Override
	@Transactional (readOnly = true)
	public Tag selectTagByTagName(String tagName) {
		return tagDao.selectTagByTagName(tagName);
	}

}