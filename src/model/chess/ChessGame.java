package model.chess;

import java.util.ArrayList;
import java.util.List;

import model.boardgame.Board;
import model.boardgame.Position;
import model.chess.util.ChessPieceType;

public class ChessGame {
	
	private Board board;
	
	private ChessColor turn;
	private boolean[] castle;
	private Position enPassant;
	private int halfMoves;
	private int fullMoves;
	
	private List<ChessPiece> piecesOnBoard = new ArrayList<>();
	private List<ChessPiece> capturedPieces = new ArrayList<>();
	private ChessPieceType promotionSelection;
	
	public ChessGame() {
		board = new Board(8, 8);
		turn = ChessColor.WHITE;
		castle = new boolean[4];
		halfMoves = 0;
		fullMoves = 1;
		promotionSelection = ChessPieceType.QUEEN;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public ChessColor getTurn() {
		return turn;
	}
	
	public boolean canCastle(int side) {
		return castle[side];
	}
	
	public void setCastle(int side, boolean value) {
		castle[side] = value;
	}
	
	public Position getEnPassant() {
		return enPassant;
	}
	
	public int getHalfMoves() {
		return halfMoves;
	}
	
	public int getFullMoves() {
		return fullMoves;
	}

	public List<ChessPiece> getPiecesOnBoard() {
		return piecesOnBoard;
	}

	public List<ChessPiece> getCapturedPieces() {
		return capturedPieces;
	}
	
	public ChessPieceType getPromotionSelection() {
		return promotionSelection;
	}

	public void setPromotionSelection(ChessPieceType promotionSelection) {
		this.promotionSelection = promotionSelection;
	}
}
