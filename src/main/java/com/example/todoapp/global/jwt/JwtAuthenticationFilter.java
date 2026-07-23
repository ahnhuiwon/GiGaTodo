package com.example.todoapp.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 요청마다 JWT를 검사하는 필터 클래스
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtProvider jwtProvider;
	
	/**
	 * 인스턴스 할당 메소드
	 * @param jwtProvider - JWT 검증 도구
	 */
	public JwtAuthenticationFilter(JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
	) throws ServletException, IOException {
		String token = this.resolveToken(request);
		
		if(token != null && this.jwtProvider.validateToken(token)) {
			Long userId = this.jwtProvider.getUserId(token);
			Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, List.of());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
	
	/**
	 * Authorization 헤더에서 뒤의 토큰을 꺼내는 메소드
	 * @param request - request HTTP 요청
	 * @return - 토큰 문자열, 없을경우 null
	 */
	private String resolveToken(HttpServletRequest request) {
		String bearer = request.getHeader("Authorization");
		
		if(bearer != null && bearer.startsWith("Bearer ")) {
			return bearer.substring(7);
		}
		
		return null;
	}
}
