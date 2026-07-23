package com.example.todoapp.todo;

import com.example.todoapp.todo.dto.TodoCreateRequest;
import com.example.todoapp.todo.dto.TodoResponse;
import com.example.todoapp.todo.dto.TodoUpdateRequest;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Todo API 컨트롤러 클래스
 */
@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	private final TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	/**
	 * Todo 생성 메소드
	 * @param email 로그인한 사용자 이메일
	 * @param request 생성 요청
	 * @return 생성된 Todo (상태 201)
	 */
	@PostMapping
	public ResponseEntity<TodoResponse> create(
			@AuthenticationPrincipal Long userId,
			@Valid @RequestBody TodoCreateRequest request
	) {
		Todo todo = this.todoService.create(userId, request.getContent());
		return ResponseEntity.status(HttpStatus.CREATED).body(new TodoResponse(todo));
	}
	
	/**
	 * Todo 전체 조회 메소드
	 * @param email 로그인한 사용자 이메일
	 * @return Todo 전체 리스트
	 */
	@GetMapping
	public List<TodoResponse> findAll(@AuthenticationPrincipal Long userId) {
		return this.todoService.findAll(userId).stream()
					.map(TodoResponse::new)
					.toList();
	}
	
	/**
	 * Todo 단건 조회 메소드
	 * @param email 로그인한 사용자 이메일
	 * @param id Todo id
	 * @return 조회된 Todo
	 */
	@GetMapping("/{id}")
	public TodoResponse findById(
			@AuthenticationPrincipal Long UserId,
			@PathVariable("id") Long id
	) {
		return new TodoResponse(this.todoService.findById(id, UserId));
	}
	
	/**
	 * 사용자 Todo 내용 변경 메소드
	 * @param email - 로그인한 사용자 이메일
	 * @param id - Todo id
	 * @param request - 수정 요청 내용
	 * @return 수정된 Todo
	 */
	@PutMapping("{id}")
	public TodoResponse updateContent(
			@AuthenticationPrincipal Long userId,
			@PathVariable("id") Long id,
			@Valid @RequestBody TodoUpdateRequest request
	) {
		return new TodoResponse(this.todoService.updateContent(id, userId, request.getContent()));
	}
	
	/**
	 * Todo 상태 토글 메소드
	 * @param email 로그인한 사용자 이메일
	 * @param id TOdo id
	 * @return 변경된 Todo
	 */
	@PatchMapping("/{id}/done")
	public TodoResponse toggleDone(
			@AuthenticationPrincipal Long userId,
			@PathVariable("id") Long id
	) {
		return new TodoResponse(this.todoService.toggleDone(id, userId));
	}
	
	/**
	 * 사용자 Todo 삭제 메소드
	 * @param email 로그인한 사용자 이메일
	 * @param id Todo id
	 * @return 빈 응답 (상태 204)
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@AuthenticationPrincipal Long userId,
			@PathVariable("id") Long id
	) {
		this.todoService.deleteTodo(id, userId);
		return ResponseEntity.noContent().build();
	}
}
