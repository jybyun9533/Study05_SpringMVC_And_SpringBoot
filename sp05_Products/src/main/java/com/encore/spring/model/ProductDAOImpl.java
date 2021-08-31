package com.encore.spring.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.encore.spring.domain.MyProduct;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SqlSession sqlSession;
	private final String NS = "ProductMapper.";

	@Override
	public List<MyProduct> getProductList() {
		return sqlSession.selectList(NS + "getProductList");
	}

	@Override
	public List<MyProduct> findProducts(Map map) {
		return sqlSession.selectList(NS + "findProducts", map);
	}

	@Override
	public int enrollProduct(MyProduct product) {
		int result = sqlSession.insert(NS + "enrollProduct", product);
		return result;
	}

	@Override
	public int deleteProduct(String name) {
		int result = sqlSession.delete(NS + "deleteProduct", name);
		return result;
	}

	@Override
	public int updateProduct(MyProduct product) {
		int result = sqlSession.update(NS + "updateProduct", product);
		return result;
	}

}
