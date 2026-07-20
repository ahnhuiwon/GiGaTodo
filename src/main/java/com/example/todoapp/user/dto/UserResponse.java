package com.example.todoapp.user.dto;

import com.example.todoapp.user.User;
import java.time.LocalDateTime;

public class UserResponse {

	private final Long id;
	private final String email;
	private final String nickname;
	private final LocalDateTime createdAt;
	
	/**
	 * Entity 객체로부터 응답 DTO 생성
	 * @param user - 회원 Entity
	 */
	public UserResponse(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.nickname = user.getNickname();
		this.createdAt = user.getCreatedAt();
	}
	
	/**
	 * ID Getter
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * 이메일 Getter
	 * @return
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * nickname Getter
	 * @return
	 */
	public String getNickname() {
		return this.nickname;
	}
	
	/**
	 * 가입 시간 Getter
	 * @return
	 */
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}
}
