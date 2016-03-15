package com.cidic.design;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserServiceTest {
	
	@Autowired
	@Qualifier(value="userServiceImpl")
    private UserService userServiceImpl;  
     
    @Test
    public  void testSave(){
    	userServiceImpl.insertUser();
    }
}
