package com.example.todoapp.todo.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoCreateRequest {
	
	@NotBlank(message = "content는 필수입니다.")
	private String content;
	
	/**
	 * content(본문) Getter
	 * @return
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * content(본문) Setter
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
}
