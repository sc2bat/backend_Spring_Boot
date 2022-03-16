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
		
		
		// request 로 multipart 를 못받기에 전부 null 이 나오며 문제는 Valid 를 못함
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
			// MultipartRequest 로 전달인수를 모두 전달받은 후에 Validation 에 적용합니다
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
			//전송된 파일은 업로드 되고, 파일 이름은 모델에 저장합니다
			md.addAttribute("imgfilename", mt.getFilesystemName("imgfilename"));
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
		// bs.boardView(num); 에서 조회수 +1, 게시물 조회, 댓글 리스트 조회
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
