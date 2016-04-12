package com.cidic.design.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import com.cidic.design.exception.UIDesignException;
import com.cidic.design.model.Courseware;
import com.cidic.design.model.ListResultModel;
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.VideoCourse;
import com.cidic.design.service.VideoCourseService;
import com.cidic.design.util.DateUtil;

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
		resultModel.setMessage(ex.getErrMsg());
		return resultModel;
	}
	
	@RequestMapping(value = {"/videoCourseCOR"}, method = RequestMethod.GET)
	public ModelAndView getVideoCourseCOR(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/courseVideoCOR");
		return view;
	}
	
	@RequestMapping(value = {"/videoCourseCOR/{id}"}, method = RequestMethod.GET)
	public ModelAndView getVideoCourseCOR(HttpServletRequest request,@PathVariable int id) {
		VideoCourse videoCourse = null;
		if (id > 0){
			videoCourse = videoCourseServiceImpl.selectVideoCourse(id);
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/courseVideoCOR");
		view.addObject("videoCourse", videoCourse);
		
		return view;
	}
	
	@RequestMapping(value = "/videoCourseMgr", method = RequestMethod.GET)
	public ModelAndView getCourseWareMgr(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/courseVideoMgr");
		return view;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces="application/json")  
	@ResponseBody 
	public ResultModel insertVideoCourse(@RequestParam String title, @RequestParam String abstract_,@RequestParam(required=false) String duration,
			@RequestParam String thumbnail,@RequestParam String createTime,@RequestParam String content,
			@RequestParam int topTag , @RequestParam String insertTag,@RequestParam String author){
		VideoCourse videoCourse = new VideoCourse();
		videoCourse.setTitle(title);
		videoCourse.setAbstract_(abstract_);
		videoCourse.setContent(content);
		videoCourse.setCreateTime(DateUtil.stringToDate(createTime));
		videoCourse.setDuration(duration);
		videoCourse.setThumbnail(thumbnail);
		videoCourse.setTopTag(topTag);
		videoCourse.setAuthor(author);
		try{
			videoCourseServiceImpl.insertVideoCourse(videoCourse,insertTag);
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
	public ResultModel selectVideoCourse(HttpServletRequest request,HttpServletResponse response,@PathVariable int id) throws Exception{
		VideoCourse videoCourse = null;
		try{
			response.setContentType("text/html;charset=UTF-8");
			response.addHeader("Access-Control-Allow-Origin","*");
		    if("IE".equals(request.getParameter("type"))){
		    	response.addHeader("XDomainRequestAllowed","1");
		    }
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
	public ResultModel updateVideoCourse(@RequestParam String title, @RequestParam String abstract_,@RequestParam(required=false) String duration,
			@RequestParam String thumbnail,@RequestParam String createTime,@RequestParam String insertTag,@RequestParam String deleteTag,
			@RequestParam String content,@RequestParam int topTag, @PathVariable int id,@RequestParam String author){
		VideoCourse videoCourse = new VideoCourse();
		videoCourse.setId(id);
		videoCourse.setTitle(title);
		videoCourse.setAbstract_(abstract_);
		videoCourse.setContent(content);
		videoCourse.setCreateTime(DateUtil.stringToDate(createTime));
		videoCourse.setDuration(duration);
		videoCourse.setThumbnail(thumbnail);
		videoCourse.setTopTag(topTag);
		videoCourse.setAuthor(author);
		try{
			videoCourseServiceImpl.updateVideoCourse(videoCourse,insertTag,deleteTag);
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
	public ListResultModel listVideoCourse(@RequestParam int iDisplayLength, @RequestParam int iDisplayStart,@RequestParam String sEcho){
		ListResultModel listResultModel = new ListResultModel();
		try{
			List<VideoCourse> list = videoCourseServiceImpl.getDataByPage(iDisplayLength, iDisplayStart, sEcho);
			listResultModel.setAaData(list);
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(iDisplayLength);
			listResultModel.setiTotalDisplayRecords(iDisplayStart + iDisplayLength);
			listResultModel.setSuccess(true);
		}
		catch(Exception e){
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	@RequestMapping(value = "/frontList", method = RequestMethod.GET, produces="application/json")  
	@ResponseBody 
	public ListResultModel frontListVideoCourse(HttpServletRequest request,HttpServletResponse response,@RequestParam int limit, @RequestParam int offset,@RequestParam int choice){
		ListResultModel listResultModel = new ListResultModel();
		try{
			response.setContentType("text/html;charset=UTF-8");
			response.addHeader("Access-Control-Allow-Origin","*");
		    if("IE".equals(request.getParameter("type"))){
		    	response.addHeader("XDomainRequestAllowed","1");
		    }
			List<VideoCourse> list = videoCourseServiceImpl.getFrontDataByPage(limit, offset, choice);
			listResultModel.setAaData(list);
			listResultModel.setiTotalRecords(list.size());
			listResultModel.setiTotalDisplayRecords(offset + list.size());
			listResultModel.setSuccess(true);
		}
		catch(Exception e){
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
}
