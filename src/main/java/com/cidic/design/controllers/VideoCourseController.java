package com.cidic.design.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.design.exception.UIDesignException;
import com.cidic.design.model.Courseware;
import com.cidic.design.model.ListResultModel;
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
	private static final String REQUEST_RESULT_MESSAGE = "操作成功！";
	
	@ExceptionHandler(UIDesignException.class)
	public @ResponseBody ResultModel handleCustomException(UIDesignException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getMessage());
		return resultModel;
	}
	
	@RequestMapping(value = {"/videoCourseCOR","/videoCourseCOR/{id}"}, method = RequestMethod.GET)
	public ModelAndView getVideoCourseCOR(HttpServletRequest request,@PathVariable int id) {
		VideoCourse videoCourse = null;
		if (id > 0){
			videoCourse = videoCourseServiceImpl.selectVideoCourse(id);
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/videoCourseCOR");
		view.addObject("videoCourse", videoCourse);
		
		return view;
	}
	
	@RequestMapping(value = "/videoCourseMgr", method = RequestMethod.GET)
	public ModelAndView getCourseWareMgr(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/videoCourseMgr");
		return view;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces="application/json")  
	@ResponseBody 
	public ResultModel insertVideoCourse(@RequestBody VideoCourse videoCourse){
		
		try{
			videoCourseServiceImpl.insertVideoCourse(videoCourse);
			resultModel = new ResultModel();
			resultModel.setMessage(REQUEST_RESULT_MESSAGE);
			resultModel.setResultCode(200);
		}
		catch(Exception e){
			throw new UIDesignException(500, "写入数据出错");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET, produces="application/json")  
	@ResponseBody 
	public ResultModel selectVideoCourse(@PathVariable int id) throws Exception{
		VideoCourse videoCourse = null;
		try{
			videoCourse = videoCourseServiceImpl.selectVideoCourse(id);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
			resultModel.setMessage(REQUEST_RESULT_MESSAGE);
			resultModel.setObject(videoCourse);
		}
		catch(Exception e){
			throw new UIDesignException(500, "查找数据出错");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces="application/json")  
	@ResponseBody 
	public ResultModel updateVideoCourse(@RequestBody VideoCourse videoCourse,@PathVariable int id){
		
		try{
			videoCourseServiceImpl.updateVideoCourse(videoCourse);
			resultModel = new ResultModel();
			resultModel.setMessage(REQUEST_RESULT_MESSAGE);
			resultModel.setResultCode(200);
		}
		catch(Exception e){
			throw new UIDesignException(500, "更新数据出错");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces="application/json")  
	@ResponseBody 
	public ResultModel deleteVideoCourse(@PathVariable int id){
		VideoCourse videoCourse = new VideoCourse();
		videoCourse.setId(id);
		try{
			videoCourseServiceImpl.deleteVideoCourse(videoCourse);
			resultModel = new ResultModel();
			resultModel.setMessage(REQUEST_RESULT_MESSAGE);
			resultModel.setResultCode(200);
		}
		catch(Exception e){
			throw new UIDesignException(500, "删除数据出错");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")  
	@ResponseBody 
	public ListResultModel listVideoCourse( @RequestParam int limit, @RequestParam int offset,@RequestParam String sEcho){
		ListResultModel listResultModel = new ListResultModel();
		try{
			List<VideoCourse> list = videoCourseServiceImpl.getDataByPage(limit, offset, sEcho);
			listResultModel.setAaData(list);
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(limit);
			listResultModel.setiTotalDisplayRecords(offset + limit);
			listResultModel.setSuccess(true);
		}
		catch(Exception e){
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
}
