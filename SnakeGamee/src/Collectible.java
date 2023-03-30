import java.awt.Point;

public abstract class Collectible {
	public enum Type {
		Food, Bomb
	}

	private Type type;
	private Point position;
	private int scoreValue;

	public Collectible(Type type, Point position, int scoreValue) {
		this.type = type;
		this.position = position;
		this.scoreValue = scoreValue;
	}

	public Type getType() {
		return type;
	}

	public Point getPosition() {
		return position;
	}

	public int getScoreValue() {
		return scoreValue;
	}
}
