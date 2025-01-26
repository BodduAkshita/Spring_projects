package com.example.demo;

import com.example.demo.game.GameRunner;
import com.example.demo.game.MarioGame;
import com.example.demo.game.PacManGame;
import com.example.demo.game.SuperContractGame;

public class DemoApplication {

	public static void main(String[] args) {
		var game = new MarioGame();
//		var game = new PacManGame();
//		var game = new SuperContractGame();
		var gameRunner = new GameRunner(game);
		gameRunner.run();
	}

}
