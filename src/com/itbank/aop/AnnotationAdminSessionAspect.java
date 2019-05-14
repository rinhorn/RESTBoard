package com.itbank.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/*
 * xml�� ������̼� ��� AOPó���� ����� �����ϸ� ���� ��ȣ���� ���� �����ڰ� ���ϴ� ����� �����ϸ� �ȴ�.
 * */
@Aspect
public class AnnotationAdminSessionAspect {
	//xml�� ���� ������ ��ġ(PointCut), ��(Advice)�� �ڹ� �ڵ忡�� �����ؾ� ��
	@Pointcut("execution(public * com.itbank.controller..*(..))")
	public void checkMember() {//�ܼ��� ���̵� ����
		
	}
	@Pointcut("execution(public * com.itbank.controller.BoardController..*(..))")
	public void checkBoard() {//�ܼ��� ���̵� ����
		
	}
	@Pointcut("execution(public * com.itbank.controller.ProductController..*(..))")
	public void checkProduct() {//�ܼ��� ���̵� ����
		
	}
	
	//�Ʒ��� �迭�� ����ִ� ��û URL�� ���ؼ��� ����üũ�� ���ذ���
	String[] exceptList={
		"/admin/login",
		"/admin/aaa",
		"/admin/bbb"
	};
	//���� ���� �ڵ�
	//�α����� �ʿ��� (������ ������) ȣ�⸸ ó���ؾ� �Ѵ�
	@Around("checkMember()")
	public String loginCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		//��� �α��� üũ�������� ������ ���縦 üũ�ؾ��ϴ� ��쿡�� ����
		//�Ǵ� ����? �ᱹ HttpServletRequest�� �Ѿ������ ����
		String viewName=null;
		HttpServletRequest request=null;
		
		String requestURL=null;
		int count=0;//��ܿ� ������ ��� ������ų����
		
		Object[] objArray=joinPoint.getArgs();//�޼��� ȣ��� ���޵� �Ű������� ��ȯ
		for(Object obj:objArray) {//��� �Ű����� ����(������Ʈ��ü���� ���� ����)
			if(obj instanceof HttpServletRequest) {
				request=(HttpServletRequest)obj;
				requestURL=request.getRequestURL().toString();
				System.out.println("requestURL "+requestURL);
				
				for(int i=0;i<exceptList.length;i++) {
					if(requestURL.endsWith(exceptList[i])) {
						count++;//���ܸ�� �߰�
					}
				}
			}
		}
		
		//�α����� �ʿ��� �޼��� ȣ��ø� ����üũ
		if(request!=null&&count<1) {
			if(request.getSession().getAttribute("admin")==null) {
				viewName="admin/login/error";
			}else {
				viewName=(String)joinPoint.proceed();
				String methodName=joinPoint.getSignature().getName();
				System.out.println("�α��� �ʿ� : ȣ��� ���� �޼���� "+methodName+", �޼����� ��ȯ���� "+viewName);
			}
		}else {
			//�׳� ���������� ��Ʈ�ѷ��� �޼����� ��ȯ���� �״�� ���� ModelAndViewó��
			viewName=(String)joinPoint.proceed();
			String methodName=joinPoint.getSignature().getName();
			System.out.println("�α��� ���ʿ� : ȣ��� ���� �޼���� "+methodName+", �޼����� ��ȯ���� "+viewName);
		}
		
		return viewName;
	}
}
