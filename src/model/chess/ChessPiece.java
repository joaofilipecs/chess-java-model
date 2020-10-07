package model.chess;

import java.util.List;

import model.boardgame.Piece;
import model.chess.move.MoveCalculus;
import model.chess.util.ChessPieceType;

public abstract class ChessPiece extends Piece {
	
	private ChessColor color;
	private ChessPieceType type;
	protected MoveCalculus calculus;

	public ChessPiece(ChessColor color, ChessPieceType type, MoveCalculus calculus) {
		this.color = color;
		this.type = type;
		this.calculus = calculus;
	}
	
	public ChessColor getColor() {
		return color;
	}
	
	public void setColor(ChessColor color) {
		this.color = color;
	}
	
	public ChessPieceType getType() {
		return type;
	}

	public ChessColor getOppositeColor() {
		return (ChessColor.WHITE == color) ? ChessColor.BLACK : ChessColor.WHITE;
	}
	
	protected abstract boolean[][] legalMoves();

	public abstract List<ChessPiece> targets();

	public boolean isLegalMove(int row, int column) {
		return legalMoves()[row][column];
	}

}
