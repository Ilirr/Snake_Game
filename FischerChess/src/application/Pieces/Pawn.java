package application.Pieces;

import java.util.ArrayList;
import java.util.Collection;

import application.Board;
import application.Piece;
import application.Position;
import application.Square;

public class Pawn extends Piece {

	private boolean canEnPassant = false;

	private boolean hasMoved;
	public Pawn(int color, Square square) {
		super(color, square);
	}
	public void setHasMoved(boolean moved)
	{
		hasMoved = moved;
	}

	public boolean getHasMoved()
	{
		return hasMoved;
	}
	
	@Override
	public Collection<Square> legalMoves(Board board) {
		ArrayList<Square> legalMoves = new ArrayList<Square>();
		Position myPos = this.getSquare().getPosition();
		Square forward; // Pawn can only move forward , or en pessant
		if (this.getHasMoved()) 
		{
			//forward = new Square(this, new Position(myPos.getX(), myPos.getY() + 1));

		} else {
			//forward = new Square(this, new Position(myPos.getX(), myPos.getY() + 2));

		}

		// Check if move puts own king in check
		

		//legalMoves.add(forward);

		return legalMoves;
	}

}
