package com.ezen.spg15.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import com.ezen.spg15.dto.BoardVO;
import com.ezen.spg15.dto.MemberVO;
import com.ezen.spg15.dto.Paging;
import com.ezen.spg15.service.BoardService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class BoardController {
	
	private ModelAndView mv;
	
	@Autowired
	BoardService bs;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("/main")
	public ModelAndView goMain(HttpServletRequest rq) {
		ModelAndView mav = new ModelAndView();
		HttpSession ss = rq.getSession();
		if(ss.getAttribute("loginUser") == null) {
			mav.setViewName("member/loginForm");
		}else {
			int page = 1;
			String PageParam = rq.getParameter("page");
			if(PageParam != null) {
				page = Integer.parseInt(PageParam);
				ss.setAttribute("page", page);
			}else if(ss.getAttribute("page") != null) {
				page = (int)ss.getAttribute("page");
			}else {
				ss.removeAttribute("page");
			}
			Paging paging = new Paging();
			paging.setPage(page);
//			int count = bs.getAllCount();
//			paging.setTotalCount(count);
			paging.setTotalCount(bs.getAllCount());
			paging.paging();
			
			mav.addObject("boardList", bs.getBoardListAll(paging));
			mav.addObject("paging", paging);
			mav.setViewName("board/main");
		}
		return mav;
	}
	
	@RequestMapping("/boardWriteForm")
	public String boardWriteForm(HttpServletRequest rq) {
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
			return "board/boardWriteForm";
		}
	}
	/*
	@RequestMapping(value="/boardWrite", method=RequestMethod.POST)
	public String boardWrite(@ModelAttribute("dto")@Valid BoardVO boardvo, BindingResult result,
			HttpServletRequest rq, Model md) {
		
		
		// request �� multipart �� ���ޱ⿡ ���� null �� ������ ������ Valid �� ����
		System.out.println("pass : " + boardvo.getPass());
		System.out.println("title : " + boardvo.getTitle());
		System.out.println("content : " + boardvo.getContent());
		
		if(result.hasErrors()) {
			System.out.println("pass : " + result.getFieldError("pass").getDefaultMessage());
			System.out.println("title : " + result.getFieldError("title").getDefaultMessage());
			System.out.println("content : " + result.getFieldError("content").getDefaultMessage());
		}
		
		String path = context.getRealPath("/upload");
		
		try {
			MultipartRequest multi = new MultipartRequest(rq, path, 5*1024*1024, "UTF-8", 
					new DefaultFileRenamePolicy());
			BoardVO bdto = new BoardVO();
			bdto.setUserid(multi.getParameter("userid"));
			bdto.setPass(multi.getParameter("pass"));
			bdto.setEmail(multi.getParameter("email"));
			bdto.setTitle(multi.getParameter("title"));
			bdto.setContent(multi.getParameter("content"));
			
			md.addAttribute("dto", bdto);
			// MultipartRequest �� �����μ��� ��� ���޹��� �Ŀ� Validation �� �����մϴ�
			if(bdto.getPass() == null && bdto.getPass().equals("")) {
				md.addAttribute("message", "pwd null");
				return "board/boardWriteForm";
			}else if(bdto.getTitle() == null && bdto.getTitle().equals("")) {
				md.addAttribute("message", "title null");
				return "board/boardWriteForm";
			}else if(bdto.getContent() == null && bdto.getContent().equals("")) {
				md.addAttribute("message", "content null");
				return "board/boardWriteForm";
			}
			
			if(multi.getFilesystemName("imgfilename") == null) {
				bdto.setImgfilename("");
			}else {
				bdto.setImgfilename(multi.getFilesystemName("imgfilename"));
			}
			bs.insertBoard(bdto);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/main";
	}*/
	
	@RequestMapping("/selectImg")
	public String selectImg() {
		return "board/selectImg";
	}
	
	@RequestMapping(value="/fileUpload", method=RequestMethod.POST)
	public String fileUpload(Model md, HttpServletRequest rq) {
		
		try {
			MultipartRequest mt = new MultipartRequest(
					rq, context.getRealPath("/upload"), 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			//���۵� ������ ���ε� �ǰ�, ���� �̸��� �𵨿� �����մϴ�
			md.addAttribute("uploadImage", mt.getFilesystemName("uploadImage"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "board/completeUpload";
	}
	
	@RequestMapping(value="/boardWrite", method=RequestMethod.POST)
	public String boardWrite(@ModelAttribute("dto")@Valid BoardVO boardvo, BindingResult result,
			HttpServletRequest rq, Model md) {
		String url = "board/boardWriteForm";
		if(result.getFieldError("pass") != null) {
			md.addAttribute("message", result.getFieldError("pass").getDefaultMessage());
		}else if(result.getFieldError("title") != null) {
			md.addAttribute("message", result.getFieldError("title").getDefaultMessage());
		}else if(result.getFieldError("content") != null) {
			md.addAttribute("message", result.getFieldError("content").getDefaultMessage());
		}else {
			bs.insertBoard(boardvo);
			url = "redirect:/main";
		}
		return url;
	}
	
	@RequestMapping("/boardView")
	public ModelAndView boardView(@RequestParam("num")int num) {
		mv = new ModelAndView();
		
		HashMap<String, Object> hm = bs.boardView(num);
		// bs.boardView(num); ���� ��ȸ�� +1, �Խù� ��ȸ, ��� ����Ʈ ��ȸ
		
		mv.addObject("board", hm.get("board"));
		mv.addObject("replyList", hm.get("replyList"));
		mv.setViewName("board/boardView");
		return mv;
	}
	
	@RequestMapping("/boardViewWithOutReadCount")
	public ModelAndView boardViewWithOutReadCount(@RequestParam("num")int num) {
		mv = new ModelAndView();
		HashMap<String, Object> hm = bs.boardViewWithOutReadCount(num);
		mv.addObject("board", hm.get("board"));
		mv.addObject("replyList", hm.get("replyList"));
		mv.setViewName("board/boardView");
		return mv;
	}
	
	@RequestMapping("/addReply")
	public String addReply(@RequestParam("boardnum")int num, @RequestParam("userid")String userid, 
			@RequestParam("content")String content, HttpServletRequest rq) {
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
			bs.addReply(num, userid, content);
			return "redirect:/boardViewWithOutReadCount?num=" + num;
		}
	}
	
	
	@RequestMapping("/deleteReply")
	public String deleteReply(@RequestParam("num")int num, @RequestParam("boardnum")int boardnum, HttpServletRequest rq) {
		MemberVO mvo = (MemberVO)rq.getSession().getAttribute("loginUser");
		if(mvo == null) {
			return "member/loginForm";
		}else {
			bs.deleteReply(num);
			return "redirect:/boardViewWithOutReadCount?num=" + boardnum;
		}
	}
	
	
	@RequestMapping("/boardEditForm")
	public String boardEditForm(@RequestParam("num")int num, Model model) {
		model.addAttribute("num", num);
		return "board/boardCheckPassForm";
	}
	
	@RequestMapping("/boardEdit")
	public String boardEdit(@RequestParam("num")int num, @RequestParam("pass")String pass, Model model) {
		BoardVO bdto = bs.getBoardOne(num);
		model.addAttribute("num", num);
		if(!bdto.getPass().equals(pass)) {
			model.addAttribute("message", "pass �� �ٸ�");
			return "board/boardCheckPassForm";
		}else {
			return "board/boardCheckPass";
		}
	}
	
	@RequestMapping("/boardUpdateForm")
	public String boardUpdateForm(@RequestParam("num")int num, Model model) {
		model.addAttribute("dto", bs.getBoardOne(num));
		return "board/boardEditForm";
	}
	
	/*
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public ModelAndView boardUpdate(@RequestParam("board")@Valid BoardVO boardvo, BindingResult result,
			@RequestParam("oldfilename")String oldfilename) {
		mv = new ModelAndView();
		mv.setViewName("board/boardEditForm");
		if(result.getFieldError("pass") != null) {
			mv.addObject("message", result.getFieldError("pass").getDefaultMessage());
		}else if(result.getFieldError("title") != null) {
			mv.addObject("message", result.getFieldError("title").getDefaultMessage());
		}else if(result.getFieldError("content") != null) {
			mv.addObject("message", result.getFieldError("content").getDefaultMessage());
		}else {
			if(boardvo.getImgfilename().equals("") || boardvo.getImgfilename() == null) {
				boardvo.setImgfilename(oldfilename);
			}
			bs.updateBoard(boardvo);
			mv.setViewName("redirect:/boardViewWithOutReadCount?num=" + boardvo.getNum());
		}
		return mv;
	}*/
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdate(@ModelAttribute("dto")@Valid BoardVO boardvo, BindingResult result,
			@RequestParam("oldfilename")String oldfilename, Model model) {
		String url = "board/boardEditForm";
		if(result.getFieldError("pass") != null) {
			model.addAttribute("message", "pass null");
		}else if(result.getFieldError("title") != null) {
			model.addAttribute("message", "title null");
		}else if(result.getFieldError("content") != null) {
			model.addAttribute("message", "content null");
		}else {
			if(boardvo.getImgfilename().equals("") || boardvo.getImgfilename() == null) {
				boardvo.setImgfilename(oldfilename);
			}
			bs.updateBoard(boardvo);
			url = "redirect:/boardViewWithOutReadCount?num=" + boardvo.getNum();
		}
		return url;
	}
	

	@RequestMapping("/boardDeleteForm")
	public String boardDeleteForm(@RequestParam("num")int num, Model model) {
		model.addAttribute("num", num);
		return "board/boardCheckPassForm";
	}
	
	@RequestMapping("/boardDelete")
	public String baordDelete(Model model, @RequestParam("num")int num) {
		bs.deleteBoard(num);
		return "redirect:/main";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
