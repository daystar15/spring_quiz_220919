package com.quiz.lesson06.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quiz.lesson06.model.Favorite;

@Repository
public interface FavoriteDAO {

	public void insertFavorite(
			@Param("name") String name, 
			@Param("url") String url);
	
	public List<Favorite> selectFavorite();
	
	public boolean existFavoriteByUrl(String url);
	
	// 중복된 데이터로 인해 오류가 발생하면 List로 받는다.
	public Favorite selectFavoriteByUrl(String url); // null이거나 아니거나
	
	public int deleteFavoriteByIds(int id);
	
	public void deleteFavoriteById(int id);
}
