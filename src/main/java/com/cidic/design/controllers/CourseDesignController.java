package com.cidic.design.controllers;

import java.util.Date;
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
import com.cidic.design.model.CourseDesign;
import com.cidic.design.model.CoursePageModel;
import com.cidic.design.model.ListResultModel;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.CourseDesignService;
import com.cidic.design.util.DateUtil;

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
		resultModel.setMessage(ex.getErrMsg());
		return resultModel;
	}
	
	@RequestMapping(value = {"/courseDesignCOR"}, method = RequestMethod.GET)
	public ModelAndView getCourseCORView(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/courseDesignCOR");
		return view;
	}
	
	@RequestMapping(value = {"/courseDesignCOR/{id}"}, method = RequestMethod.GET)
	public ModelAndView getCourseCOR(HttpServletRequest request,@PathVariable int id) {
		CourseDesign courseDesign = null;
		if (id > 0){
			courseDesign = courseDesignServiceImpl.selectCourseDesign(id);
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/courseDesignCOR");
		view.addObject("courseDesign", courseDesign);
		return view;
	}
	
	@RequestMapping(value = "/courseDesignMgr", method = RequestMethod.GET)
	public ModelAndView getCourseMgr(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/courseDesignMgr");
		return view;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)  
	@ResponseBody 
	public ResultModel insertCourseDesign(@RequestParam String title, @RequestParam String abstract_,
			@RequestParam String teacher,@RequestParam String createTime,@RequestParam int topTag,
			@RequestParam int courseDetailId,@RequestParam String insertTag,@RequestParam String bg,@RequestParam String thumbnail){
		
		logger.info("/coursedesign/insert/"+title);
		CourseDesign courseDesign;
		try{
			courseDesign = new CourseDesign();
			courseDesign.setAbstract_(abstract_);
			courseDesign.setTeacher(teacher);
			courseDesign.setTitle(title);
			if (createTime.equals("")){
				courseDesign.setCreateTime(new Date());
			}
			else{
				courseDesign.setCreateTime(DateUtil.stringToDate(createTime));
			}
			
			courseDesign.setTopTag(topTag);
			courseDesign.setCourseDetailId(courseDetailId);
			courseDesign.setBg(bg);
			courseDesign.setThumbnail(thumbnail);
			courseDesignServiceImpl.insertCourseDesign(courseDesign,insertTag);
			
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
	public ResultModel selectCourseDesign(HttpServletRequest request,HttpServletResponse response,@PathVariable int id) throws Exception{
		CourseDesign courseDesign = null;
		try{
			response.setContentType("text/html;charset=UTF-8");
			response.addHeader("Access-Control-Allow-Origin","*");
		    if("IE".equals(request.getParameter("type"))){
		    	response.addHeader("XDomainRequestAllowed","1");
		    }
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
	public ResultModel updateCourseDesign(@PathVariable int id, @RequestParam String title, @RequestParam String abstract_,
			@RequestParam String teacher,@RequestParam String createTime,@RequestParam int topTag,@RequestParam String bg,
			@RequestParam int courseDetailId,@RequestParam String insertTag,@RequestParam String deleteTag,@RequestParam String thumbnail){
		
		CourseDesign courseDesign;
		try{
			courseDesign = new CourseDesign();
			courseDesign.setId(id);
			courseDesign.setAbstract_(abstract_);
			courseDesign.setTeacher(teacher);
			courseDesign.setTitle(title);
			if (createTime.equals("")){
				courseDesign.setCreateTime(new Date());
			}
			else{
				courseDesign.setCreateTime(DateUtil.stringToDate(createTime));
			}
			courseDesign.setTopTag(topTag);
			courseDesign.setCourseDetailId(courseDetailId);
			courseDesign.setBg(bg);
			courseDesign.setThumbnail(thumbnail);
			courseDesignServiceImpl.updateCourseDesign(courseDesign,insertTag,deleteTag);
			
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
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")  
	@ResponseBody 
	public ListResultModel listCourseDesign(@RequestParam int iDisplayLength, @RequestParam int iDisplayStart,@RequestParam String sEcho){
		ListResultModel listResultModel = new ListResultModel();
		try{
			CoursePageModel coursePageModel = courseDesignServiceImpl.getDataByPage(iDisplayLength, iDisplayStart, sEcho);
			List<CourseDesign> list = coursePageModel.getList();
			listResultModel.setAaData(list);
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(coursePageModel.getCount());
			listResultModel.setiTotalDisplayRecords(coursePageModel.getCount());
			listResultModel.setSuccess(true);
		}
		catch(Exception e){
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	@RequestMapping(value = "/frontList", method = RequestMethod.GET, produces="application/json")  
	@ResponseBody 
	public ListResultModel frontListCourseDesign(HttpServletRequest request,HttpServletResponse response,@RequestParam int limit, @RequestParam int offset,@RequestParam int choice){
		ListResultModel listResultModel = new ListResultModel();
		try{
			response.setContentType("text/html;charset=UTF-8");
			response.addHeader("Access-Control-Allow-Origin","*");
		    if("IE".equals(request.getParameter("type"))){
		    	response.addHeader("XDomainRequestAllowed","1");
		    }
			List<CourseDesign> list = courseDesignServiceImpl.getFrontDataByPage(limit, offset, choice);
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
