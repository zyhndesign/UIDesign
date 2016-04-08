package com.cidic.design;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.model.CourseDesignTag;
import com.cidic.design.service.CourseDesignTagService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CourseDesignTagServiceTest {

	@Autowired
	@Qualifier(value="courseDesignTagServiceImpl")
    private CourseDesignTagService courseDesignTagService; 
	
	//@Test
	public void testDeleteCourseDesignTag()
	{
		courseDesignTagService.deleteCourseDesignTag(1, 1);
	}
	
	@Test
	public void testGetDataByTagName()
	{
		List<String> list = new ArrayList<String>();
		list.add("字体");
		list.add("视觉");
		List<CourseDesignTag> listCourseDesignTag= courseDesignTagService.getCourseDesignByTagName(list);
		for (CourseDesignTag cdTag : listCourseDesignTag){
			System.out.println(cdTag.getCourseDesign().getAbstract_());
		}
	}
}
