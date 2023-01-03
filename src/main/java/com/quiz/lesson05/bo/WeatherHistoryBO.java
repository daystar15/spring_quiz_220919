package com.quiz.lesson05.bo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson05.dao.WeatherHistoryDAO;
import com.quiz.lesson05.model.WeatherHistory;

@Service
public class WeatherHistoryBO {

	@Autowired
	private WeatherHistoryDAO weatherhistoryDAO;
	
	public List<WeatherHistory> getWeatherHistoryList() {
		return weatherhistoryDAO.selectWeatherHistoryList();
	}
	
	public void addWeatherHistoryList(
			Date date,
			String weather,
			double temperatures,
			double precipitation,
			String microDust,
			double windSpeed) {
		weatherhistoryDAO.insertWeatherHistoryList(date, weather, temperatures, precipitation, microDust, windSpeed);
	}
}
