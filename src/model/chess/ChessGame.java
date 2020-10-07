package model.chess;

import java.util.ArrayList;
import java.util.List;

import model.boardgame.Board;
import model.boardgame.Piece;
import model.boardgame.Position;
import model.chess.move.MoveCalculus;
import model.chess.move.MoveException;
import model.chess.move.MoveType;
import model.chess.pieces.King;
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
	
	private MoveCalculus calculus;
	
	public ChessGame() {
		board = new Board(8, 8);
		turn = ChessColor.WHITE;
		castle = new boolean[4];
		halfMoves = 0;
		fullMoves = 1;
		promotionSelection = ChessPieceType.QUEEN;
		calculus = new MoveCalculus(piecesOnBoard);
		initialPosition();
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
	
	public void putChessPiece(ChessPiece piece, Square square) {
		board.putPiece(piece, square.toPosition());
		piecesOnBoard.add(piece);
	}
	
	public ChessPiece removePiece(Position position) {
		ChessPiece removed = (ChessPiece)board.getPiece(position);
		if (removed != null) {
			board.removePiece(position);
			piecesOnBoard.remove(removed);
			capturedPieces.add(removed);
		}
		return removed;
	}
	
	public void initialPosition() {
		cleanPosition();
		putChessPiece(new King(ChessColor.WHITE, calculus, this), new Square('e', 1));
		putChessPiece(new King(ChessColor.BLACK, calculus, this), new Square('e', 3));
	}
	
	public void cleanPosition() {
		
	}
	
	public MoveType makeMove(Position source, Position target) {
		if (board.getPiece(source) == null) {
			throw new MoveException("There is no source piece");
		}
		
		MoveType type = MoveType.DEFAULT;
		ChessPiece moved = (ChessPiece)board.getPiece(source);
		
		if (moved.isLegalMove(target.getRow(), target.getColumn())) {
			if (getMoveType(source, target) == MoveType.DEFAULT) {
				board.removePiece(source);
				removePiece(target);
				board.putPiece(moved, target);
			}
		} else {
			throw new MoveException("That's not a legal move");
		}
		return type;
	}
	
	public MoveType getMoveType(Position source, Position target) {
		return MoveType.DEFAULT;
	}
	

	public void printBoardDiagram() {
		System.out.println(getBoardDiagram());
	}
	
	public void printLegalMovesDiagram(Position position) {
		System.out.println(getLegalMovesDiagram(position));
	}
	
	private String getBoardDiagram() {
		String diagram = "";

		for (int i = 0; i < 8; i++) {
			diagram += "\n +---+---+---+---+---+---+---+---+ \n ";
			for (int j = 0; j < 8; j++) {
				String piece = (((ChessPiece) board.getPiece(i, j)) != null)
						? ((ChessPiece) board.getPiece(i, j)).toString()
						: " ";
				diagram += "| " + piece + " ";
			}
			diagram += "| ";
		}
		diagram += "\n +---+---+---+---+---+---+---+---+ \n\n";
		diagram += " " + capturedPieces.toString();

		return diagram;
	}

	private String getLegalMovesDiagram(Position position) {
		ChessPiece piece = (ChessPiece) board.getPiece(position);

		if (piece != null) {
			String diagram = "";

			for (int i = 0; i < 8; i++) {
				diagram += "\n +---+---+---+---+---+---+---+---+ \n ";
				for (int j = 0; j < 8; j++) {

					boolean arg = ((ChessPiece) board.getPiece(i, j)) == piece;

					String pieceStr = (arg) ? piece.toString() : ((piece.isLegalMove(i, j)) ? "X" : " ");
					diagram += "| " + pieceStr + " ";
				}
				diagram += "| ";
			}
			diagram += "\n +---+---+---+---+---+---+---+---+ \n\n";

			return diagram;
		} else {
			throw new ChessException("There's no piece on given position");
		}
	}
}
