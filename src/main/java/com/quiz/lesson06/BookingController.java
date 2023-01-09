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

	/** 1번*/
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
			deleteResult.put("code", 1); // 성공
			deleteResult.put("result", "성공");
		} else {
			deleteResult.put("code", 500);
			deleteResult.put("result", "실패");
			deleteResult.put("error_message", "삭제하는데 실패했습니다.");
		}
		
		return deleteResult;
	}
	
	/** 2번*/
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
			// @RequestParam("date") String date, 아래와 같다.
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("day") int day,
			@RequestParam("headcount") int headcount,
			@RequestParam(value="phoneNumber", required=false) String phoneNumber) {

		// db insert
		bookingBO.addBooking(name, date, day, headcount, phoneNumber);
		
		// 응답값
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
	
	/** 3번 */
	// 메인화면 - 예약 조회(AJAX)
	@ResponseBody
	@PostMapping("/reservation_search")
	public Map<String, Object> reservationSearch(
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber) {
		
		
		// BO에서 정보 select - 최신 예약 정보 한 건
		Booking booking = bookingBO.getBookingList(name, phoneNumber);

		// 또 다른 방법)날짜는 아래처럼 안뽑고 map에 다른 것처럼 넣은 뒤 ㄴ
		// main의 ajax에서 slice로 잘라서 뽑으면 됨 
		// jackson라이브러리 미변환으로 날짜 오류가 있을 수 있어서
		// application.properties에 추가 코드를 입력해야함(블로그에 써놈)
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date whatsDate = booking.getDate();
		String whatDate = dateFormat.format(whatsDate);
		
		Map<String, Object> result = new HashMap<>();
		result.put("booking", booking);
		if (booking != null) {
			result.put("booking", booking);
			result.put("code", 1);
			result.put("date", whatDate);
		} else {
			result.put("code", 500);
		}
		
		// 예약확인 정보
		//String whatName = booking.getName();
		//String whatPhoneNumber = booking.getPhoneNumber();
		//int whatDay = booking.getDay();
		//int whatHeadcount = booking.getHeadcount();
		//String whatState = booking.getState();
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//Date whatsDate = booking.getDate();
		//String whatDate = dateFormat.format(whatsDate);

		//if (name.equals(whatName)) {
		//	if (phoneNumber.equals(whatPhoneNumber)) {
		//		result.put("findOk", 1);
		//		result.put("day", whatDay);
		//		result.put("headcount", whatHeadcount);
		//		result.put("state", whatState);
		//		result.put("date", whatDate);
		//	}
		//	result.put("findOk", "재확인");
		//} 
		
		return result;
	}
}
