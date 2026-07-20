package com.example.todoapp.user;

/**
 * 이미 존재하는 이메일 검증 예외 처리 클래스
 */
public class DuplicateEmailException extends RuntimeException {
	
	/**
	 * 이미 존재하는 이메일 예외 처리 메소드
	 * @param email - 중복된 이메일
	 */
	public DuplicateEmailException(String email) {
		super("이미 사용 중인 이메일입니다.");
	}
}
