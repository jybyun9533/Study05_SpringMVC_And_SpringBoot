package com.encore.spring.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.encore.spring.domain.Item;

// MyBatisramework 단위테스트
public class MyBatisUnitTest1 {
	public static void main(String[] args) throws Exception {
		// 1. 설명문서 읽기
		Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
		
		// 2. SqlSessionFactory
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		
		// 3. SqlSession
		SqlSession session = factory.openSession();
		
		// 4.쿼리문 수행
		List<Item> list = session.selectList("ItemMapper.getItemList");
		for(Item vo : list) 
			System.out.println(vo);
		System.out.println("\n==============\n");
		
		Item item = session.selectOne("ItemMapper.getItem",1111);
		System.out.println(item);
	}

}
