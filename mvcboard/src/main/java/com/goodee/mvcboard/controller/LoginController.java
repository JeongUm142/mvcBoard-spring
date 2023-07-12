package com.goodee.mvcboard.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
// @SessionAttributes(names = "로그인정보키")
public class LoginController {
	// 각각 따로 받고 싶을때 사용@RequestParam 
	@PostMapping("/login")
	public String login(HttpSession session,  // 세션
						@RequestParam(value="memberId") String memberId,
						@RequestParam(value="memberPw") String memberPw) {
			// service(memberId, memberPw) -> mapper  -> 로그인 성공유무 반환
			
			// 로그인 성공시
			// model.addAttribute("로그인정보키", "로그인정보값"); - 모델 생명주기를 세션으로 변경방법
			// session을 받는 방법
			session.setAttribute("", ""); // 로그인 정보저장
			
		return "redirect:/";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/login";
	}
}
