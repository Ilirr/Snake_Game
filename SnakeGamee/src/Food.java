import java.awt.Point;

public class Food extends Collectible {

	private int scoreValue = 1;

	public Food(Point position, int scoreValue) {
		super(Collectible.Type.Food, position, scoreValue);
		this.scoreValue = scoreValue;
		// TODO Auto-generated constructor stub
	}

}
