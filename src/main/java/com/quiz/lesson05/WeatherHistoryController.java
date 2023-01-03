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
	
	// http://localhost:8080/lesson05/add_weather_view
	@GetMapping("/add_weather_view")
	public String addWeatherView() {
		return "lesson05/quiz05_1";
	}
	
	@PostMapping("/add_weather")
	public String addWeather(
			@DateTimeFormat(pattern="yyyy-MM-dd") Date date,
			@RequestParam("weather") String weather,
			@RequestParam("temperatures") double temperatures,
			@RequestParam("precipitation") double precipitation,
			@RequestParam("microDust") String microDust,
			@RequestParam("windSpeed") double windSpeed) {
		
		weatherhistoryBO.addWeatherHistoryList(date, weather, temperatures, precipitation, microDust, windSpeed);
		
		return "lesson05/quiz05";
	}
	
}
