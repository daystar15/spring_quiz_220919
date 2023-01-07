package com.quiz.lesson06;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.lesson06.bo.BookingBO;
import com.quiz.lesson06.model.Booking;

@Controller
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingBO bookingBO;	

	// 예약 목록 보기
	// http://localhost:8080/booking/booking_list_view
	@GetMapping("/booking_list_view")
	public String bookingListView(Model model) {
		
		List<Booking> bookingList = new ArrayList<>();
		bookingList = bookingBO.getBookingListById();
		
		model.addAttribute("bookingList", bookingList);
		
		return "booking/bookingList";
	}
	
	// 예약 목록 보기 - 삭제 AJAX
	@ResponseBody
	@DeleteMapping("/delete_booking_list")
	public Map<String, Object> deleteBookingList(
			@RequestParam("id") int id) {
		
		Map<String, Object> deleteResult = new HashMap<>();
		
		int row = bookingBO.deleteBookingList(id);
		if (row > 0) {
			deleteResult.put("result", "성공");
		} else {
			deleteResult.put("result", "실패");
		}
		
		return deleteResult;
	}
	
	// 예약하기 화면
	// http://localhost:8080/booking/reservation_view
	@GetMapping("/reservation_view")
	public String reservationView() {;
		return "booking/reservation";
	}
	
	// 예약하기 화면 - AJAX 통신
	@ResponseBody
	@PostMapping("/add_reservation")
	public Map<String, Object> addBooking(
			@RequestParam("name") String name,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("day") int day,
			@RequestParam("headcount") int headcount,
			@RequestParam(value="phoneNumber", required=false) String phoneNumber) {

		bookingBO.addBooking(name, date, day, headcount, phoneNumber);
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "성공");
		
		return result;
	}
	
	// 메인 화면
	// http://localhost:8080/booking/main_view
	@GetMapping("/main_view")
	public String mainView() {
		return "booking/main";
	}
	
	// 메인화면 - 예약 조회(AJAX)
	@ResponseBody
	@PostMapping("/reservation_serch")
	public Map<String, String> reservationSearch(
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber,
			Model model) {
		
		Map<String, String> result = bookingBO.getBookingList(name, phoneNumber);
		model.addAttribute("name", name);
		model.addAttribute("phoneNumber", phoneNumber);
		
		return result;
		
	}
}
