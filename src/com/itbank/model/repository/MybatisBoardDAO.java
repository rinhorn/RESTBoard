package com.itbank.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itbank.model.domain.Board;

@Repository
public class MybatisBoardDAO implements BoardDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	public List selectAll() {
		return sessionTemplate.selectList("Board.selectAll");
	}

	public Board select(int board_id) {
		return sessionTemplate.selectOne("Board.select",board_id);
	}

	public int insert(Board board) {
		return sessionTemplate.insert("Board.insert", board);
	}

	public int update(Board board) {
		return sessionTemplate.update("Board.update", board);
	}

	public int delete(int board_id) {
		return sessionTemplate.delete("Board.delete", board_id);
	}
}
