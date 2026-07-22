package com.example.todoapp.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

/**
 * 회원 정보
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AuthProvider provider;
	
	@Column
	private String providerId;
	
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
		this.provider = AuthProvider.LOCAL;
		this.createdAt = LocalDateTime.now();
	}
	
	/**
	 * 소셜 회원을 생성
	 * @param email 이메일 (없을 수도 있음)
	 * @param password 닉네임
	 * @param provider SNS 제공자
	 * @param providerId SNS 제공자가 준 id
	 */
	public User(String email, String nickname, AuthProvider provider, String providerId) {
		this.email = email;
		this.nickname = nickname;
		this.provider = provider;
		this.providerId = providerId;
		this.createdAt = LocalDateTime.now();
	}
	
	/**
	 * SNS 제공자 Getter
	 * @return SNS 제공자
	 */
	public AuthProvider getProvider() {
		return this.provider;
	}
	
	/**
	 * SNS 제공자 고유 id Getter
	 * @return providerId (일반 가입자는 null)
	 */
	public String getProviderId() {
		return this.providerId;
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
