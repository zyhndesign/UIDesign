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

import com.cidic.design.dao.CourseDesignDao;
import com.cidic.design.dao.CourseDesignTagDao;
import com.cidic.design.dao.TagDao;
import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.CourseDesignTag;
import com.cidic.design.model.CoursePageModel;
import com.cidic.design.model.Tag;
import com.cidic.design.service.CourseDesignService;
import com.cidic.design.util.DateUtil;

@Service
@Component
@Qualifier(value = "courseDesignServiceImpl")
@Transactional
public class CourseDesignServiceImpl implements CourseDesignService {

	private static final Logger logger = LoggerFactory.getLogger(CourseDesignServiceImpl.class);

	@Autowired
	@Qualifier(value = "courseDesignDaoImpl")
	private CourseDesignDao courseDesignDao;

	@Autowired
	@Qualifier(value = "courseDesignTagDaoImpl")
	private CourseDesignTagDao courseDesignTagDaoImpl;

	@Autowired
	@Qualifier(value = "tagDaoImpl")
	private TagDao tagDao;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertCourseDesign(CourseDesign courseDesign, String insertTag) {
		if (insertTag.length() > 0){
			String[] insertTags = insertTag.split(",");

			// insert tag into tag table first:check tag is in the table, second:if
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
			List<CourseDesignTag> courseTagList = new ArrayList<CourseDesignTag>();
			CourseDesignTag courseDesignTag = null;
			for (Tag tag : tagList) {
				courseDesignTag = new CourseDesignTag();
				courseDesignTag.setCourseDesign(courseDesign);
				courseDesignTag.setTag(tag);
				courseTagList.add(courseDesignTag);
			}
			courseDesign.setCourseTagList(courseTagList);
		}
		
		courseDesignDao.insertCourseDesign(courseDesign);
	}

	@Override
	@Transactional(readOnly = true)
	public CourseDesign selectCourseDesign(int id) {
		return  courseDesignDao.selectCourseDesign(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateCourseDesign(CourseDesign courseDesign, String updateTag, String deleteTag) {
		// delete tag in course design tag table
		if (deleteTag.length() > 0) {
			String[] deleteTagStr = deleteTag.split(",");
			for (String s : deleteTagStr) {
				courseDesignTagDaoImpl.deleteCourseDesignTag(courseDesign.getId(), Integer.parseInt(s));
			}
		}
		// insert tag into tag table first:check tag is in the table, second:if
		// tag name in the table then get id,
		// otherwise insert the name into table and get id
		if (updateTag.length() > 0) {
			String[] updateTags = updateTag.split(",");

			// insert tag into tag table first:check tag is in the table,
			// second:if tag name in the table then get id,
			List<Tag> tagList = tagDao.selectTagByTagNameList(Arrays.asList(updateTags));
			// otherwise insert the name into table and get id
			for (String str : updateTags) {
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
			List<CourseDesignTag> courseTagList = new ArrayList<CourseDesignTag>();
			CourseDesignTag courseDesignTag = null;
			for (Tag tag : tagList) {
				courseDesignTag = new CourseDesignTag();
				courseDesignTag.setCourseDesign(courseDesign);
				courseDesignTag.setTag(tag);
				courseTagList.add(courseDesignTag);
			}
			courseDesign.setCourseTagList(courseTagList);
		}

		courseDesignDao.updateCourseDesign(courseDesign);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteCourseDesign(CourseDesign courseDesign) {
		// TODO Auto-generated method stub
		courseDesignDao.deleteCourseDesign(courseDesign);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseDesign> getTopCourseDesign() {
		// TODO Auto-generated method stub
		return courseDesignDao.getTopCourseware();
	}

	@Override
	@Transactional(readOnly = true)
	public CoursePageModel getDataByPage(int limit, int offset, String sEcho) {
		CoursePageModel coursePageModel = new CoursePageModel();
		List<CourseDesign> list = courseDesignDao.getDataByPage(limit, offset, sEcho);
		int count = courseDesignDao.getCountData();
		coursePageModel.setList(list);
		coursePageModel.setCount(count);
		return coursePageModel;
	}

	@Override
	public List<CourseDesign> getFrontDataByPage(int limit, int offset, int choice) {
		// TODO Auto-generated method stub
		return courseDesignDao.getFrontDataByPage(limit, offset, choice);
	}

}
