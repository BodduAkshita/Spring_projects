package com.example.learn_spring_framework.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.learn_spring_framework.game")
public class GamingAppLauncherApplication {
	public static void main(String[] args) {
//		 TODO Auto-generated method stub
//		var game = new MarioGame();
//		var game = new PacManGame();
//		var game = new SuperContractGame();
//		var gameRunner = new GameRunner(game);
//		gameRunner.run();
		try(var context = 
				new AnnotationConfigApplicationContext
				    (GamingAppLauncherApplication.class))
		{
			context.getBean(GamingConsole.class).up();
			context.getBean(GameRunner.class).run();
		}
	}

}
