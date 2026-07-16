package com.example.todoapp.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * 회원 정보
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String nickname;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	/**
	 * JPA 기본 생성자
	 */
	protected User() {}
	
	/**
	 * 회원 생성
	 * @param email - 이메일 (로그인 아이디)
	 * @param password - 비밀번호 (암호화 적용)
	 * @param nickname - 닉네임
	 */
	public User(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.createdAt = LocalDateTime.now();
	}
	
	/**
	 * id Getter
	 * @return 회원 id
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * email Getter
	 * @return 이메일
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * password Getter
	 * @return 비밀번호 (암호화 적용)
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * nickname Getter
	 * @return 닉네임
	 */
	public String getNickname() {
		return this.nickname;
	}
	
	/**
	 * createdAt Getter
	 * @return 가입 시각
	 */
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}
}
