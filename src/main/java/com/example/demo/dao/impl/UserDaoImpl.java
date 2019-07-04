package com.example.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.pojo.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insert(IUser user) {
		String sql = "insert into user(id,username,password,birthday) values(?,?,?,?)";
		return this.jdbcTemplate.update(
				sql, 
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				user.getBirthday());
	}

	@Override
	public int deleteById(Integer id) {
		String sql = "delete from user where id=?";
		return this.jdbcTemplate.update(sql, id);
	}

	@Override
	public int update(IUser user) {
		String sql = "update user set password=? where id=?";
		return this.jdbcTemplate.update(
                sql, 
                user.getPassword(),
                user.getId());
	}

	@Override
	public IUser getById(Integer id) {
		String sql = "select * from user where id = ?";
		return this.jdbcTemplate.queryForObject(sql, new RowMapper<IUser>(){

			@Override
			public IUser mapRow(ResultSet rs, int rowNum) throws SQLException {
				IUser user = new IUser();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
                user.setBirthday(rs.getDate("birthday"));
				return user;
			}
			
			
		},id);
	}
}
