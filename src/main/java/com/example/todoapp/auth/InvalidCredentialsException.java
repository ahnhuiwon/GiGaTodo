package com.example.todoapp.auth;

public class InvalidCredentialsException extends RuntimeException {
	
	/**
	 * 예외를 생성하는 메소드
	 */
	public InvalidCredentialsException() {
		super("이메일 또는 비밀번호가 올바르지 않습니다.");
	}
}
