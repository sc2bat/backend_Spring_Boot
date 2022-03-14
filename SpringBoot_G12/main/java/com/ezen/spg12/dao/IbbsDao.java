package com.ezen.spg12.dao;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg12.dto.bbsDto;

@Mapper
public interface IbbsDao {

	public List<bbsDto> list();

//	public void write(String writer, String title, String content);
	void write(bbsDto bdto);

	public bbsDto view(int id);

//	public bbsDto update(int id);
	
}
