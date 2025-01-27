package com.in28minutes.springboot.learn_jpa_and_hibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
import com.in28minutes.springboot.learn_jpa_and_hibernate.course.jdbc.CourseJdbcRepository;
import com.in28minutes.springboot.learn_jpa_and_hibernate.course.jpa.CourseJpaRepository;
import com.in28minutes.springboot.learn_jpa_and_hibernate.course.springdatajp.CourseSpringDataJpaRepository;
@Component
public class CourseCommandLineRunner implements CommandLineRunner {
	
//	@Autowired
//	private CourseJdbcRepository repository;
	
//	@Autowired
//	private CourseJpaRepository repository;
	
	@Autowired
	private CourseSpringDataJpaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.save(new Course(1, "Learn AWS JPA!", "in28minutes"));
		repository.save(new Course(2, "Learn Azure Now!", "in28minutes"));
		repository.save(new Course(3, "Learn Devops and JPA!", "in28minutes"));
		
//		repository.delete(1L);
		repository.deleteById(1L);
		
		
//		System.out.println(repository.select(2L));
//		System.out.println(repository.select(3L));
		
		System.out.println(repository.findById(2L));
		System.out.println(repository.findById(3L));
		
//		repository.
		
		System.out.println(repository.findAll());
		System.out.println(repository.count());
		System.out.println(repository.findByAuthor("in28minutes"));
		System.out.println(repository.findByName("Learn Azure Now!"));
		
		
	}
	

}
