package com.itbank.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;

//관리자 모드를 접근하고자 하는 유저의 세션이 존재하는지 체크
//xml기반으로...POJO 클래스임
public class XMLAdminSessionAspect{
	public String loginCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		String viewName=null;
		
		System.out.println("세션을 체크하겠습니다.");
		String methodName=joinPoint.getSignature().getName();
		Object[] objArray=joinPoint.getArgs(); //메서드 호출시 매개변수 갯수
		System.out.println("매개변수 갯수는 "+objArray.length);
		
		//HttpServletRequest 인것만 골라내서 세션을 얻어와 로그인 여부를 체크
		System.out.println(methodName+"호출 전");
		for(Object obj:objArray) {
			if(obj instanceof HttpServletRequest) {
				HttpServletRequest request=(HttpServletRequest)obj;
				if(request.getSession().getAttribute("admin")==null) {
					System.out.println("로그인이 필요합니다.");
					viewName="admin/login/login";
				}else {
					joinPoint.proceed(); //원래 호출했던 메서드 selectAll() 그대로 호출
				}
			}
		}
		System.out.println(methodName+"호출 후");
		return viewName;
	}
}
