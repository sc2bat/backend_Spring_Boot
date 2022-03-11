package com.ezen.spg04;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormDataController {
	@RequestMapping("/")
	public String root() throws Exception{
		return "testForm";
	}
	
	@RequestMapping("/test1")
	public String test1(HttpServletRequest rq, Model md) {
		String id = rq.getParameter("id");
		String name = rq.getParameter("name");
		
		md.addAttribute("id", id);
		md.addAttribute("name", name);
		
		return "test1";
	}
	
	// @RequestParam(value="id", required=false)String id // Null �� ����
	@RequestMapping("/test2")
	public String test2(Model md, @RequestParam("id")String id,  @RequestParam("name")String name) {
		md.addAttribute("id", id);
		md.addAttribute("name", name);
		
		return "test2";
	}
	
	// form ���� ������ �˾Ƽ� ã�Ƽ� ��
	/*
	@RequestMapping("/test3")
	public String test3(Model md, Member member) {
		md.addAttribute("id", member.getId());
		md.addAttribute("name", member.getName());
		
		return "test3";
	}*/
	
	@RequestMapping("/test3_1")
	public String test3_1(@ModelAttribute("mbr")Member member) {
		// �Ķ���Ϳ� ��ġ�ϴ� ��������� �ִ� ��ü�� ����� �� ��ü�� �Ű������� ����� �� �վ��ϴ�
		// ���޵� ���Ĥ������ʹ� �Ű������� �ڵ����� �Էµ˴ϴ�
		// ModelAttribute ������̼��� ���� �ڵ����� Model ���� ����ǰ� �� �� �ֽ��ϴ�
		// �� �Ű������� ����� ������̼��� Model.addAttribute("member", member) ��� �� �Ͱ� �����ϴ�
		
		return "test3_1";
	}
	
	// �ּҿ� �־ �����Ͽ��� ��� node.js ���� ���Ǳ⵵ ��
	//http://localhost:8070/test4/qwer/asdf
	@RequestMapping("/test4/{id}/{name}")
	public String getStudent(@PathVariable String id, @PathVariable String name, Model md) {
		md.addAttribute("id", id);
		md.addAttribute("name", name);
		return "test4";
	}
	
	
	
}
