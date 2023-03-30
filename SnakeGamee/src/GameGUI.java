import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class GameGUI extends JFrame implements KeyListener {

	private Random rand;
	// private JPanel gamePanel;
	private JLabel scoreLabel;

	private JLayeredPane pane = new JLayeredPane();

	private ArrayList<Collectible> collectibles;

	private GamePanel myGamePanel;

	private int score;

	private Food food;
	private Bomb bood;

	private Game game;
	private Snake snake;

	private static final int cellSize = 20;
	private static final int gameWidth = 30;
	private static final int gameHeight = 30;
	private Point[][] field = new Point[gameWidth][gameHeight];
	private static final int gameSize = cellSize * gameWidth * cellSize * gameHeight;

	private volatile long lastUpdateTime;
	private long collectibleTimer = 0;

	public GameGUI(Game game) {
		super("Snake Game");
		this.game = game;
		rand = new Random();
		collectibles = new ArrayList<Collectible>();
		for (int i = 0; i < gameWidth; i++) {
			for (int j = 0; j < gameHeight; j++) {
				field[i][j] = new Point(i, j);
			}
		}

		myGamePanel = new GamePanel(this);
		myGamePanel.setPreferredSize(new Dimension(gameWidth * cellSize, gameHeight * cellSize));
		myGamePanel.setBackground(Color.WHITE);

		scoreLabel = new JLabel("Score: 0");
		score = 0;

		snake = new Snake(this, new Point(gameWidth / 2, gameHeight / 2), 3, 1);

		this.add(scoreLabel, BorderLayout.NORTH);
		this.add(myGamePanel, BorderLayout.CENTER);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public Snake getSnake() {
		return snake;
	}

	public GameGUI getGameGUI() {
		return this;

	}

	public Game getGame() {
		return this.game;
	}

	public void generateCollectible() {
		//generate food every 2 seconds
		long currentTime = System.currentTimeMillis();
		if (currentTime - collectibleTimer >= 2000) {
			int x = rand.nextInt(gameWidth);
			int y = rand.nextInt(gameHeight);
			Collectible item;
			if ((x + y) % 2 == 0) {

				item = new Food(field[x][y], 1);
			} else {
				item = new Bomb(field[x][y], -1);

			}
			collectibles.add(item);
			collectibleTimer = currentTime;
		}

	}

	public void updateScore() {
		scoreLabel.setText("Score: " + score);
	}

	public void run() {
		Timer timer = new Timer(1000 / 12, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				generateCollectible();
				snake.move();
				checkCollision();
				updateScore();
				getGamePanel().repaint();
				if (game.getGameState() == Game.GameState.gameOver) {
					System.exit(0);
				}
			}
		});
		timer.start();
	}

	public Point[][] getField() {
		return field;
	}

	public int getCellSize() {
		return cellSize;
	}

	public void checkCollision() {
		//check if snake collides with collectible
		Point snakePosition = snake.getPosition();
		for (Collectible collectible : collectibles) {
			if (collectible instanceof Food) {
				Point foodPosition = collectible.getPosition();
				if (foodPosition.equals(snakePosition)) {
					// The snake has collided with the food
					snake.eat(collectible);
					collectibles.remove(collectible);
					score++;
					break;
				}
			} else if (collectible instanceof Bomb) {
				Point foodPosition = collectible.getPosition();
				if (foodPosition.equals(snakePosition)) {
					// The snake has collided with the food
					snake.eat(collectible);
					collectibles.remove(collectible);
					score = score + collectible.getScoreValue();
					break;
				}
			}
		}
	}

	public ArrayList<Collectible> getCollectibles() {
		return collectibles;
	}

	public GamePanel getGamePanel() {

		return myGamePanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		snake.setDirection(keyCode);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		return;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;

	}

}
