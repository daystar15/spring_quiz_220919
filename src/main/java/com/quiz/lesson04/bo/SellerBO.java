package com.quiz.lesson04.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson04.dao.SellerDAO;
import com.quiz.lesson04.model.Seller;

@Service
public class SellerBO {
	
	@Autowired
	private SellerDAO sellerDAO;

	public void addSeller(String nickname, String profileImageUrl, Double temperature) {
		sellerDAO.insertSeller(nickname, profileImageUrl, temperature);
	}
	
	public Seller getSellerInfo() {
		return sellerDAO.selectSellerInfo();
	}
	
	public Seller getSellerById(int id) { // null이 못들어오게 파라미터에 id를 넣음
		return sellerDAO.selectSellerById(id);
	}
	
}
