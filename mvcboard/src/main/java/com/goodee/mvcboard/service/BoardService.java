package com.goodee.mvcboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.mvcboard.mapper.BoardMapper;
import com.goodee.mvcboard.vo.Board;

@Service
@Transactional
public class BoardService {
	// 자료구조 필터링, DAO
	@Autowired 
	private BoardMapper boardMapper;
	
	// 리스트
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String localName){
		// 서비스 레이어 역할 1) 컨트롤러가 넘겨준 서비스가 가진 값을 DAO의 매개값에 맞게 가공
		int beginRow = (currentPage - 1) * rowPerPage;
		
		// 반환값 1
		List<Map<String, Object>> localNameList = boardMapper.selectLocalNameList();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("localName", localName);
		
		// 반환값 2
		List<Board> boardList = boardMapper.selectBoardListByPage(paramMap);
		
		// 서비스 레이어 역할 2) DAO에서 반환받은 값을 맞게 가공하여 컨트롤러에 반환
		int boardCount = boardMapper.selectBoardCount();
		int lastPage = boardCount / rowPerPage;
		if(boardCount % rowPerPage != 0) {
			lastPage += 1;
		}

		// 맵에 담아서 리턴
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("localNameList", localNameList);
		resultMap.put("boardList", boardList);
		resultMap.put("lastPage", lastPage);
		
		return resultMap;
	}
	
	// 지역 리스트(셀렉트)
	public Map<String, Object> localList() {
		List<Map<String, Object>> localList = boardMapper.selectLocalNameList();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("localList", localList);
		
		return map;
	}
	
	// 상세보기
	public Board boardOne(Board board) {
		return boardMapper.selectBoardOne(board);
	}
	
	// 추가
	public int addBoard(Board board) {
		board.getLocalName();
		board.getBoardTitle();
		board.getBoardContent();
		board.getMemberId();
		
		return boardMapper.insertBoard(board);
	}
		
	// 수정
	public int modifyBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	
	// 삭제
	public int deleteBoard(Board board) {
		return boardMapper.deleteBoard(board);
	}
	
}
