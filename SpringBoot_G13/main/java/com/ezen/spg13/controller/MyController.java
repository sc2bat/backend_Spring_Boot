package com.ezen.spg13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.spg13.service.MyService;

@Controller
public class MyController {
	
	@Autowired
	MyService ms;
	
	@RequestMapping("/")
	public String root() {
		return "buy_ticket";
	}
	
	@RequestMapping("/buyTicketCard")
	public String buy_ticket_cart(
			@RequestParam("id")String id, @RequestParam("amount")int amount, 
			@RequestParam("error")int error, Model md) {
		// 현재 해야할 일은 전달된 아이디가 티켓을 전달된 구매갯수만큼 구매한 걸로 데이터베이스 테이블에 insert 하는것
		int result = ms.buyTicket(id, amount, error);
		// 전달된 아이디, 구매갯수, 에러여부를 서비스단에 전달하여 구매작업을 지속합니다
		// 구매작업 성공여부를 리턴받아 성공이라면 buy_ticket_end.jsp 실패라면 buy_ticket_error.jsp
		
		md.addAttribute("id", id);
		md.addAttribute("amount", amount);
		
		if(result == 1) {
			return "buy_ticket_end";
		}else {
			return "buy_ticket_error";
		}
	}
}
