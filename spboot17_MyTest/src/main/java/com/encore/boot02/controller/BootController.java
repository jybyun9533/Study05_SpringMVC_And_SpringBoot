package com.encore.boot02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BootController {
	@GetMapping("index.do")
	public String index(Model model) {
		model.addAttribute("data","myTest Success");
		return "result";
	}
}
