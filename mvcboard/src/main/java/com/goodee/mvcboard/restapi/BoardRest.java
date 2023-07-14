package com.goodee.mvcboard.restapi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.mvcboard.service.BoardService;

@CrossOrigin
@RestController
public class BoardRest {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/rest/localNameList")
	public List<Map<String, Object>> getLocalNameList() {
		return boardService.getLocalNameList();
		
	}
}
