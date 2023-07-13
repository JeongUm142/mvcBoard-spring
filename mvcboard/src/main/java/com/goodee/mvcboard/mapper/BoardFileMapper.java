package com.goodee.mvcboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Boardfile;

@Mapper
public interface BoardFileMapper {
	int insertBoardfile(Boardfile boardfile);
	
	List<Boardfile> selectBoardfile(int boardNo);
	
	int deletBoardfile(int boardNo);
}
