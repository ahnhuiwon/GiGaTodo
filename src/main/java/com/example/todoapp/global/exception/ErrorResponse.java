package com.example.todoapp.global.exception;

/**
 * API 에러 응답 공통 포맷 클래스
 */
public class ErrorResponse {

	private final int status;
	private final String message;
	
	/**
	 * 인스턴스 할당
	 * @param status HTTP 상태 코드
	 * @param message 에러 메시지
	 */
	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	/**
	 * 상태 코드 Getter
	 * @return HTTP 상태 코드
	 */
	public int getStatus() {
		return this.status;
	}
	
	/**
	 * 메시지 Getter
	 * @return 에러 메시지
	 */
	public String getMessage() {
		return this.message;
	}
}
