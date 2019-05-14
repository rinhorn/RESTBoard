package com.itbank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*component-scan �±װ� ��ϵǾ� �־�� �Ʒ��� ������Ʈ�� ����*/
@Controller
public class BoardController {
	
	//DefaultAnnotationHandlerMapping�� ���� �Ʒ��� ��û������ ������ �� �ִ� ���̴�.
	@RequestMapping(value="/admin/board/list",method=RequestMethod.GET)
	public String selectAll(HttpServletRequest request) {
		
		System.out.println("����� ���ϴ±���");
		return "admin/board/index";
	}
}
