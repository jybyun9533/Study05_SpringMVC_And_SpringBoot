package com.encore.spring.model;

import java.util.List;
import java.util.Map;

import com.encore.spring.domain.MyProduct;

public interface ProductDAO {
	public List<MyProduct> getProductList();

	public List<MyProduct> findProducts(Map map);

	public int enrollProduct(MyProduct product);

	public int deleteProduct(String name);

	public int updateProduct(MyProduct product);

}
