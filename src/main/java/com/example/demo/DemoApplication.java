package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;
import java.util.List;
@SpringBootApplication

public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

	@Override
	public void run(String... args) throws Exception{
		JFrame window = new frame();
//		String sql = "SELECT * FROM grades";
//		List<Grades> grades = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Grades.class));
//		grades.forEach(System.out :: println);
	}

}
