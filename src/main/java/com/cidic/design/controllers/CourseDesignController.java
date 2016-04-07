package com.cidic.design.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.exception.UIDesignException;
import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.CourseDesignService;

@Controller
@RequestMapping("/coursedesign")
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
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)  
	@ResponseBody 
	public ResultModel insertCourseDesign(@RequestParam String title, @RequestParam String abstract_,@RequestParam String teacher){
		
		logger.info("/coursedesign/insert/"+title);
		CourseDesign courseDesign;
		try{
			courseDesign = new CourseDesign();
			courseDesign.setAbstract_(abstract_);
			courseDesign.setTeacher(teacher);
			courseDesign.setTitle(title);
			courseDesignServiceImpl.insertCourseDesign(courseDesign);
			
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
			
		}
		catch(Exception e){
			throw new UIDesignException(500, "写入数据出错");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET, produces="application/json")  
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
			throw new UIDesignException(500, "获取数据出错");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)  
	@ResponseBody 
	public ResultModel updateCourseDesign(@PathVariable int id, @RequestParam String title, @RequestParam String abstract_,@RequestParam String teacher){
		
		logger.info("/coursedesign/update/"+id +" "+title);
		CourseDesign courseDesign;
		try{
			courseDesign = new CourseDesign();
			courseDesign.setId(id);
			courseDesign.setAbstract_(abstract_);
			courseDesign.setTeacher(teacher);
			courseDesign.setTitle(title);
			courseDesignServiceImpl.updateCourseDesign(courseDesign);
			
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
			
		}
		catch(Exception e){
			throw new UIDesignException(500, "更新数据出错");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces="application/json")  
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
			throw new UIDesignException(500, "删除数据出错");
		}
		return resultModel;
	}
}
