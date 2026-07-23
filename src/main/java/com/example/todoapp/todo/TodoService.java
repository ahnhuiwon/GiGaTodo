package com.example.todoapp.todo;

import com.example.todoapp.user.User;
import com.example.todoapp.user.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoService {
	
	private final TodoRepository todoRepository;
	private final UserRepository userRepository;
	
	/**
	 * TodoService 인스턴스 할당
	 * @param todoRepository - todoRepository
	 * @param userRepository - userRepository
	 */
	public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
	}
	
	/**
	 * Todo를 생성하는 메소드
	 * @param email - 로그인한 사용자 이메일
	 * @param content - Todo
	 * @return - 저장된 Todo
	 */
	@Transactional
	public Todo create(Long userId, String content) {
		User user = this.getUser(userId);
		Todo todo = new Todo(content, user);
		return this.todoRepository.save(todo);
	}
	
	/**
	 * Todo 전체를 조회하는 메소드
	 * @param email - 로그인한 사용자 이메일
	 * @return - 해당 사용자의 Todo 리스트
	 */
	public List<Todo> findAll(Long userId) {
		User user = this.getUser(userId);
		return this.todoRepository.findByUser(user);
	}
	
	/**
	 * Todo 단건 조회 메소드
	 * @param id - Todo id
	 * @param email - 로그인한 사용자 이메일
	 * @return - 조회된 단건 Todo
	 */
	public Todo findById(Long id, Long userId) {
		User user = this.getUser(userId);
		return this.getOwnedTodo(id, user);
	}
	
	/**
	 * Todo 내용을 수정하는 메소드
	 * @param id - Todo id
	 * @param email - 로그인한 사용자 이메일
	 * @param content - 수정할 내용
	 * @return - 수정된 Todo
	 */
	@Transactional
	public Todo updateContent(Long id, Long userId, String content) {
		User user = this.getUser(userId);
		Todo todo = this.getOwnedTodo(id, user);
		todo.updateContent(content);
		return todo;
	}
	
	/**
	 * Todo 완료 상태를 토글한다.
	 * @param id - Todo id
	 * @param email - 로그인한 사용자 이메일
	 * @return - 변경된 Todo
	 */
	@Transactional
	public Todo toggleDone(Long id, Long userId) {
		User user = this.getUser(userId);
		Todo todo = this.getOwnedTodo(id, user);
		todo.toggleDone();
		return todo;
	}
	
	/**
	 * 로그인한 사용자의 Todo를 삭제한다.
	 * @param id - Todo id
	 * @param email - 로그인한 사용자 이메일
	 */
	@Transactional
	public void deleteTodo(Long id, Long userId) {
		User user = this.getUser(userId);
		Todo todo = this.getOwnedTodo(id, user);
		this.todoRepository.delete(todo);
	}
	
	/**
	 * 이메일로 사용자를 찾는 메소드
	 * @param email - 사용자 이메일
	 * @return - 사용자
	 */
	private User getUser(Long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다."));
	}
	
	/**
	 * Todo 소유권 확인 메소드
	 * @param id - Todo id
	 * @param user - 로그인한 사용자
	 * @return - 사용자 소유의 Todo
	 */
	private Todo getOwnedTodo(Long id, User user) {
		Todo todo = this.todoRepository.findById(id)
						.orElseThrow(() -> new TodoNotFoundException(id));
		
		if(!todo.getUser().getId().equals(user.getId())) {
			throw new TodoNotFoundException(id);
		}
		
		return todo;
	}
}