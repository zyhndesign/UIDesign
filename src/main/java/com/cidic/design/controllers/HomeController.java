package com.cidic.design.controllers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.exception.UIDesignException;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.HomeService;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private ResultModel resultModel = null;
	
	@Autowired
	@Qualifier(value="homeServiceImpl")
	private HomeService homeServiceImpl;
	
	@ExceptionHandler(UIDesignException.class)
	public @ResponseBody ResultModel handleCustomException(UIDesignException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		return resultModel;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "login";
	}
	
	@RequestMapping(value = "/homeContent", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getHomeContent(Locale locale, Model model){
		
		List<Object> list = null;
		
		try{
			
			list = homeServiceImpl.getHomeContentData();
			
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
			resultModel.setObject(list);
		}
		catch(Exception e){
			throw new UIDesignException(500, "获取数据失败！");
		}
		return resultModel;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getSearchContent(HttpServletRequest request,@RequestParam String searchKey){
		List<Object> list = new ArrayList<Object>();
		String[] searchKeyValue = searchKey.split(",");
		if (searchKeyValue.length > 0){
			try{
				list = homeServiceImpl.getSearchResultByKeywards(Arrays.asList(searchKey));
				resultModel = new ResultModel();
				resultModel.setResultCode(200);
				resultModel.setObject(list);
			}
			catch(Exception e){
				throw new UIDesignException(500, "获取数据失败！");
			}
		}
		else
		{
			throw new UIDesignException(500, "没有搜索关键词！");
		}
		
		return resultModel;
	}
	
	@RequestMapping(value = "/getUploadKey", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getUploadKey(HttpServletRequest request){
			
			final String ACCESS_KEY = "Q-DeiayZfPqA0WDSOGSf-ekk345VrzuZa_6oBrX_";
			final String SECRET_KEY = "fIiGiRr3pFmHOmBDR2Md1hTCqpMMBcE_gvZYMzwD";
			final String bucketname = "design-course";
			try{
				StringMap strMap = new StringMap().putNotNull("returnBody", "{\"key\": $(key), \"hash\": $(etag), \"w\": $(imageInfo.width), \"h\": $(imageInfo.height)}");
				Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
				
				String token = auth.uploadToken(bucketname,null,3600,strMap);
				
				resultModel = new ResultModel();
				resultModel.setResultCode(200);
				resultModel.setUptoken(token);
			}
			catch(Exception e){
				throw new UIDesignException(500, "获取数据失败！");
			}
		
		
		return resultModel;
	}
}
