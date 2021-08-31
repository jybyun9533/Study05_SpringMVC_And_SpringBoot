package com.encore.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.rest.dto.Customer;

/*
 * @Controller와 @RestController의 차ㅣ
 * 1. @Controller
 *  - @RequestMapping() 실행결과로 결과페이지 정보가 반환
 *  
 * 2. @RestController
 *  - @RequestMapping() 실행결과로 데이터가 반환
 *  - @RequestMapping() Rest API 서비스를 사용해서 요청함
 *   -> C - INSERT :: doPost()
 *   -> R - SELECT :: doGet()
 *   -> U - UPDATE :: doPut()
 *   -> D - DELETE :: doDelete()
 */

@RestController
@RequestMapping("/simple")
public class SimpleController {

	// http://127.0.0.1:8888/rest/hello --> 브라우저에 반환
	@GetMapping("/hello")
	public String sayHello() {

		return "Hello Restful Service";
	}

	// http://127.0.0.1:8888/rest/simple/custom
	// 객체가 리턴되는 경우 JSON으로 변환되어서 리턴되기 때문에 라이브러리 반드시 추가
	@GetMapping("/custom")
	public Customer detail() {
		// 비지니스로직 수행한 결과로 Customer 객체가 리턴 (가정)
		Customer custom = new Customer("아이유", "새벽거리를 걷다");
		return custom;
	}

	@GetMapping("/allCustom")
	public List<Customer> allCustom() {
		// 비지니스로직 수행한 결과로 List 객체가 리턴 (가정)
		List<Customer> list = new ArrayList<Customer>();
		for (int i = 0; i < 3; i++) {
			Customer vo = new Customer();
			vo.setName("이름" + i);
			vo.setMessage("메세지" + i);
			list.add(vo);
		}

		return list;
	}

	@GetMapping("/mapCustom")
	public Map<Integer, Customer> sendCustom() {
		// 비지니스로직 수행한 결과로 Map 객체가 리턴 (가정)
		Map<Integer, Customer> map = new HashMap<Integer, Customer>();
		map.put(1, new Customer("아이유", "새벽길1"));
		map.put(2, new Customer("아이유", "새벽길2"));
		map.put(3, new Customer("아이유", "새벽길3"));
		return map;
	}

}
