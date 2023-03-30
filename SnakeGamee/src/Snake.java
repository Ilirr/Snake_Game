import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Snake {
	private ArrayList<Point> body;
	private int direction;

	private GameGUI gui;

	private Point position;

	public Snake(GameGUI gui, Point head, int length, int direction) {

		this.gui = gui;
		body = new ArrayList<>(length);
		body.add(head);

		for (int i = 1; i < length; i++) {
			Point part = new Point(head.x - i, head.y);
			body.add(part);
		}
		this.direction = direction;
	}

	// make snake eat the collectible on its position
	public void eat(Collectible item) {
		if (item.getType() == Collectible.Type.Food) {
			Point tail = body.get(body.size() - 1);
			int directionX = tail.x - body.get(body.size() - 2).x;
			int directionY = tail.y - body.get(body.size() - 2).y;
			Point newPart = new Point(tail.x + directionX, tail.y + directionY);
			body.add(newPart);

		} else if (item.getType() == Collectible.Type.Bomb) {

			body.remove(body.size() - 1);
			if (body.size() == 1) {
				gui.getGame().setGameState(Game.GameState.gameOver);

			}

		}

	}

	// draw the snake
	public void draw(Graphics g) {
		int cellSize = gui.getCellSize();
		for (Point segment : this.getBody()) {
			g.fillRect(segment.x * gui.getCellSize(), segment.y * gui.getCellSize(), gui.getCellSize(), cellSize);
			g.setColor(Color.BLACK);

		}
	}

	public Point getPosition() {
		return position;
	}

	public ArrayList<Point> getBody() {
		return body;
	}

	public void move() {

		// don't let the snake reverse direction
		int dx = 0, dy = 0;
		switch (direction) {
		case 0: // up
			dy = -1;
			break;
		case 1: // right
			dx = 1;
			break;
		case 2: // down
			dy = 1;
			break;
		case 3: // left
			dx = -1;
			break;
		}
		Point head = body.get(0);
		Point newHead = new Point(head.x + dx, head.y + dy);
		body.add(0, newHead);
		body.remove(body.size() - 1);
		for (int i = 1; i < body.size(); i++) {
			Point segment = body.get(i);
			// if snake has only head left, stop game
			if (newHead.equals(segment)) {
				gui.getGame().setGameState(Game.GameState.gameOver);
				return;
			}
		}
		position = head;

	}

	// set the direction of the snake
	public void setDirection(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_W:
			if (direction != 2) {
				direction = 0;
			}
			break;
		case KeyEvent.VK_D:
			if (direction != 3) {
				direction = 1;
			}
			break;
		case KeyEvent.VK_S:
			if (direction != 0) {
				direction = 2;
			}
			break;
		case KeyEvent.VK_A:
			if (direction != 1) {
				direction = 3;
			}

			break;
		}

	}

}
