package com.example.demo.helloworld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person (String name, int age, Address address) {};

record Address (String firstLine, String city) {};
@Configuration
public class HelloWorldConfiguration {
	
	@Bean(name = "yourCustomBean")
	public String name() {
		return "Akshita";
	}
	
	@Bean
	public int age() {
		return 16;
	}
	
	
	@Bean
	public Person person() {
		var person = new Person("Akki", 20, new Address("123", "hyd"));
		return person;
	}
	
	//1.method call
	@Bean
	@Primary
	public Person person2MethodCall() {
		var person = new Person(name(), age(), address());
		return person;
	}
	
	@Bean
	public Person person3Parameters(String yourCustomBean, int age, Address address) {
		var person = new Person(yourCustomBean, age, address);
		return person;
	}
	
	
	
	@Bean
	public Address address() {
		var address = new Address("7-1-308", "Hyderabad");
		return address;
	}

}
