package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
@Repository
public class CourseJdbcRepository {
	@Autowired
	private JdbcTemplate springJdbcTemplate;
	
	private static String INSERT_QUERY = 
			"""
			insert into course (id,name,author)
            values(?, ?, ?);
			       """;
	private static String Delete_QUERY = 
			"""
			delete from course
            where id=?;
			       """;
	
	private static String Select_QUERY = 
			"""
			Select * from course
			where id = ?;
			       """;
	
	public void insert(Course course) {
		springJdbcTemplate.update(INSERT_QUERY,course.getId(),course.getName(),course.getAuthor());
	}
	public void delete(long id) {
		springJdbcTemplate.update(Delete_QUERY,id);
	}
	
//		Resultset => Bean are called Row Mappers
	public Course select(long id) {
		return springJdbcTemplate.queryForObject(Select_QUERY,
				new BeanPropertyRowMapper<>(Course.class),id);
	}
}
