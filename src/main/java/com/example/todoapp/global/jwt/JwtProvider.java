package com.example.todoapp.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JWT 토큰 생성 및 검증 도구 클래스
 */
@Component
public class JwtProvider {

    private final SecretKey key;
    private final long validityMs;
    
    /**
     * 
     * @param secret - 토큰 서명용 비밀키
     * @param validtyMs - 토큰 유효시간
     */
    public JwtProvider(
    		@Value("${jwt.secret}") String secret,
    		@Value("${jwt.validity-ms}") long validityMs    		
    ) {
    	this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    	this.validityMs = validityMs;
    }
    
    /**
     * 토큰 생성 메소드
     * @param userId 회원 Id
     * @return 생성된 JWT 문자열
     */
    public String createToken(Long userId) {
    	Date now = new Date();
    	Date expiry = new Date(now.getTime() + this.validityMs);
    	
    	return Jwts.builder()
    				.subject(String.valueOf(userId))
    				.issuedAt(now)
    				.expiration(expiry)
    				.signWith(this.key)
    				.compact();
    }
    
    /**
     * 토큰에서 회원 id를 추출 메소드
     * @param token - JWT 문자열
     * @return - 회원 id
     */
    public Long getUserId(String token) {
    	String subject = Jwts.parser()
    						.verifyWith(this.key)
    						.build()
    						.parseSignedClaims(token)
    						.getPayload()
    						.getSubject();
    	return Long.valueOf(subject);
    }
    
    /**
     * 토큰이 유효한지 검사 메소드
     * @param token - JWT 문자열
     * @return - 유효하면 true, 위조 또는 만료면 false
     */
    public boolean validateToken(String token) {
    	try {
    		Jwts.parser()
    			.verifyWith(this.key)
    			.build()
    			.parseSignedClaims(token);
    		
    		return true;
    	} catch (Exception e) {
    		return false;
    	}
    }
}
