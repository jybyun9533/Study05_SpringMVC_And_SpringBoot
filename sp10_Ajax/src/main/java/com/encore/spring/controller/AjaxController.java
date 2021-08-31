package com.encore.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.domain.Person;

@Controller
public class AjaxController {

	@RequestMapping("/") // contextPath로 요청 한다면.. http://127.0.0.1:8888/spring
	public String index() { //결과페이지만 리턴 할 경우 String
		System.out.println("/..calling");
		return "redirect:index.jsp";
	}
	
	@RequestMapping("synchro")
	public String synchro(Model model) {
		model.addAttribute("info", "addAttribute : 동기 통신");
		System.out.println("동기통신 synchro() calling");
		
		return "synchro_result"; // forward :: WEB-INF/response/synchro_result.jsp
	}
	
	@RequestMapping("asynch")
	public String asynch(Model model) {
		System.out.println("비동기통신..asynchro()");
		model.addAttribute("person", new Person("아이유","서초동"));
		//return new ModelAndView("JsonView", "person", new Person("아이유","서초동"));
		return "JsonView";
	}
}
