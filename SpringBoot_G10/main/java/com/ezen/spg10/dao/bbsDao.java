package com.ezen.spg10.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezen.spg10.bbsDto;

@Repository
public class bbsDao implements IBbsDao{
	
	@Autowired
	private JdbcTemplate tpl;

	//implements 후 오버라이드
	@Override
	public List<bbsDto> list() {
		String sql = "SELECT * FROM bbs";
//		List<bbsDto> list = tpl.query(sql, new BeanPropertyRowMapper<bbsDto>(bbsDto.class));
//		return list;
		return tpl.query(sql, new BeanPropertyRowMapper<bbsDto>(bbsDto.class));
	}

	@Override
	public int write(bbsDto bdto) {
		String sql = "INSERT INTO bbs VALUES(bbs_seq.nextVal, ?, ?, ?)";
		int result = tpl.update(sql, bdto.getWriter(), bdto.getTitle(), bdto.getContent());
		return result;
	}

	@Override
	public int update(bbsDto bdto) {
		String sql = "UPDATE bbs SET writer=?, title=?, content=? WHERE id=?";
		return tpl.update(sql, bdto.getWriter(), bdto.getTitle(), bdto.getContent(), bdto.getId());
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM bbs WHERE id=?";
		return tpl.update(sql, id);
	}

	// queryForObject
	@Override
	public bbsDto view(int id) {
		String sql = "SELECT * FROM bbs WHERE id=?";
//		bbsDto dto = tpl.queryForObject(sql, new BeanPropertyRowMapper<bbsDto>(bbsDto.class), id);
//		return dto;
		return tpl.queryForObject(sql, new BeanPropertyRowMapper<bbsDto>(bbsDto.class), id);
	}
}
