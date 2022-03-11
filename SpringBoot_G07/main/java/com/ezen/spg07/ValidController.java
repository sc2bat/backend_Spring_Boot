package com.ezen.spg07;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ValidController {
	@RequestMapping("/")
	public String main() {
		return "startPage";
	}
	
	@RequestMapping("/create")
	public String insert(@ModelAttribute("dto")ContentDto contentdto, BindingResult result, Model model) {
		ContentValidator validator = new ContentValidator();
		validator.validate(contentdto, result);
		
		if(result.hasErrors()) {
			if(result.getFieldError("writer") != null) {
				model.addAttribute("message", "writer is null");
			}else {
				model.addAttribute("message", "content is null");
			}
			return "startPage";
		}else {
			return "DonePage";
		}
	}
}
