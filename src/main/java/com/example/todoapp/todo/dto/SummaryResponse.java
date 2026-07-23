package com.example.todoapp.todo.dto;

/**
 * 기간별 총평 응답 메소드
 */
public class SummaryResponse {
	
	private final long createdCount;
	private final long completedCount;
	
	/**
	 * 총평 응답을 생성하는 메소드
	 * @param createdCount 생성 개수
	 * @param completedCount 완료 개수
	 */
	public SummaryResponse(long createdCount, long completedCount) {
		this.createdCount = createdCount;
		this.completedCount = completedCount;
	}
	
	/**
	 * 생성 개수를 반환하는 Getter
	 * @return 생성 개수
	 */
	public long getCreatedCount() {
		return this.createdCount;
	}
	
	/**
	 * 완료 개수를 반환하는 Getter
	 * @return 완료 개수
	 */
	public long getCompletedCount() {
		return this.completedCount;
	}
}
