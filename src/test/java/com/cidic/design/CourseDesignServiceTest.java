package com.cidic.design;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.CourseDesignTag;
import com.cidic.design.model.Tag;
import com.cidic.design.service.CourseDesignService;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CourseDesignServiceTest {

	@Autowired
	@Qualifier(value="courseDesignServiceImpl")
    private CourseDesignService courseDesignService;  
     
    @Test
    public  void testInsertCourseDesign(){
    	/*
    	CourseDesign courseDesign = new CourseDesign();
    	courseDesign.setTitle("服务设计课程作业2");
    	courseDesign.setAbstract_("服务设计");
    	courseDesign.setTeacher("王魏");
    	courseDesign.setCreateTime(new Date());
    	courseDesign.setCourseDetailId(1);
    	List<CourseDesignTag> courseTagList = new ArrayList<CourseDesignTag>();
    	CourseDesignTag courseDesignTag1 = new CourseDesignTag();
    	courseDesignTag1.setCourseDesign(courseDesign);
    	Tag tag1 = new Tag();
    	tag1.setId(3);
    	courseDesignTag1.setTag(tag1);
    	courseTagList.add(courseDesignTag1);
    	
    	CourseDesignTag courseDesignTag2 = new CourseDesignTag();
    	courseDesignTag2.setCourseDesign(courseDesign);
    	Tag tag2 = new Tag();
    	tag2.setId(1);
    	courseDesignTag2.setTag(tag2);
    	courseTagList.add(courseDesignTag2);
    	
    	CourseDesignTag courseDesignTag3 = new CourseDesignTag();
    	courseDesignTag3.setCourseDesign(courseDesign);
    	Tag tag3 = new Tag();
    	tag3.setId(4);
    	courseDesignTag3.setTag(tag3);
    	courseTagList.add(courseDesignTag3);
    	
    	courseDesign.setCourseTagList(courseTagList);
    	courseDesignService.insertCourseDesign(courseDesign);
    	*/
    }
    
    @Test
    public  void testSelectCourseDesign(){
    	/*
    	CourseDesign courseDesign1 = courseDesignService.selectCourseDesign(1);
    	out.println(courseDesign1.getTeacher());
    	
    	List<CourseDesignTag> list = courseDesign1.getCourseTagList();

    	for (CourseDesignTag courseDesignTag:list){
    		out.println(courseDesignTag.getTag().getTagName());
    	}
    	*/
    }
    
    @Test
    public void testDeleteTagRelation(){
    	
    	CourseDesign courseDesign = new CourseDesign();
    	courseDesign.setId(5);
    	courseDesignService.deleteCourseDesign(courseDesign);
    	
    }
    
   // @Test
    public void testUpdateRelation(){
    	CourseDesign courseDesign = new CourseDesign();
    	courseDesign.setTitle("服务设计课程作业666666");
    	courseDesign.setAbstract_("服务设计asdasdasd");
    	courseDesign.setTeacher("王魏老师");
    	courseDesign.setCreateTime(new Date());
    	courseDesign.setCourseDetailId(1);
    	courseDesign.setId(5);
    	List<CourseDesignTag> courseTagList = new ArrayList<CourseDesignTag>();
    	CourseDesignTag courseDesignTag1 = new CourseDesignTag();
    	courseDesignTag1.setCourseDesign(courseDesign);
    	Tag tag1 = new Tag();
    	tag1.setId(3);
    	courseDesignTag1.setTag(tag1);
    	courseTagList.add(courseDesignTag1);
    	
    	CourseDesignTag courseDesignTag2 = new CourseDesignTag();
    	courseDesignTag2.setCourseDesign(courseDesign);
    	Tag tag2 = new Tag();
    	tag2.setId(1);
    	courseDesignTag2.setTag(tag2);
    	courseTagList.add(courseDesignTag2);
    	
    	CourseDesignTag courseDesignTag3 = new CourseDesignTag();
    	courseDesignTag3.setCourseDesign(courseDesign);
    	Tag tag3 = new Tag();
    	tag3.setId(4);
    	courseDesignTag3.setTag(tag3);
    	courseTagList.add(courseDesignTag3);
    	
    	courseDesign.setCourseTagList(courseTagList);
    	
    	courseDesignService.updateCourseDesign(courseDesign);
    }
}
