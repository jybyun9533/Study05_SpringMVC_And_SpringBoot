package com.encore.rest.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.encore.rest.dto.Greeting;

@RestController
public class GreetingController {

	private final AtomicLong counter = new AtomicLong();
	
	// http://127.0.0.1:8888/rest/greet
	@GetMapping("/greet")
	public Greeting sayGreet() {
		return new Greeting(314L, "Restful API");
	}

	// http://127.0.0.1:8888/rest/greet/33
	/*
	 * @PathVariable은 단순 파라미터 값을 URL경로에 포함시키는 방법
	 * greet/{num} 처럼 {}를 이용해서 매핑되는 URL 경로를 
	 * 메소드 인자값 옵션인 @PathVariable 어노테이션으로 작성된 인자값 num에 자동 매핑
	 * */
	@GetMapping("/greet/{num}")
	public String showSample(@PathVariable int num) {
		return "Hello Restful API case number" + num;
	}

	
	// http://127.0.0.1:8888/rest/greet2?msg=ENCORE
	@GetMapping("/greet2")
	public Greeting sayGreet(@RequestParam(value="msg", required=false, defaultValue="world") String name) {
		
		return new Greeting(counter.incrementAndGet(),name);
	}
}
