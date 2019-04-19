package com.itbank.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itbank.exception.DeleteFailException;
import com.itbank.exception.RegistFailException;
import com.itbank.exception.UpdateFailException;
import com.itbank.model.domain.Board;
import com.itbank.model.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	@Qualifier("mybatisBoardDAO")
	private BoardDAO boardDAO;
	
	public List selectAll() {
		return boardDAO.selectAll();
	}

	public Board select(int board_id) {
		return boardDAO.select(board_id);
	}

	public void insert(Board board) throws RegistFailException{
		int result=boardDAO.insert(board);
		if(result==0) {
			throw new RegistFailException("등록 실패");
		}
	}

	public void update(Board board) throws UpdateFailException{
		int result=boardDAO.update(board);
		if(result==0) {
			throw new UpdateFailException("수정 실패");
		}
	}

	public void delete(int board_id) throws DeleteFailException{
		int result=boardDAO.delete(board_id);
		if(result==0) {
			throw new DeleteFailException("삭제 실패");
		}
	}

}
