package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.game.GameRunner;
import com.example.demo.game.GamingConsole;
import com.example.demo.game.PacManGame;


@Configuration
public class GamingConfiguration {
	
	@Bean
	public GamingConsole game() {
		var game = new PacManGame();
		return game;
	}
	
	@Bean
	public GameRunner gameRunner(GamingConsole game) {
		var gameRunner = new GameRunner(game);
		return gameRunner;
	}
}