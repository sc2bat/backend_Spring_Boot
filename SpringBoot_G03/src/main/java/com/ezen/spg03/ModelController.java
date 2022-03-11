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
		// ���ϵǴ�  jsp ���ϱ����� �ش� ������ ������ �� �ִ� 1ȸ�� �ڷ� ���� ����
		md.addAttribute("name1", "1ȫ�浿1");
		
		// request �� ������ ���ϴ� ���������� ������ ����� �ٽ� ������ �� �ִ� ���޵���
		rq.setAttribute("name2", "2ȫ�浿2");
		return "test1";
	}
	@RequestMapping("/test2")
	public String test2(HttpServletRequest rq, Model md) {
		md.addAttribute("name1", "1ȫ�浿1");
		
		rq.setAttribute("name2", "2ȫ�浿2");
		
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
		
		// �ƹ��͵� ���޵��� �ʴ´�
		
		// �������� ������
		
		return "/test2";
	}
	
	@RequestMapping("/mv")
	public ModelAndView test4() {
		// �����Ϳ� �並 ���ÿ� ������ ����
		ModelAndView mv = new ModelAndView();
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
		mv.addObject("lists", list);
		mv.addObject("ObjectTest", "�׽�Ʈ");
		mv.addObject("name", "ȫ�浿");
		mv.setViewName("view/myView");
		
		return mv;
	}
	
}
