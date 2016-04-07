package com.cidic.design.controllers;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.ModelAndView;

import com.cidic.design.exception.UIDesignException;
import com.cidic.design.model.Courseware;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.CoursewareService;

@Controller
@RequestMapping("/courseware")
public class CoursewareController {
	
	private static final Logger logger = LoggerFactory.getLogger(CoursewareController.class);
	
	@Autowired
	@Qualifier(value="coursewareServiceImpl")
	private CoursewareService coursewareServiceImpl;
	
	private ResultModel resultModel = null;
	
	@ExceptionHandler(UIDesignException.class)
	public @ResponseBody ResultModel handleCustomException(UIDesignException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getMessage());
		return resultModel;
	}
	
	@RequestMapping(value = "/courseWareCOR", method = RequestMethod.GET)
	public ModelAndView getCourseWareCOR(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/courseWareCOR");
		return view;
	}
	
	@RequestMapping(value = "/courseWareMgr", method = RequestMethod.GET)
	public ModelAndView getCourseWareMgr(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/courseWareMgr");
		return view;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces="application/json")  
	@ResponseBody 
	public ResultModel insertCourseware(@RequestBody Courseware courseware){
		
		try{
			coursewareServiceImpl.insertCourseware(courseware);
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
	public ResultModel selectCourseware(@PathVariable int id) throws Exception{
		Courseware courseware = null;
		try{
			courseware = coursewareServiceImpl.selectCourseware(id);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
			resultModel.setObject(courseware);
		}
		catch(Exception e){
			throw new UIDesignException(500, "获取数据出错");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces="application/json")  
	@ResponseBody 
	public ResultModel updateCourseware(@RequestBody Courseware courseware,@PathVariable int id){
		
		try{
			coursewareServiceImpl.updateCourseware(courseware);
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
	public ResultModel deleteCourseware(@PathVariable int id){
		Courseware courseware = new Courseware();
		courseware.setId(id);
		try{
			coursewareServiceImpl.deleteCourseware(courseware);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
		}
		catch(Exception e){
			throw new UIDesignException(500, "删除数据出错");
		}
		return resultModel;
	}
}
