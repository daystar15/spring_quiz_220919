package com.quiz.lesson06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	// AJAX - url 중복 확인, Model 객체를 쓸 수 없다.
	@ResponseBody
	@PostMapping("/quiz02/is_duplication")
	public Map<String, Boolean> isDuplication(
			@RequestParam("url") String url) {
		
		Map<String, Boolean> duplMap = new HashMap<>();
		// 중복확인 select
		// 아래와 같은 쿼리는 재활용이 가능해서 좋다.
		/* 만약 데이터에 중복이 있으면 에러가 난다. 하나의 객체에 담을 수가 없기 때문에(too many result) 
		   해결) list로 받아야함
		 * Favorite favorite = favoriteBO.getFavoriteByUrl(url); 
		 * if (favorite != null) {
		 * // 중복 duplMap.put("is_duplication", true); } 
		 * else {
		 * duplMap.put("is_duplication", false); }
		 */
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
	
	// 선생님 풀이
	// AJAX 요청
	// http://localhost:8080/lesson06/quiz02/delete_favorite
	@ResponseBody
	@DeleteMapping("/quiz02/delete_favorite")
	public Map<String, Object> deleteFavorite(
			@RequestParam("id") int id) {
		
		Map<String, Object> result = new HashMap<>();
		
		// db delete, 정의하는 것은 서버개발자 재량껏, 클라이언트가 서버가 어떻게 작동되었는지 알 수있다.
		int row = favoriteBO.deleteFavoriteByIds(id);
		if (row > 0) {
			result.put("code", 1); // 성공
			result.put("result", "성공");
		} else {
			result.put("code", 500); // 실패
			result.put("result", "실패");
			result.put("error_message", "삭제된 행이 없습니다.");
		}
		
		return result;
	}
	
	// 내가 한 것
	// 즐겨 찾기 목록 삭제 - AJAX
	@ResponseBody
	@DeleteMapping("/quiz02/after_delete_favorite")
	public String afterDeleteFavorite(
			@RequestParam("id") int id) {
		
		favoriteBO.deleteFavoriteById(id);
		
		return "성공";
	}
}
