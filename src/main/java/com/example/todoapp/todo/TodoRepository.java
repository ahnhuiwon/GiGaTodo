package com.example.todoapp.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todoapp.user.User;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	
	/**
	 * 작성자의 Todo 목록을 조회
	 * @param user - 작성자
	 * @return - 작성자의 Todo 목록
	 */
	List<Todo> findByUser(User user);
}
