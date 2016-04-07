package com.cidic.design.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.TagDao;
import com.cidic.design.dao.VideoCourseDao;
import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.VideoCourse;
import com.cidic.design.service.VideoCourseService;

@Service
@Component
@Qualifier(value = "videoCourseServiceImpl")
@Transactional
public class VideoCourseServiceImpl implements VideoCourseService {

	private static final Logger logger = LoggerFactory.getLogger(VideoCourseServiceImpl.class);
	
	@Autowired
	@Qualifier(value="videoCourseDaoImpl")
	private VideoCourseDao videoCourseDao;
	
	@Autowired
	@Qualifier(value="tagDaoImpl")
	private TagDao tagDao;
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertVideoCourse(VideoCourse videoCourse) {
		videoCourseDao.insertVideoCourse(videoCourse);
	}

	@Override
	@Transactional (readOnly = true)
	public VideoCourse selectVideoCourse(int id) {
		return videoCourseDao.selectVideoCourse(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateVideoCourse(VideoCourse videoCourse) {
		videoCourseDao.updateVideoCourse(videoCourse);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteVideoCourse(VideoCourse videoCourse) {
		videoCourseDao.deleteVideoCourse(videoCourse);
	}

	@Override
	@Transactional (readOnly = true)
	public List<VideoCourse> getTopVideoCourse() {
		// TODO Auto-generated method stub
		return videoCourseDao.getTopVideoCourse();
	}

	@Override
	public List<VideoCourse> getDataByPage(int limit, int offset, String sEcho) {
		// TODO Auto-generated method stub
		return videoCourseDao.getDataByPage(limit, offset, sEcho);
	}

}
