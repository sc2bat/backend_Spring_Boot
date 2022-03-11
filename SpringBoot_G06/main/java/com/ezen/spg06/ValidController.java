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
	 * ���1
	@RequestMapping("/creat")
	public String create(HttpServletRequest rq) {
		String writer = rq.getParameter("writer");
		String content = rq.getParameter("content");
		return "createPage";
	}
	
	// ���2
	@RequestMapping("/creat")
	public String create(@RequestParam("writer")String writer, @RequestParam("content")String content) {
		return "createPage";
	}
	*/
	@RequestMapping("/create")
//	public String create(ContentDto contentdto, Model model) {
//	public String create(@ModelAttribute("dto")ContentDto contentdto, Model model) {
		// �Ű������� Dto ��ü�� ������ ������ ���޵Ǿ� ���� �Ķ���͵��� ��ü���� ������ �̸���
		// ��������� �ڵ� ���Եȴ�. // writer -> contentdto.writer content-> contentdto.content
		
		// �Ű����� ��ü(�Ű�����) �տ� ModelAttribute(�����̸�) �� �ٿ��� return �Ǵ� �������� �ش� ��ü��
		// model.addAttribute �� ������ó�� ���� ���޵ǰ� �մϴ�.
		// model.addAttribute("dto", contentdto); �� ������ ����
		
//		if(contentdto.getWriter() == null || contentdto.getContent() == null) { //����ִٰ� null �� �ƴ϶� ������ �Ǿ����
		/*
		if(contentdto.getWriter().equals("") || contentdto.getContent().equals("")) {
			model.addAttribute("message", "writer or content is null");
			return "startPage";
			// �� �� �ϳ��� �ț��ٸ� ���ư��� 
			// ���ư����� ������ ������ �ִٸ� ������ ���ư� contentdto �� dto �� �����Ͽ�
		}else {
			return "DonePage";
		}*/
		
	public String create(@ModelAttribute("dto")ContentDto contentdto, Model model, BindingResult result) {
		// validation �� �ִ� Ŭ������ ���� ����մϴ�
		ContentValidator validator = new ContentValidator();
		validator.validate(contentdto, result);
		// BindingResult result : ���� ����(Ű��)�� ����(value��)�� ���� �� �ִ� ��ü
		// validator �� ����޼����� validate �� contentdto ������ �˻��� �� result �� ���� ������ ����ְ�,
		// ���ϵ��� �ȾƵ� call by reference �̱� ������ ���������� ���� ��ġ������ result ��� �̸����� ����� �����մϴ�
		
//		if(contentdto.getWriter().equals("") || contentdto.getContent().equals("")) {
		if(result.hasErrors()) {
			model.addAttribute("message", "writer or content is null");
			return "startPage";
		}else {
			return "DonePage";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
