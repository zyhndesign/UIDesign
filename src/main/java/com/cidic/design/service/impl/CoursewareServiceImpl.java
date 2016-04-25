package com.cidic.design.service.impl;

import java.text.ParseException;
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

import com.cidic.design.dao.CoursewareDao;
import com.cidic.design.dao.CoursewareTagDao;
import com.cidic.design.dao.TagDao;
import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.CourseDesignTag;
import com.cidic.design.model.CoursePageModel;
import com.cidic.design.model.Courseware;
import com.cidic.design.model.CoursewarePageModel;
import com.cidic.design.model.CoursewareTag;
import com.cidic.design.model.Tag;
import com.cidic.design.service.CoursewareService;
import com.cidic.design.util.DateUtil;

@Service
@Component
@Qualifier(value = "courseWareServiceImpl")
@Transactional
public class CoursewareServiceImpl implements CoursewareService {

	private static final Logger logger = LoggerFactory.getLogger(CoursewareServiceImpl.class);

	@Autowired
	@Qualifier(value = "coursewareDaoImpl")
	private CoursewareDao coursewareDao;

	@Autowired
	@Qualifier(value = "coursewareTagDaoImpl")
	private CoursewareTagDao coursewareTagDaoImpl;

	@Autowired
	@Qualifier(value = "tagDaoImpl")
	private TagDao tagDao;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertCourseware(Courseware courseware, String insertTag) {
		if (insertTag.length() > 0) {
			String[] insertTags = insertTag.split(",");

			// insert tag into tag table first:check tag is in the table,
			// second:if tag name in the table then get id,
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
			List<CoursewareTag> coursewareTagList = new ArrayList<CoursewareTag>();
			CoursewareTag coursewareTag = null;
			for (Tag tag : tagList) {
				coursewareTag = new CoursewareTag();
				coursewareTag.setCourseware(courseware);
				coursewareTag.setTag(tag);
				coursewareTagList.add(coursewareTag);
			}
			courseware.setCoursewareTagList(coursewareTagList);
		}
		coursewareDao.insertCourseware(courseware);
	}

	@Override
	@Transactional(readOnly = true)
	public Courseware selectCourseware(int id) {
		return coursewareDao.selectCourseware(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateCourseware(Courseware courseware, String updateTag, String deleteTag) {
		// delete tag in course design tag table
		if (deleteTag.length() > 0) {
			String[] deleteTagStr = deleteTag.split(",");
			for (String s : deleteTagStr) {
				coursewareTagDaoImpl.deleteCoursewareTag(courseware.getId(), Integer.parseInt(s));
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
			List<CoursewareTag> coursewareTagList = new ArrayList<CoursewareTag>();
			CoursewareTag coursewareTag = null;
			for (Tag tag : tagList) {
				coursewareTag = new CoursewareTag();
				coursewareTag.setCourseware(courseware);
				coursewareTag.setTag(tag);
				coursewareTagList.add(coursewareTag);
			}
			courseware.setCoursewareTagList(coursewareTagList);
		}
		coursewareDao.updateCourseware(courseware);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteCourseware(Courseware courseware) {
		coursewareDao.deleteCourseware(courseware);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Courseware> getTopCourseware() {
		return coursewareDao.getTopCourseware();
	}

	@Override
	@Transactional(readOnly = true)
	public CoursewarePageModel getDataByPage(int limit, int offset, String sEcho) {
		CoursewarePageModel coursewarePageModel = new CoursewarePageModel();
		List<Courseware> list = coursewareDao.getDataByPage(limit, offset, sEcho);
		int count = coursewareDao.getCountData();
		coursewarePageModel.setList(list);
		coursewarePageModel.setCount(count);
		return coursewarePageModel;
	}

	@Override
	public List<Courseware> getFrontDataByPage(int limit, int offset, int choice) {
		// TODO Auto-generated method stub
		return coursewareDao.getFrontDataByPage(limit, offset, choice);
	}

}
