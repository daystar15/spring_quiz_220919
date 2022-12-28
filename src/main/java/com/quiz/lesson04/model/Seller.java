package com.quiz.lesson04.model;

import java.util.Date;

// DTO의 역할을 한다.
public class Seller {

	// 필드
	private int id;
	private String nickname;
	private String profileImageUrl;
	private double temperature; // 기본값이 존재하기 때문에 select를 해왔을 때 null이 안됨
	// insert일 경우에 null이 될 수 있으니 Double로 써야함
	private Date createdAt;
	private Date updatedAt;
	
	// getter, setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
