package com.example.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.game.GameRunner;
import com.example.demo.game.GamingConsole;


public class App03GamingBasicJava {

	public static void main(String[] args) {
//		 TODO Auto-generated method stub
//		var game = new MarioGame();
//		var game = new PacManGame();
//		var game = new SuperContractGame();
//		var gameRunner = new GameRunner(game);
//		gameRunner.run();
		try(var context = 
				new AnnotationConfigApplicationContext
				    (GamingConfiguration.class))
		{
			context.getBean(GamingConsole.class).up();
			context.getBean(GameRunner.class).run();
		}
	}
}
