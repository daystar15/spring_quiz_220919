package com.quiz.lesson06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.lesson06.bo.FavoriteBO;
import com.quiz.lesson06.model.Favorite;

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
	
	// AJAX 통신 - 즐겨찾기 추가
	@PostMapping("/quiz01/add_favorite")
	@ResponseBody
	public Map<String, String> addFavorite( // 여기서 String은 내가 내리고자 하는 단어를 의미한다.
			@RequestParam("name") String name,
			@RequestParam("url") String url){
		
		// db insert
		favoriteBO.addFavorite(name, url);
		
		// 성공 값 응답값
		Map<String, String> result = new HashMap<>();
		result.put("result", "성공");
		
		return result;  // jackson라이브러리가 동작해서 JSON(String)이 된다
	}
	
	// AJAX - url 중복 확인
	@ResponseBody
	@PostMapping("/quiz02/is_duplication")
	public Map<String, Boolean> isDuplication(
			@RequestParam("url") String url) {
		
		Map<String, Boolean> duplMap = new HashMap<>();
		duplMap.put("is_duplication", favoriteBO.existFavoriteByUrl(url));
		
		return duplMap;
	}
	
	// http://localhost:8080/lesson06/quiz01/after_add_favorite_view
	@GetMapping("/quiz01/after_add_favorite_view")
	public String AfterAddFavoriteView(Model model) {
		// 데이터 DB select
		List<Favorite> list = new ArrayList<>();
		list = favoriteBO.getFavorite();
		
		// model에 담기
		model.addAttribute("list", list);
		
		return "lesson06/quiz01_1";
	}
}
