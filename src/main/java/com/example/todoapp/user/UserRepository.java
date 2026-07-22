package com.example.todoapp.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todoapp.user.AuthProvider;

/**
 * 회원 저장 클래스
 */
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * 이메일로 회원 조회
	 * @param email - 조회 이메일
	 * @return - 조회된 회원 정보
	 */
	Optional<User> findByEmail(String email);
	
	/**
	 * 소셜 로그인 소속과 소셜 로그인 id로 회원 조회
	 * @param provider 소셜 로그인 소속
	 * @param providerId 소속 고유 id
	 * @return 회원 (없으면 비어있음)
	 */
	Optional<User> findByProviderAndProviderId(AuthProvider provider, String providerId);
	
	/**
	 * 이메일 존재 여부 확인 (회원가입 중복 체크)
	 * @param email - 확인할 이메일
	 * @return - 이메일이 있을 경우 true / 없으면 false
	 */
	boolean existsByEmail(String email);
}
