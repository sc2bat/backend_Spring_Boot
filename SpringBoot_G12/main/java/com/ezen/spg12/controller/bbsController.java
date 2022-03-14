package com.ezen.spg12.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.spg12.dao.IbbsDao;
import com.ezen.spg12.dto.bbsDto;

@Controller
public class bbsController {
	@Autowired
	IbbsDao bdao;
	
	@RequestMapping("/")
	public String userlistPage(Model md) {
		md.addAttribute("list", bdao.list());
		return "list";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "writeForm";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute("dto")@Valid bbsDto bdto, BindingResult result, Model model) {
		if(result.hasErrors()) {
			if(result.getFieldError("writer") != null) {
				model.addAttribute("msg", result.getFieldError("writer").getDefaultMessage());
			}else if(result.getFieldError("title") != null) {
				model.addAttribute("msg", result.getFieldError("title").getDefaultMessage());
			}else if(result.getFieldError("content") != null) {
				model.addAttribute("msg", result.getFieldError("content").getDefaultMessage());
			}
			return "writeForm";
		}else {
			bdao.write(bdto);
//			bdao.write(bdto.getWriter(), bdto.getTitle(), bdto.getContent());
			return "redirect:/";
		}
	}
	
	@RequestMapping("/view")
	public String view(@RequestParam("id")int id, Model md) {
		md.addAttribute("dto", bdao.view(id));
		return "view";
	}
	
	@RequestMapping("/updateForm")
	public String updateForm() {
		return "updateForm";
	}
	
	/*
	@RequestMapping("/update")
	public String update(@ModelAttribute("dto")@Valid bbsDto bdto, BindingResult result, Model md) {
		if(result.hasErrors()) {
			if(result.getFieldError("writer") != null) {
				md.addAttribute("msg", result.getFieldError("writer").getDefaultMessage());
			}else if(result.getFieldError("title") != null) {
				md.addAttribute("msg", result.getFieldError("title").getDefaultMessage());
			}else if(result.getFieldError("content") != null) {
				md.addAttribute("msg", result.getFieldError("content").getDefaultMessage());
			}
			return "updateForm";
		}else {
			bdao.update(bdto.getId());
			return "redirect:/";
		}
	}*/
	
	
}
