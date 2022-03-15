package com.ezen.spg12.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class bbsDto {
	private int id;
	
	@NotNull(message="Writer is NUll")
	@NotEmpty(message="Writer is Empty")
	private String writer;

	@NotNull(message="Title is NUll")
	@NotEmpty(message="Title is Empty")
	private String title;

	@NotNull(message="Content is NUll")
	@NotEmpty(message="Content is Empty")
	private String content;
}
