package com.encore.spring.model;

import java.util.List;
import java.util.Map;

import com.encore.spring.domain.MyProduct;

public interface ProductCatalog {

	public List<MyProduct> getProductList();

	public List<MyProduct> findProducts(Map map);

	public void enrollProduct(MyProduct product);

	public void deleteProduct(String name);

	public void updateProduct(MyProduct product);

}
