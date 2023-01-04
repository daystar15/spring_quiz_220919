package com.quiz.lesson06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.lesson06.bo.FavoriteBO;

@Controller
@RequestMapping("/lesson06")
public class Lesson06Controller {
	
	@Autowired
	private FavoriteBO favoriteBO;

	// 즐겨찾기 추가 화면
	// http://localhost:8080/lesson06/quiz01/add_favorite_view
	@GetMapping("/quiz01/add_favorite_view")
	public String addFavoriteView() {
		return "lesson06/quiz01";
	}
	
	// AJAX 통신
	@PostMapping("/quiz01/add_favorite")
	@ResponseBody
	public String addFavorite(
			@RequestParam("name") String name,
			@RequestParam("url") String url,
			Model model){
		
		// db insert
		favoriteBO.addFavorite(name, url);
		model.addAttribute("model", model);
		
		return "성공";
	}
	
	@GetMapping("/quiz01/after_add_favorite_view")
	public String AfterAddFavoriteView() {
		return "lesson06/quiz01_1";
	}
}
