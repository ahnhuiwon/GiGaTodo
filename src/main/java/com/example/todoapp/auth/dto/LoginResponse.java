package com.example.todoapp.auth.dto;

public class LoginResponse {

	private final String token;
	
	/**
	 * 로그인 응답을 생성하는 메소드
	 * @param token - 발급된 JWT 문자열
	 */
	public LoginResponse(String token) {
		this.token = token;
	}
	
	/**
	 * 토큰을 반환하는 메소드
	 * @return - JWT 문자열
	 */
	public String getToken() {
		return this.token;
	}
}
