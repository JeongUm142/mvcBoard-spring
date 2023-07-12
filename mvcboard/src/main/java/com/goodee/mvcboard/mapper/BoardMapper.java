package com.goodee.mvcboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Board;

@Mapper
public interface BoardMapper {
	// local_name컬럼과 count()를 반환
	List<Map<String, Object>> selectLocalNameList(); // 추상메소드
	
	// board별로 리스트 출력
	// mybatis 메서드는 매개값을 하나만 허용
	// param : Map<Object, Object> map =>
	List<Board> selectBoardListByPage(Map<String, Object> paramMap);
	
	// 전체 행의 수
	int selectBoardCount();
	
	// 상세보기
	Board selectBoardOne(Board board);
	
	// 추가 
	int insertBoard(Board board);
	
	// 수정 
	int updateBoard(Board board);
	
	// 삭제
	int deleteBoard(Board board);
}
