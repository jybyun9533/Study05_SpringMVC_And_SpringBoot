package com.encore.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	public void insert(Board vo) throws Exception {
		boardDAO.insert(vo);
		
	}

	public List<Board> selectAll() throws Exception {

		return boardDAO.selectAll();
	}

	public Board selectOne(String no) throws Exception {

		return boardDAO.selectOne(no);
	}

}
