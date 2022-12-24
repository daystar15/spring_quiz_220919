package com.quiz.lesson03.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quiz.lesson03.model.RealEstate;

@Repository
public interface RealEstateDAO {

	public RealEstate selectRealEstate(int id);
	
	public List<RealEstate> selectRentPrice(Integer rentPrice);
	
	public List<RealEstate> selectDoubleCondition(@Param("area")int area, @Param("price")int price);
}
