package com.ezen.spg07;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ContentValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ContentDto dto = (ContentDto)target;
		
		// ���޵� ��������� null �̰ų� ����ִ����� ��������
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "writer is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content is empty");
		
		// ���޵� Ư�� ��������� ���ڼ� ����
		String sWriter = dto.getWriter();
		if(sWriter.length() < 3) {
			errors.rejectValue("writer", "writer is too short");
		}
	}
}
