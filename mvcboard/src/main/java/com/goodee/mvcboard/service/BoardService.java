package com.goodee.mvcboard.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.mvcboard.mapper.BoardFileMapper;
import com.goodee.mvcboard.mapper.BoardMapper;
import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

@Service
@Transactional
public class BoardService {
	// 자료구조 필터링, DAO
	@Autowired 
	private BoardMapper boardMapper;
	
	@Autowired 
	private BoardFileMapper boardfileMapper;
	
	// rest api chart 그래프 호출
	public List<Map<String, Object>> getLocalNameList(){
		return boardMapper.selectLocalNameList();
	}
	
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
	// 상품이미지출력 
	public List<Boardfile> selectBoardfile(int boardNo) {
		return boardfileMapper.selectBoardfile(boardNo);
	}
	
	// 상세보기
	public Board boardOne(Board board) {
		return boardMapper.selectBoardOne(board);
	}
	
	// 추가
	public int addBoard(Board board, String path) {
		int row = boardMapper.insertBoard(board);

		// board입력 성공 && 첨부된 파일이 1개 이상 있다면
		List<MultipartFile> fileList = board.getMultipartFile();
		if(row == 1 && fileList != null) {
			int boardNo = board.getBoardNo(); // row보다 늦게 호출되어야 함
			
			for(MultipartFile mf : fileList) {// 첨부된 파일의 수만큼 반복
				if (mf.getSize() > 0) {
					Boardfile bf = new Boardfile();
					bf.setBoardNo(boardNo); // 부모 키값
					bf.setFilesize(mf.getSize()); // 파일 사이즈
					bf.setFiletype(mf.getContentType()); // 파일 타입(MIME를 사용해서 텍스트로 변환)
					bf.setOriginFilename(mf.getOriginalFilename()); // 파일 원본 이름
					// 저장될 파일 이름
					// 확장자
					String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
					// 새로운 이름 + 확장자
					bf.setSaveFilename(UUID.randomUUID().toString().replace("-", "") + ext);
					
					// 테이블의 저장
					boardfileMapper.insertBoardfile(bf);
					
					// 파일 저장(저장위치 -> path변수 안에)
					// path위치에 저장파일이름으로 빈파일을 생성
					File f = new File(path + bf.getSaveFilename()); 
					 
					// 빈파일에 첨부된 파일의 스트림을 주입
					try {
						mf.transferTo(f);
					} catch (IllegalStateException | IOException e) {
						// 이럴경우 예의가 먹힘 -> 트랜젝션처리가 안됨
						e.printStackTrace();
						
						// 그래서 try...catch를 강요하지 않는 다른 예외발생 -> runtime...
						throw new RuntimeException();
					}
				}
			}
		}
		return row;
	}
		
	// 수정
	public int modifyBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	
	// 삭제
	public int deleteBoard(Board board, String path) {
		List<Boardfile> selectBoardfile = boardfileMapper.selectBoardfile(board.getBoardNo());
		
		if(selectBoardfile != null) {
			for(Boardfile bf : selectBoardfile) {// 첨부된 파일의 수만큼 반복
				File f = new File(path + bf.getSaveFilename());
				if(f.exists()) {
					f.delete();
				}
			}
		}
		int row = boardfileMapper.deletBoardfile(board.getBoardNo());
		
		row = boardMapper.deleteBoard(board);
			
		return row;
	}
	
}
