package com.quiz.lesson04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.lesson04.bo.SellerBO;
import com.quiz.lesson04.model.Seller;

@Controller
@RequestMapping("/lesson04/quiz01")
public class Lesson04Quiz01Controller {
	
	@Autowired
	private SellerBO sellerBO;

	// 판매자 추가 화면(뷰 화면)
	// http://localhost:8080/lesson04/quiz01/add_seller_view
	@GetMapping(path="/add_seller_view")
	public String addSellerView() {
		return "lesson04/add_seller";
	}
	
	// 판매자 insert => 입력 성공 jsp
	@PostMapping("/addSeller") // form의 메소드가 post(서버에서 먼저 설정 후 jsp form 설정)
	public String addSeller(
			@RequestParam("nickname") String nickname,
			// null일 수 있어서 required=false를 붙임
			@RequestParam(value="profileImageUrl", required=false) String profileImageUrl,
			// null로 들어가도 디폴트값이 있어서 채워질 수 있기 때문에 required=false들어가면 Double로 넣기
			@RequestParam(value="temperature", required=false) Double temperature) {
		
		// DB insert
		sellerBO.addSeller(nickname, profileImageUrl, temperature);
		
		return "lesson04/after_add_seller";
	}
	
	// http://localhost:8080/lesson04/quiz01/seller_info
	// http://localhost:8080/lesson04/quiz01/seller_info?id=1
	@GetMapping("/seller_info")
	public String getSellerInfo(
			@RequestParam(value="id", required=false) Integer id,
			Model model) { // model과 같이 쓸 때 model을 뒤에 둔다.
		
		// db select
		Seller seller = null; // scope 때문에 먼저 선언한다. null로 초기화하는 것이 좋은 코드
		if (id == null) {
			seller = sellerBO.getSellerInfo();
		} else {
			seller = sellerBO.getSellerById(id);
		}
		
		// 따로 담았다가 하는게 잘들어왔는지 확인하기 쉽다.
		model.addAttribute("seller", seller);
		model.addAttribute("title", "판매자 정보");
		
		return "lesson04/seller_info";
	}
	
	
	
}
