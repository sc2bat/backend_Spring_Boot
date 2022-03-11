package com.ezen.spg03;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ModelController {
	
	String name4;
	String name5;
	
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Model * View";
	}
	
	@RequestMapping("/test1")
	public String test1(HttpServletRequest rq, Model md) {
		// 리턴되는  jsp 파일까지만 해당 내용을 전달할 수 있는 1회용 자료 전달 도구
		md.addAttribute("name1", "1홍길동1");
		
		// request 의 수명이 다하는 순간까지는 내용을 살려서 다시 전달할 수 있는 전달도구
		rq.setAttribute("name2", "2홍길동2");
		return "test1";
	}
	@RequestMapping("/test2")
	public String test2(HttpServletRequest rq, Model md) {
		md.addAttribute("name1", "1홍길동1");
		
		rq.setAttribute("name2", "2홍길동2");
		
		rq.setAttribute("model", md);
		name4 = "name4";
		name5 = "name5";
		return "redirect:/test3";
	}
	
	@RequestMapping("/test3")
	public String test3(HttpServletRequest rq, Model md) {
		
		
		String name1 = (String)md.getAttribute("name1");
		String name2 = (String)rq.getAttribute("name2");
		System.out.println("name1 " + name1 + " name2 " + name2); // name1 null name2 null

		/*md.addAttribute("name1", name1);
		rq.setAttribute("name2", name2);
		md = (Model)rq.getAttribute("model");
		String name2 = (String)rq.getAttribute("name2");*/
		rq.setAttribute("name2", name2);
		
		md.addAttribute("name1", name4);
		rq.setAttribute("name2", name5);
		
		// 아무것도 전달되지 않는다
		
		// 전역변수 선언후
		
		return "/test2";
	}
	
	@RequestMapping("/mv")
	public ModelAndView test4() {
		// 데이터와 뷰를 동시에 설정이 가능
		ModelAndView mv = new ModelAndView();
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		mv.addObject("lists", list);
		mv.addObject("ObjectTest", "테스트");
		mv.addObject("name", "홍길동");
		mv.setViewName("view/myView");
		
		return mv;
	}
	
}
