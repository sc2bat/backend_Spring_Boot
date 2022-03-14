package com.ezen.spg10.dao;

import java.util.List;

import com.ezen.spg10.bbsDto;

public interface IBbsDao {
	
	public List<bbsDto> list(); // 게시물 전체조회 - 매개변수 없음, 리턴값은 List<bbsDto> 형
	public int write(bbsDto bdto); // 게시물 쓰기 - 매개변수 bbsDto형, 리턴 int
	public int update(bbsDto bdto); // 수정 - 매개변수 bbsDto 형, 리턴 int
	public int delete(int id); // 삭제 매개변수 int, 리턴 int
	public bbsDto view(int id); // 게시물 하나보기 - 매개변수 int, 리턴 bbsDto
}
