package com.cidic.design;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.CourseDesignTag;
import com.cidic.design.model.CoursePageModel;
import com.cidic.design.service.CourseDesignService;

import static java.lang.System.out;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CourseDesignServiceTest {

	@Autowired
	@Qualifier(value="courseDesignServiceImpl")
    private CourseDesignService courseDesignService;  
     
   // @Test
    public  void testInsertCourseDesign(){
    	
    	CourseDesign courseDesign = new CourseDesign();
    	courseDesign.setTitle("文字设计与编排");
    	courseDesign.setAbstract_("舍得茶舍logo设计");
    	courseDesign.setTeacher("皇家马德里");
    	courseDesign.setCreateTime(new Date());
    	courseDesign.setCourseDetailId(15);
    	
    	String insertTag = "字体,标识,文字";
    	courseDesignService.insertCourseDesign(courseDesign,insertTag);
    	
    }
    
    @Test
    public  void testSelectCourseDesign(){
    	
    	CourseDesign courseDesign1 = courseDesignService.selectCourseDesign(2);
    	out.println(courseDesign1.getCreateTime());
    	
    	List<CourseDesignTag> list = courseDesign1.getCourseTagList();

    	for (CourseDesignTag courseDesignTag:list){
    		out.println(courseDesignTag.getTag().getTagName());
    	}
    	
    }
    
    //@Test
    public void testDeleteTagRelation(){
    	
    	CourseDesign courseDesign = new CourseDesign();
    	courseDesign.setId(5);
    	courseDesignService.deleteCourseDesign(courseDesign);
    	
    }
    
    //@Test
    public void testUpdateRelation(){
    	CourseDesign courseDesign = new CourseDesign();
    	courseDesign.setTitle("图形用户界面设计与模式-图标设计--测试");
    	courseDesign.setAbstract_("设计研究基础/工业设计史/文字与编排/图形用户界面设计与模式/图钉墙");
    	courseDesign.setTeacher("国际米兰，利物浦");
    	courseDesign.setCreateTime(new Date());
    	courseDesign.setCourseDetailId(19);
    	courseDesign.setId(7);
    	
    	String deleteTag = "12";
    	
    	String updateTag = "";
    	
    	
    	courseDesignService.updateCourseDesign(courseDesign,updateTag,deleteTag);
    }
    
   // @Test
    public void listCourseDesign(){
    	CoursePageModel coursePageModel = courseDesignService.getDataByPage(10, 0, "");
    	List<CourseDesign> list = coursePageModel.getList();
    	out.println("list size is :"+list.size());
    	for (CourseDesign courseDesign : list){
    		out.println(courseDesign.getTitle());
			List<CourseDesignTag> tagList = courseDesign.getCourseTagList();
			for (CourseDesignTag tag : tagList){
				out.println("====:"+tag.getTag().getTagName());
			}
		}
    }
}
