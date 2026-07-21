package com.example.todoapp.auth.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * 로그인 요청
 */
public class LoginRequest {

	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;
	
	/**
	 * 이메일 Getter
	 * @return - 이메일
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * 이메일 Setter
	 * @param email - 이메일
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 비밀번호 Getter
	 * @return - 비밀번호
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * 비밀번호 Setter
	 * @param password - 비밀번호
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
