package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
@SpringBootApplication

public class DemoApplication implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		String sql = "INSERT INTO recipe VALUES (878, 3, 'Spelnia oczekiania')";
		jdbcTemplate.update(sql);
//		String sql = "SELECT * FROM grades";
//		List<Grades> grades = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Grades.class));
//		grades.forEach(System.out :: println);
	}

}
