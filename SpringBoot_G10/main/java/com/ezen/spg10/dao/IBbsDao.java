package com.ezen.spg10.dao;

import java.util.List;

import com.ezen.spg10.bbsDto;

public interface IBbsDao {
	
	public List<bbsDto> list(); // �Խù� ��ü��ȸ - �Ű����� ����, ���ϰ��� List<bbsDto> ��
	public int write(bbsDto bdto); // �Խù� ���� - �Ű����� bbsDto��, ���� int
	public int update(bbsDto bdto); // ���� - �Ű����� bbsDto ��, ���� int
	public int delete(int id); // ���� �Ű����� int, ���� int
	public bbsDto view(int id); // �Խù� �ϳ����� - �Ű����� int, ���� bbsDto
}
