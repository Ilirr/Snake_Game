import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

public class Game {

	public enum GameState {
		playing, gameOver

	}

	private GameGUI gameGUI;
	private GameState gameState;

	public GameState getGameState() {
		return gameState;
	}

	public Game() {
		gameGUI = new GameGUI(this);
		gameState = GameState.playing;
		this.update();
	}

	public static void main(String[] args) {
		new Game();

	}

	public void update() {

		gameGUI.run();

	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

}
