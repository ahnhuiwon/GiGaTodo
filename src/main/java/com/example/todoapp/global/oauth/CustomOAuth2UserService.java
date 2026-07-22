package com.example.todoapp.global.oauth;

import com.example.todoapp.user.AuthProvider;
import com.example.todoapp.user.User;
import com.example.todoapp.user.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 소셜 로그인 성공 시, 해당 소속으로 받은 사용자 정보로 회원을
 * 생성/조회하는 서비스 클래스
 */
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	
	private final UserRepository userRepository;
	/**
	 * CustomOAuth2UserService 생성
	 * @param userRepository 회원 저장소
	 */
	public CustomOAuth2UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
     * 소속으로부터 사용자 정보를 받아 회원을 생성/조회하고, 인증용 사용자 정보를 반환한다.
     * @param userRequest OAuth2 사용자 요청 정보
     * @return 인증에 사용할 OAuth2User
     * @throws OAuth2AuthenticationException 인증 처리 중 오류
     */
	@Override
	@Transactional
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		AuthProvider provider = AuthProvider.valueOf(registrationId.toUpperCase());
		
		String providerId = oAuth2User.getAttribute("sub");
		String email = oAuth2User.getAttribute("email");
		String name = oAuth2User.getAttribute("name");
		String nickname = (name != null) ? name : (email != null ? email : "사용자");
		
		this.userRepository.findByProviderAndProviderId(provider, providerId)
			.orElseGet(() -> this.userRepository.save(new User(email, nickname, provider, providerId)));
		
		return oAuth2User;
	}
	
}
