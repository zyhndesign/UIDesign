package com.cidic.design.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.design.interceptor.MemberInterceptor;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/login");
		return view;
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public ModelAndView submit(String username, String password, HttpServletRequest request) {

		ModelAndView view = new ModelAndView();
		if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) && username.equals("admin")
				&& password.equals("admin")) {
			// 当登陆成功是，将用户信息存放到session中去
			HttpSession session = request.getSession();
			session.setAttribute(MemberInterceptor.SEESION_MEMBER, "admin");

			view.setViewName("redirect:/admin/index");
		} else {

			view.setViewName("/login");

		}
		return view;
	}

	@RequestMapping(value = "/admin/index", method = { RequestMethod.GET })
	public ModelAndView adminIndex() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/index");
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
