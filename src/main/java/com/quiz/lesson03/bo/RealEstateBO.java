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

	// input: controller가 넘겨주는 id
	// output: RealEstate 단건 => 컨트롤러한테 넘김
	// public RealEstate getRealEstateById(int id)
	// 함수원형이 길어도 상관없음 명칭을 맞추는 것이 중요
	// By가 붙어있으면 WHERE절이 붙어있다고 생각하면 된다.
	public RealEstate getRealEstate(int id) {
		return realestateDAO.selectRealEstate(id);
	}
	
	// input: controller가 넘겨주는 rentPrice
	// output: List<RealEstate> => 컨트롤러한테 넘김
	// getRealEstateListByRentPrice
	public List<RealEstate> getRentPrice(Integer rentPrice) {
		return realestateDAO.selectRentPrice(rentPrice);
	}
	
	// input: controller가 넘겨주는 area, price
	// output: List<RealEstate> => 컨트롤러한테 넘김
	// getRealEstateListByAreaAndPrice (길면 And 빼도 됨)
	public List<RealEstate> getDoubleCondition(int area, int price) {
		// Map<String, Object> params = newHashMap<>(); (방법 1)
		return realestateDAO.selectDoubleCondition(area, price);
	}
	
	// input: RealEstate
	// output: int(성공한 행의 개수)
	public int addRealEstate(RealEstate realEstate) { // (자료형 이름)
		return realestateDAO.insertRealEstate(realEstate);
	}
	
	public int addRealEstateAsField(int realtorId, String address, int area, String type, int price, Integer rentPrice) {
		return realestateDAO.insertRealEstateAsField(realtorId, address, area, type, price, rentPrice);
	}
	
	public int updateRealEstateById(int id, String type, int price) {
		return realestateDAO.updateRealEstateById(id, type, price);
	}
	
	public void deleteRealEstateById(int id) {
		realestateDAO.deleteRealEstateById(id);
	}
}
