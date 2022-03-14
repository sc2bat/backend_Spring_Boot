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
	// 해당 클래스를 servlet-context.xml 에 bean 으로 넣어놓아야하는 방식
	@Autowried
	UserDao(ComboPooledDataSource ds){ 
		
		this.tpl = new JdbcTemplate(ds);
	}*/
	
	

	public List<UserDto> list() {
		String sql = "SELECT * FROM myuser";
//		List<UserDto> list = tpl.query(sql, new RowMapper<UserDto>() { });
		List<UserDto> list = tpl.query(sql, new BeanPropertyRowMapper<UserDto>(UserDto.class));
		// ResultSet 사용없이 검색결과 레코드의 필드를 Dto 변수에 놓고 list 에 add 동작을 실행합니다
		// 결과 레코드 갯수만큼 실행합니다
		
		return list;
	}
	
}
