package com.example.todoapp.global.exception;

import com.example.todoapp.todo.TodoNotFoundException;
import com.example.todoapp.user.DuplicateEmailException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리 클래스
 * 모든 Controller에서 발생한 예외를 해당 클래스가 응답으로 변환
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * Todo를 찾지 못한 경우 404로 응답
	 * @param e 발생한 TodoNotFoundException
	 * @return 404 상태와 에러 응답 부문
	 */
	@ExceptionHandler(TodoNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleTodoNotFound(TodoNotFoundException e) {
		ErrorResponse body = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
	
	/**
	 * 이메일 중복 시 409 Conflict로 응답
	 * @param e - 발생한 예외
	 * @return - 409 상태와 에러 응답
	 */
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateEmail(DuplicateEmailException e) {
		ErrorResponse body = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
	}
}
