package com.itbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*component-scan �±װ� ��ϵǾ� �־�� �Ʒ��� ������Ʈ�� ����*/
@Controller
public class BoardController {
	
	//DefaultAnnotationHandlerMapping�� ���� �Ʒ��� ��û������ ������ �� �ִ� ���̴�.
	@RequestMapping(value="/board/list",method=RequestMethod.GET)
	public String selectAll() {
		
		System.out.println("����� ���ϴ±���");
		return null;
	}
}
