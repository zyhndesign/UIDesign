package com.cidic.design;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.model.Tag;
import com.cidic.design.service.TagService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TagServiceTest {

	@Autowired
	@Qualifier(value="tagServiceImpl")
    private TagService tagService; 
	
	@Test
	public void testInsert(){
		Tag tag = new Tag();
		tag.setTagName("UI…Ëº∆");
		tagService.insertTag(tag);
	}
	
	public void testSelect(){
		
	}
}
