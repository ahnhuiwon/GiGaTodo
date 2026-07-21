package com.example.todoapp.auth;

import com.example.todoapp.auth.dto.LoginRequest;
import com.example.todoapp.auth.dto.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 API 클래스
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthService authService;
	
	/**
	 * 인스턴스 할당 메소드
	 * @param authService - 인증 서비스
	 */
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	/**
	 * 로그인 성공 시 JWT 발급 메소드
	 * @param request - 로그인 요청 (이메일, 비밀번호)
	 * @return - 발급된 토큰
	 */
	@PostMapping("/login")
	public LoginResponse login(@Valid @RequestBody LoginRequest request) {
		String token = this.authService.login(request.getEmail(), request.getPassword());
		return new LoginResponse(token);
	}
}
