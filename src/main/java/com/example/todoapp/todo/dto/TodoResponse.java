package com.example.todoapp.todo.dto;

import com.example.todoapp.todo.Todo;
import java.time.LocalDateTime;

public class TodoResponse {

	private final Long id;
	private final String content;
	private final boolean done;
	private final LocalDateTime createdAt;
	
	/**
	 * 투두리스트 응답 객체 인스턴스 할당
	 * @param todo
	 */
	public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.done = todo.isDone();
        this.createdAt = todo.getCreatedAt();
    }
	
	/**
	 * ID(식별자) Getter
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * content(본문) Getter
	 * @return
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Done(여부) Getter
	 * @return
	 */
	public boolean isDone() {
		return this.done;
	}
	
	/**
	 * createdAt(생성일자) Getter
	 * @return
	 */
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}
	
}
