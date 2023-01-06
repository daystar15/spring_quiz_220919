package com.quiz.lesson06.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson06.dao.FavoriteDAO;
import com.quiz.lesson06.model.Favorite;

@Service
public class FavoriteBO {
	
	@Autowired
	private FavoriteDAO favoriteDAO;

	public void addFavorite(String name, String url) {
		favoriteDAO.insertFavorite(name, url);
	}
	
	public List<Favorite> getFavorite() {
		return favoriteDAO.selectFavorite();
	}
	
	public boolean existFavoriteByUrl(String url) {
		return favoriteDAO.existFavoriteByUrl(url);
	}
	
	// return을 list<favorite>로 받아 저장한다.
	public Favorite getFavoriteByUrl(String url) {
		return favoriteDAO.selectFavoriteByUrl(url);
		// 0 1   []
		/*
		 * List<Favorite> favoriteList = favoriteDAO.selectFavoriteByUrl(url); if
		 * (favoriteList.isEmpty() == false) { // 리스트가 채워져 있을 때 return
		 * favoriteList.get(0); }
		 * 
		 * // 비어있는 경우 return null;
		 */
	}
	
	public int deleteFavoriteByIds(int id) {
		return favoriteDAO.deleteFavoriteByIds(id);
	}
	
	// 내가 한것
	public void deleteFavoriteById(int id) {
		favoriteDAO.deleteFavoriteById(id);
	}
}
