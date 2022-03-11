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
	
	// @RequestParam(value="id", required=false)String id // Null 값 허용시
	@RequestMapping("/test2")
	public String test2(Model md, @RequestParam("id")String id,  @RequestParam("name")String name) {
		md.addAttribute("id", id);
		md.addAttribute("name", name);
		
		return "test2";
	}
	
	// form 에서 보내면 알아서 찾아서 들어감
	/*
	@RequestMapping("/test3")
	public String test3(Model md, Member member) {
		md.addAttribute("id", member.getId());
		md.addAttribute("name", member.getName());
		
		return "test3";
	}*/
	
	@RequestMapping("/test3_1")
	public String test3_1(@ModelAttribute("mbr")Member member) {
		// 파라미터와 일치하는 멤버변수가 있는 객체를 만들고 이 객체를 매개변수로 사용할 수 잇씁니다
		// 전달된 ㄹ파ㅏㄹ미터는 매개변수에 자동으로 입력됩니다
		// ModelAttribute 어노테이션을 통해 자동으로 Model 에도 저장되게 할 수 있습니다
		// 위 매개변수에 사용한 어노테이션은 Model.addAttribute("member", member) 라고 쓴 것과 같습니다
		
		return "test3_1";
	}
	
	// 주소에 넣어서 전달하여서 사용 node.js 에서 사용되기도 함
	//http://localhost:8070/test4/qwer/asdf
	@RequestMapping("/test4/{id}/{name}")
	public String getStudent(@PathVariable String id, @PathVariable String name, Model md) {
		md.addAttribute("id", id);
		md.addAttribute("name", name);
		return "test4";
	}
	
	
	
}
