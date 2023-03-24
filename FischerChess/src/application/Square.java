package application;

import javafx.scene.paint.Color;

public class Square {
	private Piece piece;
	private Position position;
	private int color;
	public Square(Piece piece, Position position, int color) {
		if (piece == null) {
			this.piece = null;
		} else {
			this.piece = piece;
		}
		this.color = color;
		this.position = position;
	}
	public Position getPosition() {
		return position;
	}
	public int getColor()
	{
		return color;
	}
	public Piece getPiece()
	{
		return piece;
	}
	
	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}
	

	public Square getOppositeStartPosition() {
		int squareX = this.getPosition().getX();
		int squareY = 8;
		Square newSquare = new Square(null, new Position(squareX, squareY),this.color);
		
		return newSquare;
		
	}

}
