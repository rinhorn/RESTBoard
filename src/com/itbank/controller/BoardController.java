package com.itbank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*component-scan 태그가 등록되어 있어야 아래의 컴포넌트를 생성*/
@Controller
public class BoardController {
	
	//DefaultAnnotationHandlerMapping에 의해 아래의 요청매핑이 동작할 수 있는 것이다.
	@RequestMapping(value="/admin/board/list",method=RequestMethod.GET)
	public String selectAll(HttpServletRequest request) {
		
		System.out.println("목록을 원하는군요");
		return "admin/board/index";
	}
}
