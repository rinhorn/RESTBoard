package com.itbank.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;

//������ ��带 �����ϰ��� �ϴ� ������ ������ �����ϴ��� üũ
//xml�������...POJO Ŭ������
public class XMLAdminSessionAspect{
	public String loginCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		String viewName=null;
		
		System.out.println("������ üũ�ϰڽ��ϴ�.");
		String methodName=joinPoint.getSignature().getName();
		Object[] objArray=joinPoint.getArgs(); //�޼��� ȣ��� �Ű����� ����
		System.out.println("�Ű����� ������ "+objArray.length);
		
		//HttpServletRequest �ΰ͸� ��󳻼� ������ ���� �α��� ���θ� üũ
		System.out.println(methodName+"ȣ�� ��");
		for(Object obj:objArray) {
			if(obj instanceof HttpServletRequest) {
				HttpServletRequest request=(HttpServletRequest)obj;
				if(request.getSession().getAttribute("admin")==null) {
					System.out.println("�α����� �ʿ��մϴ�.");
					viewName="admin/login/login";
				}else {
					joinPoint.proceed(); //���� ȣ���ߴ� �޼��� selectAll() �״�� ȣ��
				}
			}
		}
		System.out.println(methodName+"ȣ�� ��");
		return viewName;
	}
}
