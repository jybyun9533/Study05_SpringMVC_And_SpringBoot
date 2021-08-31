package com.encore.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.domain.MyProduct;
import com.encore.spring.model.ProductCatalog;

@Controller
public class ProductController {

	@Autowired
	private ProductCatalog productCatalog;

	@RequestMapping("myProduct.do")
	public ModelAndView myProduct(String name, String maker, int price) throws Exception {
		MyProduct product = new MyProduct(name, maker, price);
		productCatalog.enrollProduct(product);

		List<MyProduct> list = productCatalog.getProductList();
		return new ModelAndView("productList", "list", list);
	}

	// @RequestMapping(value = "mySearch.do", method = RequestMethod.POST)
	@RequestMapping("mySearch.do")
	public ModelAndView mySearch(String choice, String word) throws Exception {
		Map map = new HashMap();
		
		map.put("choice", choice);
		map.put("word", word);
		
		List<MyProduct> list = productCatalog.findProducts(map);
		return new ModelAndView("productList", "list", list);
	}
}
