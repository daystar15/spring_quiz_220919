package com.quiz.lesson06.bo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson06.dao.BookingDAO;
import com.quiz.lesson06.model.Booking;

@Service
public class BookingBO {
	
	@Autowired
	private BookingDAO bookingDAO;
	
	public List<Booking> getBookingListById() {
		return bookingDAO.selectBookingListById();
	}

	public void addBooking(String name, Date date, int day, int headcount, String phoneNumber) {
		bookingDAO.insertBooking(name, date, day, headcount, phoneNumber);
	}
	
	public int deleteBookingList(int id) {
		return bookingDAO.deleteBookingList(id);
	}
	
	// 메소드 이름 : getLatestBookingByNamePhoneNumber
	public Booking getBookingList(String name, String phoneNumber) {
		return bookingDAO.getBookingList(name, phoneNumber);
	}
}
