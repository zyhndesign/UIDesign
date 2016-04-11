package com.cidic.design.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.VideoCourseTagDao;
import com.cidic.design.model.VideoCourse;
import com.cidic.design.model.VideoCourseTag;
import com.cidic.design.service.VideoCourseTagService;

@Service
@Component
@Qualifier(value = "videoCourseTagServiceImpl")
@Transactional
public class VideoCourseTagServiceImpl implements VideoCourseTagService {

	private static final Logger logger = LoggerFactory.getLogger(VideoCourseTagServiceImpl.class);
	
	@Autowired
	@Qualifier(value="videoCourseTagDaoImpl")
	private VideoCourseTagDao videoCourseTagDao;
	
	@Override
	public void insertVideoCourseTagDao(List<VideoCourseTag> list) {
		
	}

	@Override
	public void deleteVideoCourseTag(int videoCourseId, int tagId) {
		videoCourseTagDao.deleteVideoCourseTag(videoCourseId, tagId);
	}

	@Override
	public void updateVideoCourseTag(VideoCourseTag videoCourseTag, int videoCourseTagId) {
		videoCourseTagDao.updateVideoCourseTag(videoCourseTag, videoCourseTagId);
	}

	@Override
	public List<VideoCourse> getVideoCourseByTagName(List<String> tagName) {
		return videoCourseTagDao.getVideoCourseByTagName(tagName);
	}

}
