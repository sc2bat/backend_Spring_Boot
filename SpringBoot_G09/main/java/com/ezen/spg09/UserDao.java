package com.ezen.spg09;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private JdbcTemplate tpl;
	
	/*
	// �ش� Ŭ������ servlet-context.xml �� bean ���� �־���ƾ��ϴ� ���
	@Autowried
	UserDao(ComboPooledDataSource ds){ 
		
		this.tpl = new JdbcTemplate(ds);
	}*/
	
	

	public List<UserDto> list() {
		String sql = "SELECT * FROM myuser";
//		List<UserDto> list = tpl.query(sql, new RowMapper<UserDto>() { });
		List<UserDto> list = tpl.query(sql, new BeanPropertyRowMapper<UserDto>(UserDto.class));
		// ResultSet ������ �˻���� ���ڵ��� �ʵ带 Dto ������ ���� list �� add ������ �����մϴ�
		// ��� ���ڵ� ������ŭ �����մϴ�
		
		return list;
	}
	
}
