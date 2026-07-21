package com.example.todoapp.global.config;


import com.example.todoapp.global.jwt.JwtAuthenticationEntryPoint;
import com.example.todoapp.global.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 설정
 */
@Configuration
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	/**
	 * 인스턴스 할당 메소드
	 * @param jwtAuthenticationFilter - JWT 인증 필터
	 * @param jwtAuthenticationEntryPoint 인증 실패 시 401 응답 진입점
	 */
	public SecurityConfig(
			JwtAuthenticationFilter jwtAuthenticationFilter,
			JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint
	) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
	}
	
	
	/**
	 * 보안 필터 체인 설정 메소드
	 * @param http - HttpSecurity 설정 객체
	 * @return - 구성된 SecurityFilterChain
	 * @throws Exception - 설정 중 오류
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session
        		.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
        		.requestMatchers("/api/auth/**").permitAll()
        		.requestMatchers(HttpMethod.POST, "/api/users/signup").permitAll()
        		.anyRequest().authenticated())
        .exceptionHandling(ex -> ex.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
        .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
		return http.build();
	}
	
	/**
	 * 비밀번호 암호화 인코더 메소드
	 * @return PasswordEncoder 구현 개체
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
