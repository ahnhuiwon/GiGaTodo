package com.example.todoapp.todo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private boolean done;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	// JPA 기본 생성자
	protected Todo() {}
	
	public Todo(String content) {
		this.content = content;
		this.done = false;
		this.createdAt = LocalDateTime.now();
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public boolean isDone() {
		return this.done;
	}
	
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}
	
	public void updateContent(String content) {
		this.content = content;
	}
	
	public void toggleDone() {
		this.done = !this.done;
	}
	
}
