package com.ezen.spg14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.spg14.dao.ITransactionDao1;
import com.ezen.spg14.dao.ITransactionDao2;
import com.ezen.spg14.dao.ITransactionDao3;

@Service
public class MyService {
	@Autowired
	ITransactionDao1 tdao1;
	
	@Autowired
	ITransactionDao2 tdao2;
	
	@Autowired
	ITransactionDao3 tdao3;
	
	@Transactional
	public int buyTicket(String id, int amount, int error) {
		try {
			tdao1.buy(id, amount);
			int n = 0;
			if(error == 1) {
				n = 10/0;
			}
			tdao2.buy(id, amount);
			System.out.println("Transaction Commit");
			return 1;
		} catch (Exception e) {
			System.out.println("Transaction rollback");
			return 0;
		}
	}
}
