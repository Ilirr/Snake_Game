package application;

import java.util.Collection;

public abstract class Piece {

	private final int color;

	private Square square;

	public Piece(int color, Square square) {
		this.color = color;
		this.square = square;

	}

	public abstract Collection<Square> legalMoves(Board board);

	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public int getColor() {
		return color;
	}
}