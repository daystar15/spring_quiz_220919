package com.quiz.lesson03;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.lesson03.bo.RealEstateBO;
import com.quiz.lesson03.model.RealEstate;

@RequestMapping("/lesson03/quiz01")
@RestController
public class Lesson03Quiz01RestController {
	
	@Autowired
	private RealEstateBO realestateBO;
	
	// 1. id 로 select 하기
	@RequestMapping("/1")
	public RealEstate quiz01(
			@RequestParam(value="id", defaultValue="1") int id
		) {
//		if (id == null) {
//			return null;
//		} else {
		return realestateBO.getRealEstate(id);
//		}
	}
	
	// 2. 월세 조건 select
	@RequestMapping("/2")
	public List<RealEstate> quiz02(
			@RequestParam(value="rent_price", required=false) Integer rentPrice
		) {
		if (rentPrice == null) {
			return null;
		} else {
			List<RealEstate> list = realestateBO.getRentPrice(rentPrice);
			return list;
		}
	}
	
	// 3. 복합조건 select
	@RequestMapping("/3")
	public List<RealEstate> quiz03(
			@RequestParam("area") int area, 
			@RequestParam("price") int price
		) {
		List<RealEstate> list2 = realestateBO.getDoubleCondition(area, price);
		return list2;
	}
	
}
