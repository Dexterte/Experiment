package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.dto.User;

public class UserRowMapper implements RowMapper<User> {

    /**
     * データベースのレコードとオブジェクトのマッピングを行う
     */
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	var user = new User();
	user.setUserId(rs.getString("user_id"));
	user.setName(rs.getString("name"));
	user.setAge(rs.getString("age"));
	user.setSex(rs.getString("sex"));
	return user;
    }
}
