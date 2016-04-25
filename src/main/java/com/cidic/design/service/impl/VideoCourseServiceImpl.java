package com.cidic.design.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.cidic.design.dao.VideoCourseTagDao;
import com.cidic.design.model.Courseware;
import com.cidic.design.model.CoursewarePageModel;
import com.cidic.design.model.Tag;
import com.cidic.design.model.VideoCourse;
import com.cidic.design.model.VideoCoursePageModel;
import com.cidic.design.model.VideoCourseTag;
import com.cidic.design.service.VideoCourseService;

@Service
@Component
@Qualifier(value = "videoCourseServiceImpl")
@Transactional
public class VideoCourseServiceImpl implements VideoCourseService {

	private static final Logger logger = LoggerFactory.getLogger(VideoCourseServiceImpl.class);

	@Autowired
	@Qualifier(value = "videoCourseDaoImpl")
	private VideoCourseDao videoCourseDao;

	@Autowired
	@Qualifier(value = "videoCourseTagDaoImpl")
	private VideoCourseTagDao videoCourseTagDao;

	@Autowired
	@Qualifier(value = "tagDaoImpl")
	private TagDao tagDao;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertVideoCourse(VideoCourse videoCourse, String insertTag) {
		if (insertTag.length() > 0) {
			String[] insertTags = insertTag.split(",");

			// insert tag into tag table first:check tag is in the table,
			// second:if
			// tag name in the table then get id,
			List<Tag> tagList = tagDao.selectTagByTagNameList(Arrays.asList(insertTags));
			// otherwise insert the name into table and get id
			for (String str : insertTags) {
				boolean hasTagInTable = false;
				for (Tag tag : tagList) {
					if (str.equals(tag.getTagName())) {
						hasTagInTable = true;
					}
				}
				if (!hasTagInTable) {
					Tag tag = new Tag();
					tag.setTagName(str);
					int tagId = tagDao.insertTag(tag);
					tag.setId(tagId);
					tagList.add(tag);
				}
			}
			List<VideoCourseTag> videoCourseTagList = new ArrayList<VideoCourseTag>();
			VideoCourseTag videoCourseTag = null;
			for (Tag tag : tagList) {
				videoCourseTag = new VideoCourseTag();
				videoCourseTag.setVideoCourse(videoCourse);
				videoCourseTag.setTag(tag);
				videoCourseTagList.add(videoCourseTag);
			}
			videoCourse.setVideoCourseTagList(videoCourseTagList);
		}
		videoCourseDao.insertVideoCourse(videoCourse);
	}

	@Override
	@Transactional(readOnly = true)
	public VideoCourse selectVideoCourse(int id) {
		
		return videoCourseDao.selectVideoCourse(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateVideoCourse(VideoCourse videoCourse, String updateTag, String deleteTag) {
		// delete tag in course design tag table
		if (deleteTag.length() > 0) {
			String[] deleteTagStr = deleteTag.split(",");
			for (String s : deleteTagStr) {
				videoCourseTagDao.deleteVideoCourseTag(videoCourse.getId(), Integer.parseInt(s));
			}
		}
		if (updateTag.length() > 0) {
			String[] insertTags = updateTag.split(",");

			// insert tag into tag table first:check tag is in the table,
			// second:if tag name in the table then get id,
			List<Tag> tagList = tagDao.selectTagByTagNameList(Arrays.asList(updateTag));
			// otherwise insert the name into table and get id
			for (String str : insertTags) {
				boolean hasTagInTable = false;
				for (Tag tag : tagList) {
					if (str.equals(tag.getTagName())) {
						hasTagInTable = true;
					}
				}
				if (!hasTagInTable) {
					Tag tag = new Tag();
					tag.setTagName(str);
					int tagId = tagDao.insertTag(tag);
					tag.setId(tagId);
					tagList.add(tag);
				}
			}
			List<VideoCourseTag> videoCourseTagList = new ArrayList<VideoCourseTag>();
			VideoCourseTag videoCourseTag = null;
			for (Tag tag : tagList) {
				videoCourseTag = new VideoCourseTag();
				videoCourseTag.setVideoCourse(videoCourse);
				videoCourseTag.setTag(tag);
				videoCourseTagList.add(videoCourseTag);
			}
			videoCourse.setVideoCourseTagList(videoCourseTagList);
		}
		videoCourseDao.updateVideoCourse(videoCourse);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteVideoCourse(VideoCourse videoCourse) {
		videoCourseDao.deleteVideoCourse(videoCourse);
	}

	@Override
	@Transactional(readOnly = true)
	public List<VideoCourse> getTopVideoCourse() {
		// TODO Auto-generated method stub
		return videoCourseDao.getTopVideoCourse();
	}

	@Override
	public VideoCoursePageModel getDataByPage(int limit, int offset, String sEcho) {
		VideoCoursePageModel videoCoursePageModel = new VideoCoursePageModel();
		List<VideoCourse> list = videoCourseDao.getDataByPage(limit, offset, sEcho);
		int count = videoCourseDao.getCountData();
		videoCoursePageModel.setList(list);
		videoCoursePageModel.setCount(count);
		return videoCoursePageModel;
	}

	@Override
	public List<VideoCourse> getFrontDataByPage(int limit, int offset, int choice) {
		// TODO Auto-generated method stub
		return videoCourseDao.getFrontDataByPage(limit, offset, choice);
	}

}
