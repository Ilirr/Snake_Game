package application.Pieces;

import java.util.Collection;

import application.Board;
import application.Piece;
import application.Square;

public class King extends Piece{

	public King(int color, Square square) {
		super(color, square);
	}

	@Override
	public Collection<Square> legalMoves(Board board) {
		
		
		Collection<Square> legalSquare = null;
		
		int kingX = this.getSquare().getPosition().getX();
		int kingY = this.getSquare().getPosition().getY();
		
	
		
		
		return legalSquare;
		
	}


}
