package com.encore.spring.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.spring.domain.MyProduct;

@Service
public class ProductCatalogImpl implements ProductCatalog {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<MyProduct> getProductList() {
		return productDAO.getProductList();
	}

	@Override
	public List<MyProduct> findProducts(Map map) {
		return productDAO.findProducts(map);
	}

	@Override
	public void enrollProduct(MyProduct product) {
		productDAO.enrollProduct(product);
	}

	@Override
	public void deleteProduct(String name) {
		productDAO.deleteProduct(name);
	}

	@Override
	public void updateProduct(MyProduct product) {
		productDAO.updateProduct(product);
	}

}
