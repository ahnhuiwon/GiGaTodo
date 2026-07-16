package com.example.todoapp.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 회원가입 요청 클래스
 */
public class SignupRequest {

	@NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	private String email;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Size(min = 8, message = "비밀번호는 8자 이상이어야합니다.")
	private String password;
	
	@NotBlank(message = "닉네임은 필수입니다.")
	private String ninkname;
}
