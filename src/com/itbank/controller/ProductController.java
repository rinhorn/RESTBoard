package com.itbank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
	
	@RequestMapping(value="/admin/product/list")
	public String selectAll(HttpServletRequest request) {
		System.out.println("상품 목록을 요청");
		
		return "admin/product/index";
	}
}
