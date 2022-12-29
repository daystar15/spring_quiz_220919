package com.quiz.lesson04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.lesson04.bo.RealtorBO;
import com.quiz.lesson04.model.Realtor;

@Controller
@RequestMapping("/lesson04/quiz02")
public class Lesson04Quiz02Controller {
	
	@Autowired
	private RealtorBO realtorBO;

	// 추가화면
	// http://localhost:8080/lesson04/quiz02/add_realtor_view
	@GetMapping("/add_realtor_view")
	public String addRealtorView() {
		return "lesson04/addRealtor";
	}
	
	// action태그가 수행되는 부분
	@PostMapping("/add_realtor")
	public String addRealtor(
			@ModelAttribute Realtor realtor,
			Model model) {
		// db insert => 방금 추가된 공인중개사 정보
		realtorBO.addRealtor(realtor);
		
		// db select(방금 insert 된 정보 가져오기)
		Realtor realtor1 = realtorBO.getRealtorById(realtor.getId());
		
		// model
		model.addAttribute("result", realtor1);
		model.addAttribute("subject", "공인중개사 정보");
		
		// view 페이지 응답값
		return "lesson04/afterAddRealtor";
	}
}
