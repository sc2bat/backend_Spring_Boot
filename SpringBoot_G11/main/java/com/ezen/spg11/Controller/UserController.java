package com.ezen.spg11.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.spg11.dao.IUserDao;
import com.ezen.spg11.dto.UserDto;

@Controller
public class UserController {
	
	@Autowired
	IUserDao udao;
	
	@RequestMapping("/")
	public String root(Model md) {
		List<UserDto> list = udao.list();
		// �������̽��� �߻�޼��帣 ȣ���ϸ�, �������̽��� �ش� �޼��鸣 xml ���Ͽ��� �������ִ� ����.
		// xml ���� �޼��尡 �ƴ� ���డ���� ���𰡰� ����
		md.addAttribute("users", list);
		return "userlist";
	}
}
