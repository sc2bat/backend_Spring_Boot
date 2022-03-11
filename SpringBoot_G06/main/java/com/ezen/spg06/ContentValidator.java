package com.ezen.spg06;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// 내장되어이 있는것 사용
public class ContentValidator implements Validator {
	
	// 두개의 메서드를 오버라이드
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return false;
	} // 얘는 거의 사용안함
	

	@Override
	public void validate(Object target, Errors errors) {
		// Object target : 검사할 객체를 받아주는 매개변수(Object 형)
		// Errors errors : 보내온 객체의 에러내용을 담아 다시 보내줄 매개변수
		
		ContentDto dto = (ContentDto)target;
		String sWriter = dto.getWriter();
		String sContent= dto.getContent();
		
		// null(new String() 조차도 실행안된거)이거나 값이 비어있거나("")
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
