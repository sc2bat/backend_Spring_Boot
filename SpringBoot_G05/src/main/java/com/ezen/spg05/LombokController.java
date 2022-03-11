package com.ezen.spg05;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LombokController {
//	@RequestMapping("/")
//	public @ResponseBody String root() throws Exception{
//		return "Lombok ����ϱ�";
//	}
	
	@RequestMapping("/")
	public String root() throws Exception{
		return "testForm";
	}

	
	@RequestMapping("/test")
	public String test1(Member member, Model md) {
		// ModelAttribute ������̼��� ������� ������, �𵨿� ����Ǵ� �̸� member ��� �̸����� �����Ǿ� ����˴ϴ�.
		// �� �� ��ü�� �̸��� �ݵ�� Ŭ���� �̸��� �ҹ��ڷ� ��Ȯ�� ����� �մϴ�.
		// �����̸� member(O) mem(X) mbr(X)
		
		System.out.println(member.getId() + " " + member.getName());
		return "test1";
	}
	
}
