package com.example.demo.helloworld;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import com.example.learn_spring_framework.game.GameRunner;
//import com.example.learn_spring_framework.game.MarioGame;
//import com.example.learn_spring_framework.game.PacManGame;
//import com.example.learn_spring_framework.game.SuperContractGame;

public class App02HelloWorldSpring {

	public static void main(String[] args) {
//		 TODO Auto-generated method stub
//	     Launch a spring context
		try(var context = 
				new AnnotationConfigApplicationContext(HelloWorldConfiguration.class))
		{
//		 Configure the things that we want Spring to manage
		System.out.println(context.getBean("yourCustomBean"));
		System.out.println(context.getBean("age"));
		System.out.println(context.getBean("person"));
		System.out.println(context.getBean("address"));
		System.out.println(context.getBean("person2MethodCall"));
		System.out.println(context.getBean("person3Parameters"));
		
		System.out.println(context.getBean(Person.class));
		
		Arrays.stream(context.getBeanDefinitionNames())
		.forEach(System.out::println);
		}
	}

}
