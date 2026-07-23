package com.example.todoapp.global.oauth;

import com.example.todoapp.global.jwt.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
	
	private final JwtProvider jwtProvider;
	private final ObjectMapper objectMapper;
	
	public OAuth2SuccessHandler(JwtProvider jwtProvider, ObjectMapper objectMapper) {
		this.jwtProvider = jwtProvider;
        this.objectMapper = objectMapper;
	}
	
	public void onAuthenticationSuccess(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication
	) throws IOException {
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		Long userId = oAuth2User.getAttribute("userId");
		String token = this.jwtProvider.createToken(userId);
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(this.objectMapper.writeValueAsString(Map.of("token", token)));
	}
}
