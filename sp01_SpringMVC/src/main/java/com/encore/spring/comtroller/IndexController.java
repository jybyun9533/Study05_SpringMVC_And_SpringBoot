package com.encore.spring.comtroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class IndexController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mv = new ModelAndView(); // 뷰 페이지 이름, 이동 경로, 데이터 바인딩까지 다 할수 있는 라이브러리
		mv.setViewName("result"); // 확장자 안써도됨.
		mv.addObject("message", "Hello SpringMVC Framework!!");
		
		return mv;
	}

}
