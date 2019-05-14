package com.itbank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	@RequestMapping(value="/admin/login", method=RequestMethod.POST)
	public String loginCheck(HttpServletRequest request,String id,String pass) {
		String viewName=null;
		//DB가 있다고 가정한다.
		String admin_id="adams";
		String admin_pass="1234";
		
		if(id.equals(admin_id)&&pass.equals(admin_pass)) {
			viewName="admin/main";
		}else {
			viewName="admin/login/error";
		}
		
		return viewName;
	}
}
