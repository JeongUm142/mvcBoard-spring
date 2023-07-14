package com.goodee.mvcboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
	@GetMapping("/rest/localName")
	public String localNameList() {
		return "/html/localNameList";
	}
	
}
