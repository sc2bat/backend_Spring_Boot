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
						n = 10/0; // ���� �����߻�
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
		// ���޵� ���̵�, ���Ű����� transaction1, transaction2 2���� ���̺� insert �մϴ�
		// �ϳ��̻��� �����ͺ��̽� �۾��� �Ѵ����� ��� �ϳ��� ��������� ���ǵ� ���� Ʈ������̶���մϴ�
		// Ʈ����� �ϳ��� ��� �� ������ �Ǿ� �Ϸ�Ǹ�, commit �̶�� ������� �۾��� �Ϸ��ϰ�,
		// �߰��� ������ �߻��Ͽ� Ʈ������� ����ϰ��� �Ѵٸ� rollback �̶�� ������� ����մϴ�
		
		int n = 0;
		
		// Ʈ������� ����
		TransactionStatus status = ptm.getTransaction(td);
		// ���� commit �Ǵ� rollback
		try {
			System.out.println("test");
			tdao1.buy(id, amount);
			System.out.println("2test2");
			if(error == 1) {
				n = 10/0; // ���� �����߻�
			}
			
			tdao2.buy(id, amount);
			System.out.println("����Ϸ�");
			ptm.commit(status);
			result = 1; // ���� ���� ��� �����ͺ��̽� �۾��� �������� - Ʈ������� ��
		} catch (Exception e) {
			System.out.println("���� �߻�");
			ptm.rollback(status);
			result = 0;
		}
//		System.out.println("result" + result);
		return result;
	}
	*/
	
	
	
	
}
