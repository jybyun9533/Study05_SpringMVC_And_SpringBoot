package com.encore.board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.encore.board.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	final String NS = "sql.board.mapper.";

	@Override
	public int write(BoardVO vo) {
		return sqlSession.insert(NS + "write", vo);
	}

	@Override
	public String selectByNoForDate(int no) {
		return sqlSession.selectOne(NS + "selectByNoForDate", no);
	}

	@Override
	public List<BoardVO> getBoardList() {
		return sqlSession.selectList(NS+"getBoardList");
	}

	@Override
	public BoardVO showContent(int no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"showContent", no);
	}

	@Override
	public int updateBoard(BoardVO bvo) {
		return sqlSession.update(NS+"updateBoard", bvo);
	}

	@Override
	public int delete(int no) {
		return sqlSession.delete(NS+"delete", no);
	}

	@Override
	public int updateCount(int no) {
		return sqlSession.update(NS+"updateCount", no);
	}

}
