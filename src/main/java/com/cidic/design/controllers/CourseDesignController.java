package com.cidic.design.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.exception.UIDesignException;
import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.CourseDesignService;

@Controller
public class CourseDesignController {
	
	private static final Logger logger = LoggerFactory.getLogger(CourseDesignController.class);
	
	@Autowired
	@Qualifier(value="courseDesignServiceImpl")
	private CourseDesignService courseDesignServiceImpl;
	
	private ResultModel resultModel = null;
	
	@ExceptionHandler(UIDesignException.class)
	public @ResponseBody ResultModel handleCustomException(UIDesignException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getMessage());
		return resultModel;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")  
	@ResponseBody 
	public ResultModel selectCourseDesign(@PathVariable int id) throws Exception{
		CourseDesign courseDesign = null;
		try{
			courseDesign = courseDesignServiceImpl.selectCourseDesign(id);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
			resultModel.setObject(courseDesign);
		}
		catch(Exception e){
			throw new UIDesignException(500, "²éÕÒÊ§°Ü£¡");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces="application/json")  
	@ResponseBody 
	public ResultModel updateCourseDesign(@RequestBody CourseDesign courseDesign,@PathVariable int id){
		
		try{
			courseDesignServiceImpl.updateCourseDesign(courseDesign);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
		}
		catch(Exception e){
			throw new UIDesignException(500, "¸üÐÂÊ§°Ü£¡");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces="application/json")  
	@ResponseBody 
	public ResultModel deleteCourseDesign(@PathVariable int id){
		CourseDesign courseDesign = new CourseDesign();
		courseDesign.setId(id);
		try{
			courseDesignServiceImpl.deleteCourseDesign(courseDesign);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
		}
		catch(Exception e){
			throw new UIDesignException(500, "É¾³ýÊ§°Ü£¡");
		}
		return resultModel;
	}
}
