import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private GameGUI gui;

	public GamePanel(GameGUI gui) {
		this.gui = gui;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// draw the field
		for (Point[] points : gui.getField()) {
			for (Point point : points) {
				g.setColor(Color.BLACK);
				g.drawRect(point.x * gui.getCellSize(), point.y * gui.getCellSize(), gui.getCellSize(),
						gui.getCellSize());
			}
		}

		// draw the food

		for (Collectible collectible : gui.getCollectibles()) {
			if (collectible instanceof Bomb) {
				g.setColor(Color.RED);

			} else {
				g.setColor(Color.GREEN);
			}
			g.fillRect(collectible.getPosition().x * gui.getCellSize(), collectible.getPosition().y * gui.getCellSize(),
					gui.getCellSize(), gui.getCellSize());

		}
		// draw the snake
		gui.getSnake().draw(g);

	}

}
