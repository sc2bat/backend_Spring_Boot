package com.ezen.spg15.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MemberVO {
	@NotNull(message="userid null")
	@NotEmpty(message="userid �� �Է��ϼ���")
	private String userid;
	@NotNull(message="pwd null")
	@NotEmpty(message="pwd �� �Է��ϼ���")
	private String pwd;
	@NotNull(message="name null")
	@NotEmpty(message="name �� �Է��ϼ���")
	private String name;
	@NotNull(message="email null")
	@NotEmpty(message="email �� �Է��ϼ���")
	private String email;
	@NotNull(message="phone null")
	@NotEmpty(message="phone �� �Է��ϼ���")
	private String phone;
}
