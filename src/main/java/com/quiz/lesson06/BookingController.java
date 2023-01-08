package com.quiz.lesson06;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	private Date date;
	private int day;
	private int headcount;
	private String state;	

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
	@PostMapping("/reservation_search")
	public Map<String, Object> reservationSearch(
			@RequestParam("name") String name,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam(value="day", required=false, defaultValue="0") Integer day,
			@RequestParam(value="headcount", required=false, defaultValue="0") Integer headcount,
			@RequestParam(value="state", required=false) String state
			) {
		
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> person = new HashMap<>();
		
		// BO에서 정보 select
		Booking booking = bookingBO.getBookingList(name, date, day, headcount, phoneNumber, state);
		
		// 예약확인 정보
		String whatName = booking.getName();
		String whatPhoneNumber = booking.getPhoneNumber();
		int whatDay = booking.getDay();
		int whatHeadcount = booking.getHeadcount();
		String whatState = booking.getState();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date whatsDate = booking.getDate();
		String whatDate = dateFormat.format(whatsDate);

		result.put("findOk", "성공");
		result.put("day", whatDay);
		result.put("headcount", whatHeadcount);
		result.put("state", whatState);
		result.put("date", whatDate);
		
		//if (name == whatName) {
		//	if (phoneNumber == whatPhoneNumber) {
		//		
		//	}
		//} else {
		//	result.put("findOk", "재확인");
		//}
		
		
		return result;
		
	}
}
