import java.awt.Point;

public class Bomb extends Collectible {

	private final int scoreValue = -1;

	public Bomb(Point position, int scoreValue) {
		super(Collectible.Type.Bomb, position, scoreValue);

		// TODO Auto-generated constructor stub
	}

}
