package com.quiz.lesson01;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/lesson01/quiz01")
@Controller
public class Lesson01Quiz01Controller {
	
	// 1번 http://localhost:8080/lesson01/quiz01/1
	@ResponseBody // 필수적으로 꼭 써야함, return하는 데이터가 responsebody에 들어가서 보여지는 거임
	@RequestMapping("/1")
	public String printString() {
		String testProject = "<h1>테스트 프로젝트 완성</h1><h3>해당 프로젝트를 통해서 문제 풀이를 진행 합니다.</h3>";
		return testProject;
	}
	
	// 2번
	@ResponseBody
	@RequestMapping("/2")
	public Map<String, Integer> printMap() {
		Map<String, Integer> score = new HashMap<>();
		
		score.put("국어", 80);
		score.put("수학", 90);
		score.put("영어", 85);
		
		return score; // jackson 라이브러리로 인해 map -> json으로 변환됨
	}
}
