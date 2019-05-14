package com.itbank.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itbank.exception.DeleteFailException;
import com.itbank.exception.RegistFailException;
import com.itbank.model.domain.Board;
import com.itbank.model.service.BoardService;

@RestController
public class RestBoardController {
	@Autowired
	private BoardService boardService;

	//브라우저에서는 그대로 접근하면 되고 단지 개발자가 컨트롤러에 서블릿경로를 명시하지말자
	//하고싶으면 빈설정하면 된다.
	@RequestMapping(value="/boards",method=RequestMethod.GET)
	@ResponseBody
	public String selectAll() {
		System.out.println("REST하게 목록 요청");
		List<Board> boardList=boardService.selectAll();
		StringBuilder sb=new StringBuilder();
		
		sb.append("[");
		for(int i=0;i<boardList.size();i++) {
			Board board=boardList.get(i);
			sb.append("{");
			sb.append("\"board_id\":"+board.getBoard_id()+",");
			sb.append("\"writer\":\""+board.getWriter()+"\",");
			sb.append("\"title\":\""+board.getTitle()+"\",");
			sb.append("\"content\":\""+board.getContent()+"\",");
			sb.append("\"regdate\":\""+board.getRegdate()+"\",");
			sb.append("\"hit\":"+board.getHit());
			if(i<boardList.size()-1) {
				sb.append("},");
			}else {
				sb.append("}");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}

	// 한건 가져오기
	// board/23 : url에 포함되어있음. 경로변수 PathVariable
	// board?id=23 : 쿼리스트링 (url경로에 포함되어 있지않음)
	@RequestMapping(value = "/boards/{board_id}", method = RequestMethod.GET)
	@ResponseBody
	public String select(@PathVariable("board_id") int board_id) {
		System.out.println("넘겨받은 board_id "+board_id);
		Board board=boardService.select(board_id);
		//스트링으로 직접 처리해도 되지만 이번 경우는 json 객체를 활용해본다.
		JSONObject json=new JSONObject();
		json.put("board_id", board.getBoard_id());
		json.put("writer", board.getWriter());
		json.put("title", board.getTitle());
		json.put("content", board.getContent());
		json.put("regdate", board.getRegdate());
		json.put("hit", board.getHit());

		return json.toString();
	}

	// 등록하기.
	@RequestMapping(value = "/boards", method = RequestMethod.POST)
	@ResponseBody // 응답시 ViewResolver가 관여되지 않음. 따라서 jsp로 조합되지 않는다. 반환값 자체가 곧 응답데이터
	public String insert(Board board) {
		boardService.insert(board);

		return "{\"resultCode\":1,\"msg\":\"등록 성공\"}";
	}

	// 수정하기 : 경로변수뿐만 아니라 내용이 큰 것은 form 양식으로 전송된다.
	@RequestMapping(value = "/boards", method = RequestMethod.PUT)
	@ResponseBody
	public String update(Board board) {
		System.out.println("수정을 원하는군요");
		System.out.println(board.getBoard_id());
		System.out.println(board.getWriter());
		System.out.println(board.getTitle());
		System.out.println(board.getContent());
		
		return "{\"resultCode\":1,\"msg\":\"수정 성공\"}";
	}

	// 삭제하기
	@RequestMapping(value = "/boards/{board_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("board_id") int board_id) {
		System.out.println(board_id+"삭제를 원하는 군요?");
		boardService.delete(board_id);
		
		return "{\"resultCode\":1,\"msg\":\"삭제 성공\"}";
	}

	@ExceptionHandler(RegistFailException.class)
	@ResponseBody
	public String handleRegistFail(RegistFailException e) {

		return "{\"resultCode\":0,\"msg\":\"" + e.getMessage() + "\"}";
	}
	@ExceptionHandler(RegistFailException.class)
	@ResponseBody
	public String handleDeleteFail(DeleteFailException e) {

		return "{\"resultCode\":0,\"msg\":\"" + e.getMessage() + "\"}";
	}
}
