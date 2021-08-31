package com.encore.spring.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.encore.spring.Board;

/*
 * FactoryService를 모듈화 시켜놓고 그걸 호출하는 구조
 * 최종 테스트 버전. 이걸로 하삼 팀작업할때
 * */
public class BoardTest {
	public static void main(String[] args) throws Exception {
		// 1. Factory 얻어온다.
		SqlSession session = null;
		SqlSessionFactory factory = TestUtil.getSqlSessionFactory();
		// 2. openSession()으로 SqlSession 받아온다. db 연결 쿼리문 실행할 수 있다.
		session = factory.openSession();

		
		// 객체예제 생성
		Board board1 = new Board();
		board1.setId("id1");
		board1.setTitle("제목1");
		board1.setContents("내용 1 입니다.");
		
		// INSERT
//		session.insert("BoardMapper.insert", board1);
//		session.commit();

		// SELECTALL
		List<Board> list = session.selectList("BoardMapper.selectAll");
		System.out.println(list);

		// SELECTONE
		Board board = session.selectOne("BoardMapper.selectOne", "1");
		System.out.println(board);

	}
}
