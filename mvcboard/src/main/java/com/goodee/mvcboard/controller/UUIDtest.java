package com.goodee.mvcboard.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UUIDtest {

	String yellow = "\u001B[33m";
	String retrun = "\u001B[0m";
	
	@GetMapping("uuidTest")
	public String uuidTest() {
		UUID uuid = UUID.randomUUID();
		log.debug(yellow + uuid.toString().replace("-", "") + retrun);
		return "";
	}
}	
