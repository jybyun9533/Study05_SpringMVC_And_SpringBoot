package com.encore.board.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.board.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDao;
	
	@Override
	public int write(BoardVO vo) {
		int row = boardDao.write(vo);
		String date = boardDao.selectByNoForDate(vo.getNo());
		vo.setWriteDate(date);
		return row; 
	}

	@Override
	public List<BoardVO> getBoardList() {
		// TODO Auto-generated method stub
		return boardDao.getBoardList();
	}

	@Override
	public BoardVO showContent(int no) {
		return boardDao.showContent(no);
	}

	@Override
	public int updateBoard(BoardVO bvo) {
		return boardDao.updateBoard(bvo);
	}

	@Override
	public int delete(int no) {
		// TODO Auto-generated method stub
		return boardDao.delete(no);
	}

	@Override
	public int updateCount(int no) {
		// TODO Auto-generated method stub
		return boardDao.updateCount(no);
	}
	

	
}
