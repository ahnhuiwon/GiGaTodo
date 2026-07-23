package com.example.todoapp.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.todoapp.user.User;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.todoapp.todo.dto.DailyCompletion;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	
	/**
	 * 작성자의 Todo 목록을 조회
	 * @param user - 작성자
	 * @return - 작성자의 Todo 목록
	 */
	List<Todo> findByUser(User user);
	
	@Query(value="""
					SELECT CAST(completed_at AS DATE) AS date, COUNT(*) AS count
					FROM 
							todos
					WHERE
							user_id = :userId AND done = true
					AND
							completed_at >= :start AND completed_at < :end
					GROUP BY CAST
							(completed_at AS DATE)
					ORDER BY
							date
	""", nativeQuery = true)
	
	List<DailyCompletion> countCompletedbyDay(
			@Param("userId") Long userId,
			@Param("start") LocalDateTime start,
			@Param("end") LocalDateTime end
	);
	
	/**
	 * 기간 내 사용자가 생성한 Todo 개수
	 * @param user 사용자
	 * @param start 시작 일자
	 * @param end 마지막 일자
	 * @return 생성 개수
	 */
	long countByUserAndCreatedAtBetween(User user, LocalDateTime start, LocalDateTime end);
	
	/**
	 * 기간 내 사용자가 완료한 Todo 개수
	 * @param user 사용자
	 * @param start 시작 일자
	 * @param end 마지막 일자
	 * @return 완료 개수
	 */
	long countByUserAndDoneTrueAndCompletedAtBetween(User user, LocalDateTime start, LocalDateTime end);
}
