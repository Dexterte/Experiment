package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.entity.Task;

public class TaskRowMapper implements RowMapper<Task> {

    /**
     * データベースのレコードとオブジェクトのマッピングを行う
     */
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
	var task = new Task();
	task.setTitle(rs.getString("title"));
	task.setContent(rs.getString("content"));
	return task;
    }
}
