package com.ezen.spg15.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReplyVO {
	private int num;
	private int boardnum;
	private String userid;
	private Timestamp writedate;
	@NotNull(message="reply content null")
	@NotEmpty(message="reply content 를 입력하세요")
	private String content;
}
