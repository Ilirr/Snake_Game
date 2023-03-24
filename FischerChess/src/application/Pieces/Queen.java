package application.Pieces;

import java.util.ArrayList;
import java.util.Collection;

import application.Board;
import application.Piece;
import application.Square;
import javafx.scene.paint.Color;

public class Queen extends Piece {

	public Queen(int color, Square square) {
		super(color, square);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Square> legalMoves(Board board) {
		ArrayList<Square> legalMoves = new ArrayList<Square>();
		
		
		int queenX = this.getSquare().getPosition().getX();
		int queenY = this.getSquare().getPosition().getY();
		
		
		
		return legalMoves;
	}

}
