package com.example.learn_spring_framework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component 
@Qualifier("PacManGameQualifier")
public class PacManGame implements GamingConsole {
	public void up() {
		System.out.println("Go UP");
	}

	public void down() {
		System.out.println("Go Down");
	}

	public void left() {
		System.out.println("Go towards left");
	}
	
	public void right() {
		System.out.println("Go towards right");
	}

}
