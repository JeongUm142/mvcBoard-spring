package com.goodee.mvcboard.vo;

import lombok.Data;

@Data
public class Boardfile {
	private int boardfileNo;
	private int boardNo;
	private String originFilename;
	private String saveFilename;
	private String filetype;
	private long filesize;
}
