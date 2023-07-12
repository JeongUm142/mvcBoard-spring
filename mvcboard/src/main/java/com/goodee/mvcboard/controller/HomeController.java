package com.goodee.mvcboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // servlet대신
public class HomeController {
	@GetMapping("/home") // web.xml의 패턴맵핑 대신 or @WebSerevlet 
	public String home() {
		return "home"; // requestdispatcher.forward() 대신
	}
}
