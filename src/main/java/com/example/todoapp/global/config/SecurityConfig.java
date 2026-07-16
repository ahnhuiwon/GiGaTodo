package com.example.todoapp.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 설정
 */
@Configuration
public class SecurityConfig {
	
	/**
	 * 보안 필터 체인 설정 메소드
	 * @param http - HttpSecurity 설정 객체
	 * @return - 구성된 SecurityFilterChain
	 * @throws Exception - 설정 중 오류
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()
        );
		return http.build();
	}
	
	/**
	 * 비밀번호 암호화 인코더 메소드
	 * @return PasswordEncoder 구현 개체
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
