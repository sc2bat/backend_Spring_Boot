package com.ezen.spg10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.spg10.dao.bbsDao;

@Controller
public class bbsController {
	
	@Autowired
	bbsDao bdao;
	
	// 최초페이지는 리스트 조회, list.jsp 로 이동
	@RequestMapping("/")
	public String root(Model model) {
		model.addAttribute("bbslist", bdao.list());
		return "list";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm(Model md) {
		return "writeForm";
	}
	
	/*
	@RequestMapping("/write")
	public String wirte(HttpServletRequest rq, Model md) {
		bbsDto bdto = new bbsDto();
		bdto.setWriter(rq.getParameter("writer"));
		bdto.setTitle(rq.getParameter("title"));
		bdto.setContent(rq.getParameter("content"));
		int result = bdao.write(bdto);
		md.addAttribute("result", "1");
		return "redirect:/";
	}*/
	@RequestMapping("/write")
	public String wirte(bbsDto bdto, Model md) {
		int result = 0;
		result = bdao.write(bdto);
		if(result == 0) {
			md.addAttribute("message", "write failed");
		}else {
			md.addAttribute("message", "write success");
		}
		return "redirect:/";
	}
	
	@RequestMapping("/view")
	public String view(@RequestParam("id")int id, Model md) {
		md.addAttribute("dto", bdao.view(id));
		return "view";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")int id) {
		bdao.delete(id);
		return "redirect:/";
	}
	
	@RequestMapping("/updateForm")
	public String updateForm(@RequestParam("id")int id) {
//		bbsDto bdto = bdao.getOne(id);
		return "updateForm";
	}
	
	
	
	
	
	
}
