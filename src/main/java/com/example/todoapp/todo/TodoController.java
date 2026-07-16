package com.example.todoapp.todo;

import com.example.todoapp.todo.dto.TodoCreateRequest;
import com.example.todoapp.todo.dto.TodoUpdateRequest;

import com.example.todoapp.todo.dto.TodoResponse;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	private final TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	/**
     * 투두리스트 생성: POST /api/todos
     * @param request 생성 요청 본문
     * @return 생성된 Todo (201 Created)
     */
	@PostMapping
	public ResponseEntity<TodoResponse> create(@Valid @RequestBody TodoCreateRequest request) {
		Todo todo = this.todoService.create(request.getContent());
		TodoResponse body = new TodoResponse(todo);
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}
	
	/**
	 * 투두리스트 전체 조회: GET /api/todos
	 * @return
	 */
	@GetMapping
	public List<TodoResponse> findAll() {
		return this.todoService.findAll().stream()
				.map(TodoResponse::new)
				.toList();
	}
	
	/**
	 * 투두리스트 단건 조회: GET /api/todos/{id}
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public TodoResponse findById(@PathVariable("id") Long id) {
		Todo todo = this.todoService.findById(id);
		return new TodoResponse(todo);
	}
	
	
	/**
     * 투두리스트 단건 삭제: DELETE /api/todos/{id}
     * @param id 삭제할 Todo의 id
     * @return 빈 응답 (204 No Content)
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		this.todoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public TodoResponse updateContent(
			@PathVariable("id") Long id, 
			@Valid @RequestBody TodoUpdateRequest request) 
	{
		Todo todo = this.todoService.updateContent(id, request.getContent());
		return new TodoResponse(todo);
	}
	
	@PatchMapping("/{id}/done")
	public TodoResponse toggleDone(@PathVariable("id") Long id) {
		Todo todo = this.todoService.toggleDone(id);
		return new TodoResponse(todo);
	}
}
