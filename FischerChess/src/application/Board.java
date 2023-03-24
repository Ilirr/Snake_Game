package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import application.Pieces.Bishop;
import application.Pieces.King;
import application.Pieces.Knight;
import application.Pieces.Pawn;
import application.Pieces.Queen;
import application.Pieces.Rook;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends GridPane {

	private ArrayList<Character> pieces;

	private ArrayList<Piece> playingPieces;

	private ArrayList<Square> whiteSquaresList;
	private ArrayList<Square> blackSquaresList;

	private int currentTurn = 1; // 1 is white
	private Square[][] board = new Square[8][8];

	public Board() {
		pieces = new ArrayList<Character>(Arrays.asList('R', 'B', 'N', 'Q', 'K', 'N', 'B', 'R'));
		whiteSquaresList = new ArrayList<Square>();
		blackSquaresList = new ArrayList<Square>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int row = i + j / 8;
				int col = i + j % 8;
				if ((i + j) % 2 == 0) {
					this.board[i][j] = new Square(null, new Position(i, i), 1);
					whiteSquaresList.add(board[i][j]);
				} else {
					this.board[i][j] = new Square(null, new Position(i, i), 0);
					blackSquaresList.add(board[i][j]);
				}
			}
		}
		initWhitePieces();
		// initBlackPieces();
	}

	public int getTurn() {
		return currentTurn;
	}

	public void initWhitePieces() {
		Collections.shuffle(pieces);
		String order = pieces.toString().replaceAll("[^\\p{Upper}]", "");
		while (!order.matches(".*R.*K.*R.*") && order.charAt(0) == 'B') {

			Collections.shuffle(pieces);
			order = pieces.toString().replaceAll("[^\\p{Upper}]", "");

		}

		System.out.println(pieces);
	}

	public void FischerRandomStartingPosition() {

	}

	public void initBlackPieces() {
		Random random = new Random();
		// Add king opposite square of white king
		for (int i = 0; i < board.length; i++) {
			if (board[0][i].getPiece() instanceof King) {
				playingPieces.add(new King(0, board[0][i].getOppositeStartPosition()));
			}
		}
		// Add pawns
		for (int i = 0; i < 8; i++) {
			playingPieces.add(new Pawn(0, board[6][i]));
		}
		/*
		 * playingPieces.add(new Rook(); playingPieces.add(new Rook();
		 * playingPieces.add(new Knight(); playingPieces.add(new Knight();
		 * playingPieces.add(new Bishop(); playingPieces.add(new Bishop();
		 * playingPieces.add(new Queen();
		 */
	}

	public void setTurn(int turn) {
		currentTurn = turn;
	}

	public boolean isCheckMate() {

		return true;

	}

	public boolean nextMoveisCheck(Square destSquare, Color color) {

		return true;
	}

	public void removePieceAtSquare(Square square) {
		if (playingPieces.contains(square.getPiece())) {
			playingPieces.remove(square.getPiece());

		}
	}

	public boolean movePiece(Square oldSq, Square newSq) {
		Piece piece = board[oldSq.getPosition().getX()][oldSq.getPosition().getY()].getPiece();
		if (!piece.legalMoves(this).contains(newSq))
			return false;

		Position oldPos = oldSq.getPosition();
		Position newPos = newSq.getPosition();

		board[oldPos.getX()][oldPos.getY()].setPiece(null);
		board[newPos.getX()][newPos.getY()].setPiece(null);

		board[newPos.getX()][newPos.getY()].setPiece(piece);

		return true;
	}

	public void promotePawn(Piece piece) {
		Position pawnPos = piece.getSquare().getPosition();
		if (pawnPos.getY() == 8 || pawnPos.getY() == 1) {
			Piece newPiece = new Queen(piece.getColor(), piece.getSquare()); // temp queen

			// Create panel

			playingPieces.remove(piece);
			playingPieces.add(newPiece);
		}

	}

}
