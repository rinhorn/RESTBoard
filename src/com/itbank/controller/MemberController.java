package com.itbank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	@RequestMapping(value="/admin/member/list")
	public String selectAll(HttpServletRequest request) {
		System.out.println("ȸ�� ����� ��û");
		
		return "admin/member/index";
	}
}
