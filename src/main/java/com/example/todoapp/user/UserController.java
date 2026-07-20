package com.example.todoapp.user;

import com.example.todoapp.user.dto.SignupRequest;
import com.example.todoapp.user.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	
	/**
	 * 회원 서비스 인스턴스 할당 메소드
	 * @param userService - 회원 서비스
	 */
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserResponse> signUp(@Valid @RequestBody SignupRequest request) {
		User user = this.userService.signUp(
			request.getEmail(), 
			request.getPassword(), 
			request.getNickname()
		);

		UserResponse body = new UserResponse(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}
}
