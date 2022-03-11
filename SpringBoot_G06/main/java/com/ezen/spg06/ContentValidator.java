package com.ezen.spg06;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// ����Ǿ��� �ִ°� ���
public class ContentValidator implements Validator {
	
	// �ΰ��� �޼��带 �������̵�
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return false;
	} // ��� ���� ������
	

	@Override
	public void validate(Object target, Errors errors) {
		// Object target : �˻��� ��ü�� �޾��ִ� �Ű�����(Object ��)
		// Errors errors : ������ ��ü�� ���������� ��� �ٽ� ������ �Ű�����
		
		ContentDto dto = (ContentDto)target;
		String sWriter = dto.getWriter();
		String sContent= dto.getContent();
		
		// null(new String() ������ ����ȵȰ�)�̰ų� ���� ����ְų�("")
		if(sWriter == null || sWriter.trim().isEmpty()) {
			System.out.println("Writer is null or empty");
			errors.rejectValue("writer", "trouble");
		}
		if(sContent == null || sContent.trim().isEmpty()) {
			System.out.println("Content is null or empty");
			errors.rejectValue("content", "trouble");
		}
	}
}
