<%@page import="com.itbank.common.board.Pager"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itbank.model.domain.Board"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	Pager pager=new Pager();
%>
<%
	//여기서 게시판의 목록으리 생성하여 request객체에 담고 list.jsp에 전달
	//여기서 하는 이유는? 스프링으로 하기엔 귀찮
	List boardList=new ArrayList();
	for(int i=0;i<26;i++){
		Board board=new Board();
		board.setWriter("adams"+i);
		board.setTitle("ㅂㅂ"+i);
		
		boardList.add(board);
	}
	//페이징 처리 계산 시작
	pager.init(request, boardList.size());
	System.out.println(request.getParameter("currentPage"));
	
	//session.setAttribute("boardList", boardList);
	request.setAttribute("boardList", boardList);
	request.setAttribute("pager", pager);

	//포워딩 객체
	RequestDispatcher dis=request.getRequestDispatcher("/jstl/list.jsp");
	//<jsp:forward page=""></jsp:forward> 위와같은 의미임
	dis.forward(request, response);//포워딩 실시
%>
