package com.quiz.lesson03.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson03.dao.RealEstateDAO;
import com.quiz.lesson03.model.RealEstate;

@Service
public class RealEstateBO {
	
	@Autowired
	private RealEstateDAO realestateDAO;

	public RealEstate getRealEstate(int id) {
		return realestateDAO.selectRealEstate(id);
	}
	
	public List<RealEstate> getRentPrice(Integer rentPrice) {
		return realestateDAO.selectRentPrice(rentPrice);
	}
	
	public List<RealEstate> getDoubleCondition(int area, int price) {
		return realestateDAO.selectDoubleCondition(area, price);
	}
}
