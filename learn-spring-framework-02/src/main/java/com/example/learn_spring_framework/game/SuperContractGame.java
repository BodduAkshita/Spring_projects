package com.example.learn_spring_framework.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SuperContractGame implements GamingConsole {
	
	public void up() {
		System.out.println("Up");
	}

	public void down() {
		System.out.println("Sit down");
	}

	public void left() {
		System.out.println("Go Back");
	}
	
	public void right() {
		System.out.println("Shoot a bullet");
	}
}
