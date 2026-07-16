package com.example.todoapp.todo.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoUpdateRequest {

	@NotBlank(message = "content는 필수입니다.")
	private String content;
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
