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
		// ���� �ؾ��� ���� ���޵� ���̵� Ƽ���� ���޵� ���Ű�����ŭ ������ �ɷ� �����ͺ��̽� ���̺� insert �ϴ°�
		int result = ms.buyTicket(id, amount, error);
		// ���޵� ���̵�, ���Ű���, �������θ� ���񽺴ܿ� �����Ͽ� �����۾��� �����մϴ�
		// �����۾� �������θ� ���Ϲ޾� �����̶�� buy_ticket_end.jsp ���ж�� buy_ticket_error.jsp
		
		md.addAttribute("id", id);
		md.addAttribute("amount", amount);
		
		if(result == 1) {
			return "buy_ticket_end";
		}else {
			return "buy_ticket_error";
		}
	}
}
