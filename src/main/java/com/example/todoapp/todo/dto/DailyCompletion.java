package com.example.todoapp.todo.dto;

import java.time.LocalDate;

/**
 * 날짜별 완료 개수 조회 결과 클래스
 */
public interface DailyCompletion {
	LocalDate getDate();
	Long getCount();
}
