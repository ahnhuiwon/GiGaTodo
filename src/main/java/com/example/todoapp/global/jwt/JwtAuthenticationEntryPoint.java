package com.example.todoapp.global.jwt;

import com.example.todoapp.global.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 인증되지 않은 요청이 접근할 때 401 응답 반환 클래스
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private final ObjectMapper objectMapper;
	
	/**
	 * 인스턴스 할당 메소드
	 * @param objectMapper - 객체를 JSON 문자열로 변환하는 패키지
	 */
	public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	/**
     * 인증 실패 시 401 상태와 에러 JSON을 응답에 반환.
     * @param request - HTTP 요청
     * @param response - HTTP 응답
     * @param authException - 발생한 인증 예외
     * @throws IOException - 응답 작성 중 입출력 오류
     */
	@Override
	public void commence(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authException
	) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		
		ErrorResponse body = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "인증이 필요합니다.");
		response.getWriter().write(this.objectMapper.writeValueAsString(body));
	}
}
