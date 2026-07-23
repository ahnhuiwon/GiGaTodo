package com.example.todoapp.todo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.example.todoapp.user.User;

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
	
	@Column
	private LocalDateTime completedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	// JPA 기본 생성자
	protected Todo() {}
	
	/**
	 * 인스턴스 할당 메소드
	 * @param content - 할 일 내용
	 * @param user - 작성자
	 */
	public Todo(String content, User user) {
		this.content = content;
		this.user = user;
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
	/**
	 * Todo 작성자 Getter
	 * @return - Todo 작성자
	 */
	public User getUser() {
		return this.user;
	}
	
	public void updateContent(String content) {
		this.content = content;
	}
	
	/**
	 * 완료 시각 반환 Getter
	 * @return 완료 시각 (미완료면 null)
	 */
	public LocalDateTime getCompletedAt() {
		return this.completedAt;
	}
	
	/**
	 * 완료 여부를 토글하고
	 * 완료 시간을 기록 또는 제거하는 메소드
	 */
	public void toggleDone() {
		this.done = !this.done;
		
		if(this.done) {
			// 완료 시 시간 기록
			this.completedAt = LocalDateTime.now();
		} else {
			// 취소 시 시간 기록 제거
			this.completedAt = null;
		}
	}
	
}
