package com.quiz.lesson06.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quiz.lesson06.model.Booking;

@Repository
public interface BookingDAO {

	public List<Booking> selectBookingListById();
	
	public void insertBooking(
			// @Param을 붙여서 하나의 Map으로 만든다.
			@Param("name") String name, 
			@Param("date") Date date, 
			@Param("day") int day, 
			@Param("headcount") int headcount, 
			@Param("phoneNumber") String phoneNumber);
	
	public int deleteBookingList(int id);
	
	public Booking getBookingList(
			@Param("name") String name, 
			@Param("phoneNumber") String phoneNumber);
	
}
