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
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.VideoCourse;
import com.cidic.design.service.VideoCourseService;

@Controller
@RequestMapping("/videocourse")
public class VideoCourseController {

	private static final Logger logger = LoggerFactory.getLogger(VideoCourseController.class);
	
	@Autowired
	@Qualifier(value="videoCourseServiceImpl")
	private VideoCourseService videoCourseServiceImpl;
	
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
	public ResultModel selectCourseware(@PathVariable int id) throws Exception{
		VideoCourse videoCourse = null;
		try{
			videoCourse = videoCourseServiceImpl.selectVideoCourse(id);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
			resultModel.setObject(videoCourse);
		}
		catch(Exception e){
			throw new UIDesignException(500, "²éÕÒÊ§°Ü£¡");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces="application/json")  
	@ResponseBody 
	public ResultModel updateCourseware(@RequestBody VideoCourse videoCourse,@PathVariable int id){
		
		try{
			videoCourseServiceImpl.updateVideoCourse(videoCourse);
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
	public ResultModel deleteCourseware(@PathVariable int id){
		VideoCourse videoCourse = new VideoCourse();
		videoCourse.setId(id);
		try{
			videoCourseServiceImpl.deleteVideoCourse(videoCourse);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
		}
		catch(Exception e){
			throw new UIDesignException(500, "É¾³ýÊ§°Ü£¡");
		}
		return resultModel;
	}
}
