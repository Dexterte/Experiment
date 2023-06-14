package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public class Userpository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Userpository(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * ユーザ情報を全件取得
     * 
     * @return
     */
    public List<User> selectUsers() {
	String query = "SELECT * FROM User";
	RowMapper<User> rowMapper = new UserRowMapper();
	return this.jdbcTemplate.query(query, rowMapper);
    }

    /**
     * ユーザ情報を取得
     * 
     * @param name
     * @return
     */
    public List<User> selectUsersByName(String name) {
	String query = String.format("SELECT * FROM User WHERE name = '%s'", name);
	RowMapper<User> rowMapper = new UserRowMapper();
	return this.jdbcTemplate.query(query, rowMapper);
    }

}
