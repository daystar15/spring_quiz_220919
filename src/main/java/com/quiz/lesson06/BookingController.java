package com.quiz.lesson06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking")
public class BookingController {

	// 예약 목록 보기
	// http://localhost:8080/booking/booking_list_view
	@GetMapping("/booking_list_view")
	public String bookingListView() {
		return "booking/bookingList";
	}
	
	// 예약하기 화면
	// http://localhost:8080/booking/reservation_view
	@GetMapping("/reservation_view")
	public String reservationView() {
		return "booking/reservation";
	}
	
	// 메인 화면
	// http://localhost:8080/booking/main_view
	@GetMapping("/main_view")
	public String mainView() {
		return "booking/main";
	}
}
