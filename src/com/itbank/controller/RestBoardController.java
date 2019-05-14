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

	//������������ �״�� �����ϸ� �ǰ� ���� �����ڰ� ��Ʈ�ѷ��� ������θ� �����������
	//�ϰ������ �����ϸ� �ȴ�.
	@RequestMapping(value="/boards",method=RequestMethod.GET)
	@ResponseBody
	public String selectAll() {
		System.out.println("REST�ϰ� ��� ��û");
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

	// �Ѱ� ��������
	// board/23 : url�� ���ԵǾ�����. ��κ��� PathVariable
	// board?id=23 : ������Ʈ�� (url��ο� ���ԵǾ� ��������)
	@RequestMapping(value = "/boards/{board_id}", method = RequestMethod.GET)
	@ResponseBody
	public String select(@PathVariable("board_id") int board_id) {
		System.out.println("�Ѱܹ��� board_id "+board_id);
		Board board=boardService.select(board_id);
		//��Ʈ������ ���� ó���ص� ������ �̹� ���� json ��ü�� Ȱ���غ���.
		JSONObject json=new JSONObject();
		json.put("board_id", board.getBoard_id());
		json.put("writer", board.getWriter());
		json.put("title", board.getTitle());
		json.put("content", board.getContent());
		json.put("regdate", board.getRegdate());
		json.put("hit", board.getHit());

		return json.toString();
	}

	// ����ϱ�.
	@RequestMapping(value = "/boards", method = RequestMethod.POST)
	@ResponseBody // ����� ViewResolver�� �������� ����. ���� jsp�� ���յ��� �ʴ´�. ��ȯ�� ��ü�� �� ���䵥����
	public String insert(Board board) {
		boardService.insert(board);

		return "{\"resultCode\":1,\"msg\":\"��� ����\"}";
	}

	// �����ϱ� : ��κ����Ӹ� �ƴ϶� ������ ū ���� form ������� ���۵ȴ�.
	@RequestMapping(value = "/boards", method = RequestMethod.PUT)
	@ResponseBody
	public String update(Board board) {
		System.out.println("������ ���ϴ±���");
		System.out.println(board.getBoard_id());
		System.out.println(board.getWriter());
		System.out.println(board.getTitle());
		System.out.println(board.getContent());
		
		return "{\"resultCode\":1,\"msg\":\"���� ����\"}";
	}

	// �����ϱ�
	@RequestMapping(value = "/boards/{board_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("board_id") int board_id) {
		System.out.println(board_id+"������ ���ϴ� ����?");
		boardService.delete(board_id);
		
		return "{\"resultCode\":1,\"msg\":\"���� ����\"}";
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
