package com.ezen.spg15.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BoardVO {

	private int num;
	private String userid;
	@NotNull(message="pass null")
	@NotEmpty(message="pass �� �Է��ϼ���")
	private String pass;
	private String email;
	@NotNull(message="title null")
	@NotEmpty(message="title �� �Է��ϼ���")
	private String title;
	@NotNull(message="board content null")
	@NotEmpty(message="board content �� �Է��ϼ���")
	private String content;
	private int readcount;
	private Timestamp writedate;
	private int replycnt;
	private String imgfilename;
}
