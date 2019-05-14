package com.itbank.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/*
 * xml과 어노테이션 기반 AOP처리는 기능이 동일하며 단지 선호도에 따라 개발자가 원하는 방법을 선택하면 된다.
 * */
@Aspect
public class AnnotationAdminSessionAspect {
	//xml이 없기 때문에 위치(PointCut), 때(Advice)를 자바 코드에서 지정해야 함
	@Pointcut("execution(public * com.itbank.controller..*(..))")
	public void checkMember() {//단순한 아이디 역할
		
	}
	@Pointcut("execution(public * com.itbank.controller.BoardController..*(..))")
	public void checkBoard() {//단순한 아이디 역할
		
	}
	@Pointcut("execution(public * com.itbank.controller.ProductController..*(..))")
	public void checkProduct() {//단순한 아이디 역할
		
	}
	
	//아래의 배열에 들어있는 요청 URL에 대해서는 세션체크를 피해가자
	String[] exceptList={
		"/admin/login",
		"/admin/aaa",
		"/admin/bbb"
	};
	//공통 로직 코드
	//로그인이 필요한 (세션을 보유한) 호출만 처리해야 한다
	@Around("checkMember()")
	public String loginCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		//모두 로그인 체크하지말고 세션의 존재를 체크해야하는 경우에만 한정
		//판단 기준? 결국 HttpServletRequest가 넘어오는지 여부
		String viewName=null;
		HttpServletRequest request=null;
		
		String requestURL=null;
		int count=0;//명단에 존재할 경우 증가시킬거임
		
		Object[] objArray=joinPoint.getArgs();//메서드 호출시 전달된 매개변수를 반환
		for(Object obj:objArray) {//모든 매개변수 조사(리퀘스트객체인지 여부 조사)
			if(obj instanceof HttpServletRequest) {
				request=(HttpServletRequest)obj;
				requestURL=request.getRequestURL().toString();
				System.out.println("requestURL "+requestURL);
				
				for(int i=0;i<exceptList.length;i++) {
					if(requestURL.endsWith(exceptList[i])) {
						count++;//제외명단 발견
					}
				}
			}
		}
		
		//로그인이 필요한 메서드 호출시만 세션체크
		if(request!=null&&count<1) {
			if(request.getSession().getAttribute("admin")==null) {
				viewName="admin/login/error";
			}else {
				viewName=(String)joinPoint.proceed();
				String methodName=joinPoint.getSignature().getName();
				System.out.println("로그인 필요 : 호출된 원래 메서드는 "+methodName+", 메서드의 반환값은 "+viewName);
			}
		}else {
			//그냥 지나가야할 콘트롤러의 메서드의 반환형을 그대로 따라감 ModelAndView처럼
			viewName=(String)joinPoint.proceed();
			String methodName=joinPoint.getSignature().getName();
			System.out.println("로그인 불필요 : 호출된 원래 메서드는 "+methodName+", 메서드의 반환값은 "+viewName);
		}
		
		return viewName;
	}
}
