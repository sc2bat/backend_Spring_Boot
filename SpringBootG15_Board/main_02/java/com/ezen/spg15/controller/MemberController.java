package com.ezen.spg15.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spg15.dto.MemberVO;
import com.ezen.spg15.service.MemberService;

@Controller
public class MemberController {
	
	private ModelAndView mv;
	
	@Autowired
	MemberService ms;
	
	@RequestMapping("/")
	public String index() {
		return "member/loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("dto")@Valid MemberVO membervo, BindingResult result, 
			HttpServletRequest rq, Model md) {
		if(result.getFieldError("userid") != null) {
			md.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
			return "member/loginForm";
		}else if(result.getFieldError("pwd") != null) {
			md.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			return "member/loginForm";
		}
		
		MemberVO mvo = ms.getMember(membervo.getUserid());
		
		if(mvo == null) {
			md.addAttribute("message", "id �� ����");
			return "member/loginForm";
		}else if(mvo.getPwd() == null) {
			md.addAttribute("message", "pwd �� ����");
			return "member/loginForm";
		}else if(!mvo.getPwd().equals(membervo.getPwd())) {
			md.addAttribute("message", "pwd �� �ٸ�");
			return "member/loginForm";
		}else if(mvo.getPwd().equals(membervo.getPwd())) {
			rq.getSession().setAttribute("loginUser", mvo);
			return "redirect:/main";
		}else {
			md.addAttribute("message", "�� �� ���� ������ �α��� �Ұ�");
			return "member/loginForm";
		}
	}
	
	
	@RequestMapping("/memberJoinForm")
	public String memberJoinForm() {
		return "member/memberJoinForm";
	}
	
	@RequestMapping("/idcheck")
	public ModelAndView idcheck(@RequestParam("userid")String userid, Model md) {
		mv = new ModelAndView();
		MemberVO mvo = (MemberVO)ms.getMember(userid);
		if(mvo == null) {
			mv.addObject("result", -1);
		}else {
			mv.addObject("result", 1);
		}
		mv.addObject("userid", userid);
		mv.setViewName("member/idcheck");
		return mv;
	}
	/*
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public ModelAndView memberJoin(@ModelAttribute("dto")@Valid MemberVO membervo, BindingResult result,
			@RequestParam("re_id")String re_id, @RequestParam("pwd_check")String pwd_check, Model md) {
		mv = new ModelAndView();
		//Validation ���� ���۵� ������ �����ϰ�, null or empty ��� memeberJoinForm.jsp �� ���ư�
		if(result.getFieldError("userid") != null) {
			md.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
			mv.setViewName("member/memberJoinForm");
		}else if(result.getFieldError("pwd") != null) {
			md.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			md.addAttribute("re_id", re_id);
			mv.setViewName("member/memberJoinForm");
		}else if(result.getFieldError("name") != null) {
			md.addAttribute("message", result.getFieldError("name").getDefaultMessage());
			mv.setViewName("member/memberJoinForm");
		}else if(result.getFieldError("phone") != null) {
			md.addAttribute("message", result.getFieldError("phone").getDefaultMessage());
			mv.setViewName("member/memberJoinForm");
		}else if(result.getFieldError("email") != null) {
			md.addAttribute("message", result.getFieldError("email").getDefaultMessage());
			mv.setViewName("member/memberJoinForm");
		}else {
			ms.insertMember(membervo);
			md.addAttribute("message", "sign up complete");
			mv.setViewName("member/loginForm");
		}// MemberVO �� �ڵ����� ������� �ʴ� �����μ� - pwd_check, re_id ���� ������ ������ ���޹ް�,
		// ������ �̻������� üũ�ϰ� �̻��� ������ memberJoinForm.jsp �� �ǵ��ư�����
		// ��� �̻��� ���ٰ� ������ �Ǹ� ȸ�������ϰ�, ȸ������ �Ϸ� �޼����� �Բ� loginForm.jsp �� �ǵ��ư�����
		return mv;
	}*/
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public ModelAndView memberJoin(@ModelAttribute("dto")@Valid MemberVO membervo, BindingResult result,
			@RequestParam("re_id")String re_id, @RequestParam("pwd_check")String pwd_check) {
		mv = new ModelAndView();
		mv.setViewName("member/memberJoinForm");
		
		if(re_id != null && !re_id.equals("")) {
			mv.addObject("re_id", re_id);
		}
		
		if(result.getFieldError("userid") != null) {
			mv.addObject("message", result.getFieldError("userid").getDefaultMessage());
		}else if(!membervo.getUserid().equals(re_id)) {
			mv.addObject("message", "re_id null / id check");
		}else if(result.getFieldError("pwd") != null) {
			mv.addObject("message", result.getFieldError("pwd").getDefaultMessage());
		}else if(!membervo.getPwd().equals(pwd_check)) {
			mv.addObject("message", "pwd != pwd_check");
		}else if(result.getFieldError("name") != null) {
			mv.addObject("message", result.getFieldError("name").getDefaultMessage());
		}else if(result.getFieldError("phone") != null) {
			mv.addObject("message", result.getFieldError("phone").getDefaultMessage());
		}else if(result.getFieldError("email") != null) {
			mv.addObject("message", result.getFieldError("email").getDefaultMessage());
		}else {
			ms.insertMember(membervo);
			mv.addObject("message", "sign up complete");
			mv.setViewName("member/loginForm");
		}
		return mv;
	}
	
	@RequestMapping("/memberEditForm")
	public ModelAndView memberEditForm(HttpServletRequest rq) {
		mv = new ModelAndView();
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			mv.setViewName("member/loginForm");
		}else {
			mv.addObject("dto", mvo);
			mv.setViewName("member/memberEditForm");
		}
		return mv;
	}
	
	@RequestMapping(value="/memberEdit", method=RequestMethod.POST)
	public String memberEdit(@ModelAttribute("dto")@Valid MemberVO membervo, BindingResult result,
			@RequestParam("pwd_check")String pwd_check, HttpServletRequest rq, Model md) {
		String url = "member/memberEditForm";
		if(result.getFieldError("pwd") != null) {
			md.addAttribute("message", "pwd null");
		}else if(result.getFieldError("name") != null) {
			md.addAttribute("message", "name null");
		}else if(!membervo.getPwd().equals(pwd_check)) {
			md.addAttribute("message", "pwd != pwd_check");
		}else {
			ms.updateMember(membervo);
			rq.getSession().setAttribute("loginUser", membervo);
			url = "redirect:/main";
		}
		return url;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest rq) {
//		rq.getSession().removeAttribute("loginUser");
//		return "member/loginForm";
		rq.getSession().invalidate();
		return "redirect:/";
	}

}
