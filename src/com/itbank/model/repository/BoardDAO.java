package com.itbank.model.repository;

import java.util.List;

import com.itbank.model.domain.Board;

public interface BoardDAO {
	public List selectAll();
	public Board select(int board_id);
	public int insert(Board board);
	public int update(Board board);
	public int delete(int board_id);
}
