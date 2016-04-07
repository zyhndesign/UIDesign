package com.cidic.design.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.design.interceptor.MemberInterceptor;
import com.cidic.design.model.User;
import com.cidic.design.service.UserService;
import com.cidic.design.util.CipherUtil;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	@Qualifier(value="userServiceImpl")
	private UserService userServiceImpl;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/login");
		return view;
	}

	@RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
	public ModelAndView submit(String username, String password, HttpServletRequest request) {

		ModelAndView view = new ModelAndView();
		User user = new User();
		user.setUsername(username);
		user.setPassword(CipherUtil.generatePassword(password));
		
		if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) && userServiceImpl.checkUser(user)) {
			// 当登陆成功是，将用户信息存放到session中去
			HttpSession session = request.getSession();
			session.setAttribute(MemberInterceptor.SEESION_MEMBER, username);

			view.setViewName("redirect:/admin/home");
		} else {

			view.setViewName("/login");

		}
		return view;
	}

	@RequestMapping(value = "/admin/home", method = { RequestMethod.GET })
	public ModelAndView adminIndex() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/home");
		return view;
	}

	@RequestMapping(value = "/admin", method = { RequestMethod.GET })
	public ModelAndView admin(HttpServletRequest request) {
		String username =  (String)request.getSession().getAttribute(MemberInterceptor.SEESION_MEMBER);  
		ModelAndView view = new ModelAndView();
        if(username == null){  
        	view.setViewName("/login");
        }
        else  
        { 
        	view.setViewName("/admin/index");
        }
        return view;
	}
}
