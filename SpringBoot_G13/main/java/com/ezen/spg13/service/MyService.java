package com.ezen.spg13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ezen.spg13.dao.ITransactionDao1;
import com.ezen.spg13.dao.ITransactionDao2;

@Service
public class MyService {
	@Autowired
	ITransactionDao1 tdao1;
	
	@Autowired
	ITransactionDao2 tdao2;
	
	@Autowired
	TransactionTemplate tt;

	public int buyTicket(String id, int amount, int error) {
		try {
			tt.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					tdao1.buy(id, amount);
					int n = 0;
					if(error == 1) {
						n = 10/0; // 강제 에러발생
					}
					tdao2.buy(id, amount);
					System.out.println("Transaction commit");
				}
			});
			return 1;
		}catch(Exception e) {
			System.out.println("Transaction rollback");
			return 0;
		}
	}
	
	/* old version
	@Autowired
	PlatformTransactionManager ptm;
	
	@Autowired
	TransactionDefinition td;
	
	

	public int buyTicket(String id, int amount, int error) {
		int result = 0;
		// 전달된 아이디, 구매갯수를 transaction1, transaction2 2개의 테이블에 insert 합니다
		// 하나이상의 데이터베이스 작업을 한단위로 묶어서 하나의 실행단위로 정의된 것을 트랜잭션이라고합니다
		// 트랜잭션 하나가 모두 다 실행이 되어 완료되면, commit 이라는 명령으로 작업을 완료하고,
		// 중간에 에러가 발생하여 트랜잭션을 취소하고자 한다면 rollback 이라는 명령으로 취소합니다
		
		int n = 0;
		
		// 트랜잭션의 시작
		TransactionStatus status = ptm.getTransaction(td);
		// 끝은 commit 또는 rollback
		try {
			System.out.println("test");
			tdao1.buy(id, amount);
			System.out.println("2test2");
			if(error == 1) {
				n = 10/0; // 강제 에러발생
			}
			
			tdao2.buy(id, amount);
			System.out.println("실행완료");
			ptm.commit(status);
			result = 1; // 영역 안의 모든 데이터베이스 작업의 실행적용 - 트랜잭션의 끝
		} catch (Exception e) {
			System.out.println("에러 발생");
			ptm.rollback(status);
			result = 0;
		}
//		System.out.println("result" + result);
		return result;
	}
	*/
	
	
	
	
}
