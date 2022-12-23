package com.quiz.lesson03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.lesson03.bo.RealEstateBO;
import com.quiz.lesson03.model.RealEstate;

@RequestMapping("/lesson03/quiz01")
@Controller
public class Lesson03Quiz01RestController {
	
	@Autowired
	private RealEstateBO realestatebo;
	
	@RequestMapping("/1")
	public RealEstate quiz01() {
		return realestatebo.getRealEstate();
	}
	
}
