package com.example.todoapp.todo.dto;

import java.time.LocalDate;

/**
 * 히트맵 응답 생성 클래스
 */
public class HeatmapResponse {

	private final LocalDate date;
	private final Long count;
	
	/**
	 * 히트맵 응답을 생성하는 메소드
	 * @param date 날짜
	 * @param count 해당 날짜 완료 개수
	 */
	public HeatmapResponse(LocalDate date, Long count) {
		this.date = date;
		this.count = count;
	}
	
	/**
	 * 날짜를 반환하는 Getter
	 * @return 날짜
	 */
	public LocalDate getDate() {
		return this.date;
	}
	
	/**
	 * 완료 개수를 반환하는 Getter
	 * @return 완료 개수
	 */
	public Long getCount() {
		return this.count;
	}
}
