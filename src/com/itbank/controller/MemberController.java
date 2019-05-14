package com.itbank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	@RequestMapping(value="/admin/member/list")
	public String selectAll(HttpServletRequest request) {
		System.out.println("회원 목록을 요청");
		
		return "admin/member/index";
	}
}
