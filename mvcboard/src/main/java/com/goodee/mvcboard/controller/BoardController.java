package com.goodee.mvcboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcboard.service.BoardService;
import com.goodee.mvcboard.vo.Board;

import lombok.extern.slf4j.Slf4j;
       
@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	/*
    ANSI_RESET = "\u001B[0m";
    ANSI_BLACK = "\u001B[30m";
    ANSI_RED = "\u001B[31m";
    ANSI_GREEN = "\u001B[32m";
    ANSI_YELLOW = "\u001B[33m";
    ANSI_BLUE = "\u001B[34m";
    ANSI_PURPLE = "\u001B[35m";
    ANSI_CYAN = "\u001B[36m";
    ANSI_WHITE = "\u001B[37m";
    ANSI_BLACK_BACKGROUND = "\u001B[40m";
    ANSI_RED_BACKGROUND = "\u001B[41m";
    ANSI_GREEN_BACKGROUND = "\u001B[42m";
    ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    ANSI_BLUE_BACKGROUND = "\u001B[44m";
    ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    ANSI_CYAN_BACKGROUND = "\u001B[46m";
    ANSI_WHITE_BACKGROUND = "\u001B[47m";
    */
	// 전체리스트 
	@GetMapping("/board/boardList")
	public String boardList(Model model, 
							// 형변환 및 유효성검사 불필요한 
							@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
							@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage,
															// required는 기본 = ture
							@RequestParam(name = "localName", required = false) String localName) {
		
		System.out.println("\u001B[33m" + "localName :" + localName + "\u001B[0m"); // 색상추가
		
		Map<String, Object> resultMap = boardService.getBoardList(currentPage, rowPerPage, localName);
		
		// view로 넘길때는 다시 분리해서
		model.addAttribute("localNameList", resultMap.get("localNameList"));
		model.addAttribute("boardList", resultMap.get("boardList"));
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		model.addAttribute("localName", localName);
		
		return "board/boardList";
	}
	
	// 상세보기
	@GetMapping("/board/boardOne")
	public String boardOne(Model model,
							@RequestParam(name = "boardNo") int boardNo) {
		// No 셋팅
		Board board = new Board();
		board.setBoardNo(boardNo);
		board = boardService.boardOne(board);
		
		// 뷰로 전달
		model.addAttribute("board", board);
		
		return "board/boardOne";
	}
	
	// 추가 폼
	@GetMapping("/board/addBoard")
	public String addBoard(Model model) {
		Map<String, Object> map = boardService.localList();
		
		// 뷰로 전달
		model.addAttribute("localList", map.get("localList"));
		
		return "board/addBoard";
	}
	
	// 추가 액션
	@PostMapping("/board/addBoard")
	public String addBoard(Board board) {
		int row = boardService.addBoard(board);
		log.debug("\u001B[32m" + "row:" + row + "\u001B[0m"); // 색상추가
		return "redirect:/board/boardList";
		// redirect로 시작하지 않으면 redirectDispath로 포워드도 함께 전달
	}
	
	// 수정폼
	@GetMapping("/board/modifyBoard")
	public String modifyBoard(Model model,
								@RequestParam(name = "boardNo") int boardNo) {
		Map<String, Object> map = boardService.localList();
		
		Board board = new Board();
		board.setBoardNo(boardNo);
		board = boardService.boardOne(board);
		
		// 뷰로 전달
		model.addAttribute("localList", map.get("localList"));
		model.addAttribute("board", board);
		
		return "board/modifyBoard";
	}
	
	// 수정 액션
	@PostMapping("/board/modifyBoard")
	public String modifyBoard(@RequestParam(name="boardNo") int boardNo,
								@RequestParam(name="localName") String localName,
								@RequestParam(name="boardTitle") String boardTitle,
								@RequestParam(name="boardContent") String boardContent,
								@RequestParam(name="memberId") String memberId) {
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setLocalName(localName);
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setMemberId(memberId);
		
		int row = boardService.modifyBoard(board);
		log.debug("\u001B[32m" + "수정 row:" + row + "\u001B[0m"); // 색상추가
		
		return "redirect:/board/boardOne?boardNo=" + boardNo;
		// redirect로 시작하지 않으면 redirectDispath로 포워드도 함께 전달
	}
	
	// 삭제 폼
	@GetMapping("/board/removeBoard")
	public String removeBoard(Model model,
								@RequestParam(name="boardNo") int boardNo) {
		
		Board board = new Board();
		board.setBoardNo(boardNo);
		board = boardService.boardOne(board);
		
		model.addAttribute("board", board);
		
		return "board/removeBoard";
	}
	// 삭제 액션
	@PostMapping("/board/removeBoard")
	public String removeBoard(@RequestParam(name="boardNo") int boardNo,
								@RequestParam(name="memberId") String memberId) {
		
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setMemberId(memberId);
		
		int row = boardService.deleteBoard(board);
		log.debug("\u001B[32m" + "삭제 row:" + row + "\u001B[0m"); // 색상추가
		
		return "redirect:/board/boardList";
	}	
}
