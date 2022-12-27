package com.quiz.lesson03.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quiz.lesson03.model.RealEstate;

@Repository
public interface RealEstateDAO {

	public RealEstate selectRealEstate(int id);
	
	public List<RealEstate> selectRentPrice(Integer rentPrice);
	
	// @Param을 사용하는 방법2
	public List<RealEstate> selectDoubleCondition(
			// @Param 어노테이션에 의해서 하나의 맵이 된 것 => 그래서 parameterType이 map이됨
			// 예전에는 bo에서 직접 Map으로 만들어서 사용했음
			@Param("area") int area, 
			@Param("price") int price);

	public int insertRealEstate(RealEstate realEstate);
	
	public int insertRealEstateAsField(
			// map - mapper의 parameterType이 map
			@Param("realtorId") int realtorId, 
			@Param("address") String address, 
			@Param("area") int area, 
			@Param("type") String type, 
			@Param("price") int price, 
			@Param("rentPrice") Integer rentPrice);
	
	public int updateRealEstateById(
			@Param("id") int id, 
			@Param("type") String type, 
			@Param("price") int price);
	
	public void deleteRealEstateById(int id);
}
