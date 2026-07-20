package com.example.todoapp.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 회원 비즈니스 로직 클래스
 */
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * 회원 비즈니스 로직 인스턴스 할당 메소드
	 * @param userRepository - 회원 저장소
	 * @param passwordEncoder - 비밀번호 암호화 인코더
	 */
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
			this.userRepository = userRepository;
			this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * 회원가입 비즈니스 로직 메소드
	 * @param email - 회원 이메일
	 * @param password - 회원 비밀번호
	 * @param nickname - 회원 닉네임
	 * @return - 저장된 회원
	 */
	public User signUp(String email, String password, String nickname) {
		// 이메일 중복 확인
		if(this.userRepository.existsByEmail(email)) {
			throw new DuplicateEmailException(email);
		}
		
		// 비밀번호 암호화
		String encodedPassword = this.passwordEncoder.encode(password);
		
		// 회원 저장
		User user = new User(email, encodedPassword, nickname);
		
		return this.userRepository.save(user);
	}

}
