package com.goodee.mvcboard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcboard.service.BoardService;
import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

import lombok.extern.slf4j.Slf4j;
       
@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	String yellow = "\u001B[33m";
	String reset = "\u001B[0m";
	
	// 전체리스트 
	@GetMapping("/board/boardList")
	public String boardList(Model model, 
							// 형변환 및 유효성검사 불필요한 
							@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
							@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage,
															// required는 기본 = ture
							@RequestParam(name = "localName", required = false) String localName) {
		
		System.out.println(yellow + "localName :" + localName + reset); // 색상추가
		
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
		
		List<Boardfile> boardfile = boardService.selectBoardfile(boardNo);
		
		// 뷰로 전달
		model.addAttribute("board", board);
		model.addAttribute("boardfile", boardfile);
		
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
	public String addBoard(HttpServletRequest request, Board board) { // requestAPI를 직접 호출하기 위해서 request 객체를 받는다 
		String path = request.getServletContext().getRealPath("/upload/");
		int row = boardService.addBoard(board, path);
		
		log.debug(yellow + "row:" + row + reset); // 색상추가
		
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
		log.debug(yellow+ "수정 row:" + row + reset); // 색상추가
		
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
	public String removeBoard(HttpServletRequest request,
								@RequestParam(name="boardNo") int boardNo,
								@RequestParam(name="localName") String localName,
								@RequestParam(name="memberId") String memberId) {
		String path = request.getServletContext().getRealPath("/upload/");
		
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setLocalName(localName);
		board.setMemberId(memberId);
			
		int row = boardService.deleteBoard(board ,path);
		log.debug(yellow + "삭제 row:" + row + reset); // 색상추가
		
		return "redirect:/board/boardList";
	}	
}
