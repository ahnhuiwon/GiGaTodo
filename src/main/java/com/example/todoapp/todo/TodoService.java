package com.example.todoapp.todo;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoService {

	private final TodoRepository todoRepository;
	
	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	/**
	 * 투두리스트 생성
	 * @param content
	 * @return
	 */
	public Todo create(String content) {
		Todo todo = new Todo(content);
		return this.todoRepository.save(todo);
	}
	
	/**
	 * 투두리스트 전체 조회
	 * @return
	 */
	public List<Todo> findAll() {
		return this.todoRepository.findAll();
	}
	
	/**
	 * 투두리스트 아이디 기반 조회
	 * @param id
	 * @return
	 */
	public Todo findById(Long id) {
		return this.todoRepository.findById(id)
				.orElseThrow(() -> new TodoNotFoundException(id));
	}
	
	/**
	 * 투두리스트 삭제
	 * @param id
	 */
	public void delete(Long id) {
		this.todoRepository.deleteById(id);
	}
	
	@Transactional
	public Todo updateContent(long id, String content) {
		Todo todo = this.findById(id);
		todo.updateContent(content);
		return todo;
	}
	
	@Transactional
	public Todo toggleDone(Long id) {
		Todo todo = this.findById(id);
		todo.toggleDone();
		return todo;
	}
}
