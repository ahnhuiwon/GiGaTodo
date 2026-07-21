package com.example.todoapp.auth;

import com.example.todoapp.global.jwt.JwtProvider;
import com.example.todoapp.user.User;
import com.example.todoapp.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 인증(로그인) 비즈니스 로직 클래스
 */
@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	
	/**
	 * AuthService 인스턴스 할당 메소드
	 * @param userRepository - 회원 저장소
	 * @param passwordEncoder - 비밀번호 비교용 인코더
	 * @param jwtProvider - JWT 발급 도구
	 */
	public AuthService(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder,
			JwtProvider jwtProvider
	) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
	}
	
	/**
	 * 로그인 로직 메소드
	 * @param email - 이메일
	 * @param password - 평문 비밀번호
	 * @return 발급된 JWT 문자열
	 */
	public String login(String email, String password) {
		
		// 이메일 회원 찾기
		User user = this.userRepository.findByEmail(email)
						.orElseThrow(() -> new InvalidCredentialsException());
		
		// 비밀번호 비교
		if(!this.passwordEncoder.matches(password, user.getPassword())) {
			throw new InvalidCredentialsException();
		}
		
		// JWT 발급
		return this.jwtProvider.createToken(user.getEmail());
	}
}
