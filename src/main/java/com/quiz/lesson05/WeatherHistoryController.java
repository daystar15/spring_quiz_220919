package com.quiz.lesson05;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.lesson05.bo.WeatherHistoryBO;
import com.quiz.lesson05.model.WeatherHistory;

@Controller
@RequestMapping("/lesson05")
public class WeatherHistoryController {

	@Autowired
	private WeatherHistoryBO weatherhistoryBO;
	
	// 목록 화면
	// http://localhost:8080/lesson05/weather_history_view
	@GetMapping("/weather_history_view")
	public String weatherHistoryView(Model model) {
		List<WeatherHistory> list = new ArrayList<>();
		list = weatherhistoryBO.getWeatherHistoryList();
		
		model.addAttribute("list", list);
		return "lesson05/quiz05";
	}
	
	// 추가 => 결과화면 목록 화면 이동(redirect)
	// http://localhost:8080/lesson05/add_weather_view
	@GetMapping("/add_weather_view")
	public String addWeatherView() {
		return "lesson05/quiz05_1";
	}
	
	@PostMapping("/add_weather")
	public String addWeather(
			// @RequestParam으로 쓸거면 date는 String으로 받아야함, 
			// String으로 인서트를 해도 DB에서는 date타입으로 잘 저장된다.
			@DateTimeFormat(pattern="yyyy-MM-dd") Date date,
			@RequestParam("weather") String weather,
			@RequestParam("temperatures") double temperatures,
			@RequestParam("precipitation") double precipitation,
			@RequestParam("microDust") String microDust,
			@RequestParam("windSpeed") double windSpeed
			//, HttpServletResponse response
			) {
		
		// db insert
		weatherhistoryBO.addWeatherHistoryList(date, weather, temperatures, precipitation, microDust, windSpeed);
		
		// 목록 화면으로 리다이렉트
		// response.sendRedirect("redirect:/lesson05/weather_history_view"); - 그리고 예외처리
		return "redirect:/lesson05/weather_history_view"; // 절대경로로 지정하기
	}
	
}
