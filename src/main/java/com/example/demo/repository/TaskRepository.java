package com.example.demo.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Task;
import com.example.demo.form.TaskForm;

@Repository
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 新規登録処理
     * 
     * @param task
     */
    public void insertTask(TaskForm task) {
	String query = """
		INSERT INTO Task
		(
		title,
		content
		)
		VALUES(
		?,
		?
		)
		""";
	jdbcTemplate.update(query, task.getTitle(), task.getContent());
    }

    /**
     * タスクを全て取得
     * 
     * @return
     */
    public List<Task> selectTasks() {
	String query = """
		SELECT
		  *
		FROM
		  Task
		""";
	RowMapper<Task> rowMapper = new TaskRowMapper();
	return jdbcTemplate.query(query, rowMapper);
    }

}
