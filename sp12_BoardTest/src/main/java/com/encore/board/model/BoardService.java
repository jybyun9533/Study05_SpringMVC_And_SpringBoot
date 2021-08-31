package com.encore.board.model;

import java.util.List;

import com.encore.board.domain.BoardVO;

public interface BoardService {
	int write(BoardVO vo);
	// String selectByNoForDate(int no);
	List<BoardVO> getBoardList();
	BoardVO showContent(int no);
	int updateBoard(BoardVO bvo);
	int delete(int no);
	int updateCount(int no);
}
