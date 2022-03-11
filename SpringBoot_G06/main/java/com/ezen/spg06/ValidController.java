package com.ezen.spg06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ValidController {

	@RequestMapping("/")
	public String main(){
		return "startPage";
	}
	
	/*
	 * 방법1
	@RequestMapping("/creat")
	public String create(HttpServletRequest rq) {
		String writer = rq.getParameter("writer");
		String content = rq.getParameter("content");
		return "createPage";
	}
	
	// 방법2
	@RequestMapping("/creat")
	public String create(@RequestParam("writer")String writer, @RequestParam("content")String content) {
		return "createPage";
	}
	*/
	@RequestMapping("/create")
//	public String create(ContentDto contentdto, Model model) {
//	public String create(@ModelAttribute("dto")ContentDto contentdto, Model model) {
		// 매개변수에 Dto 객체를 변수로 넣으면 전달되어 지는 파라미터들이 객체내의 동일한 이름의
		// 멤버변수에 자동 대입된다. // writer -> contentdto.writer content-> contentdto.content
		
		// 매개변수 객체(매개변수) 앞에 ModelAttribute(전달이름) 을 붙여서 return 되는 페이지가 해당 객체가
		// model.addAttribute 로 넣은것처럼 같이 전달되게 합니다.
		// model.addAttribute("dto", contentdto); 와 동일한 동작
		
//		if(contentdto.getWriter() == null || contentdto.getContent() == null) { //비어있다고 null 이 아니라 전달이 되어버림
		/*
		if(contentdto.getWriter().equals("") || contentdto.getContent().equals("")) {
			model.addAttribute("message", "writer or content is null");
			return "startPage";
			// 둘 중 하나라도 안썻다면 돌아가고 
			// 돌아가더라도 쓰여진 내용이 있다면 가지고 돌아감 contentdto 를 dto 로 변경하여
		}else {
			return "DonePage";
		}*/
		
	public String create(@ModelAttribute("dto")ContentDto contentdto, Model model, BindingResult result) {
		// validation 이 있는 클래스를 만들어서 사용합니다
		ContentValidator validator = new ContentValidator();
		validator.validate(contentdto, result);
		// BindingResult result : 에러 제목(키값)과 내용(value값)을 담을 수 있는 객체
		// validator 의 멤버메서드인 validate 가 contentdto 내용을 검사한 후 result 에 오류 내용을 담아주고,
		// 리턴되지 안아도 call by reference 이기 때문에 오류내용을 현재 위치에서도 result 라는 이름으로 사용이 가능합니다
		
//		if(contentdto.getWriter().equals("") || contentdto.getContent().equals("")) {
		if(result.hasErrors()) {
			model.addAttribute("message", "writer or content is null");
			return "startPage";
		}else {
			return "DonePage";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
