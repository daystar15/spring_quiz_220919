package com.quiz.lesson01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/lesson01/quiz02")
public class Lesson01Quiz02Controller {
	
	// http://localhost:8080/lesson01/quiz02/1
	@RequestMapping("/1")
	public List<Map<String, Object>> movie() {
		// @ResponseBody + String 리턴 => HttpMessageConverter  
		// String 내용을 response body에 담아 HTML로 내린다.
		// @ResponseBody + 객체 리턴 => HttpMessageConverter
		// jackson 라이브러리 => JSON 응답(API)
		List<Map<String, Object>> movies = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>() {
			{ 	
				put("rate", 15);
				put("director", "봉준호"); 
				put("time", 131);
				put("title", "기생충");
			}
		};
		movies.add(map);
		map = new HashMap<String, Object>() {
			{ 	
				put("rate", 0);
				put("director", "로베르토 베니니"); 
				put("time", 116);
				put("title", "인생은 아름다워");
			}
		};
		movies.add(map);
		map = new HashMap<String, Object>() {
			{ 	
				put("rate", 12);
				put("director", "크리스토퍼 놀란"); 
				put("time", 114);
				put("title", "인셉션");
			}
		};
		movies.add(map);
		map = new HashMap<String, Object>() {
			{ 	
				put("rate", 19);
				put("director", "윤종빈"); 
				put("time", 133);
				put("title", "범죄와의 전쟁 : 나쁜놈들 전성시대");
			}
		};
		movies.add(map);
		map = new HashMap<String, Object>() {
			{ 	
				put("rate", 15);
				put("director", "프란시스 로렌스"); 
				put("time", 137);
				put("title", "헝거게임");
			}
		};
		movies.add(map);
		return movies; // 응답값
		
	}
	
	// http://localhost:8080/lesson01/quiz02/2
	@RequestMapping("/2")
	public List<Board> quiz02_2() {
		List<Board> list = new ArrayList<>();
		Board board = new Board();
		board.setTitle("안녕하세요 가입인사 드립니다.");
		board.setUser("hagulu");
		board.setContent("안녕하세요 가입했어요. 앞으로 잘 부탁드립니다. 활동 열심히 하겠습니다.");
		list.add(board);
		
		board = new Board(); // 클래스를 new 해야함
		board.setTitle("헐 대박");
		board.setUser("bada");
		board.setContent("오늘 목요일이 었어... 금요일인줄");
		list.add(board);
		
		board = new Board();
		board.setTitle("오늘 데이트 한 이야기 해드릴게요.");
		board.setUser("dulumary");
		board.setContent("...");
		list.add(board);
		
		return list; // jackson 라이브러리에 의해 JSON 변환
	}
	
	// http://localhost:8080/lesson01/quiz02/3
	@RequestMapping("/3")
	public ResponseEntity<Board> quiz02_3() {
		Board board = new Board();
		board.setTitle("안녕하세요 가입인사 드립니다.");
		board.setUser("hagulu");
		board.setContent("안녕하세요 가입했어요. 앞으로 잘 부탁드립니다. 활동 열심히 하겠습니다.");
		
		return new ResponseEntity<>(board, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
