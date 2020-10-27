package model.chess.move;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import model.boardgame.Board;
import model.boardgame.Piece;
import model.boardgame.Position;
import model.chess.ChessColor;
import model.chess.ChessException;
import model.chess.ChessPiece;
import model.chess.pieces.King;
import model.chess.pieces.Pawn;

public class MoveCalculus {

	public Set<ChessPiece> pieces;
	private Board board;

	public MoveCalculus(Set<ChessPiece> pieces, Board board) {
		this.pieces = pieces;
		this.board = board;
	}

	public boolean willCheck(Position source, Position target) {
		
		boolean enPassant = board.getPiece(source) instanceof Pawn && board.getPiece(target) == null
				&& source.getColumn() != target.getColumn();

		Piece moved = board.removePiece(source);
		Piece captured = removePiece(target);
		
		// In an en passant capture move, the CAPTURED piece POSITION is different from the capturing piece TARGET POSITION
		
		if (enPassant) {
			captured = removePiece(getCapturedEnPassantPosition(moved, target));
		}

		board.putPiece(moved, target);

		boolean check = isChecked(((ChessPiece) moved).getColor());

		board.putPiece(board.removePiece(target), source);
		
		if (enPassant) {
			reputPiece(captured, getCapturedEnPassantPosition(moved, target));
		} else {
			reputPiece(captured, target);
		}

		return check;
	}
	
	public Position getCapturedEnPassantPosition(Piece moved, Position target) {
		return new Position(((((ChessPiece)moved).getColor() == ChessColor.WHITE) ? 3 : 4) , target.getColumn());
	}

	private void reputPiece(Piece captured, Position target) {
		if (captured != null) {
			board.putPiece(captured, new Position(target.getRow(), target.getColumn()));
			pieces.add((ChessPiece) captured);
		}
	}

	private Piece removePiece(Position target) {
		Piece captured = board.removePiece(target);
		if (captured != null)
			pieces.remove(captured);
		return captured;
	}

	public boolean isChecked(ChessColor color) {

		List<ChessPiece> list = pieces.stream().filter(x -> x.getOppositeColor() == color).collect(Collectors.toList());

		for (ChessPiece p : list) {
			if (p.targets().contains(king(color))) {
				return true;
			}
		}

		return false;
	}

	private King king(ChessColor color) {
		List<ChessPiece> list = pieces.stream().filter(x -> x.getColor() == color && x instanceof King)
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			throw new ChessException("There is no " + color.toString().toLowerCase() + " king on the board");
		}
		if (list.size() > 1) {
			throw new ChessException("There is multiple " + color.toString().toLowerCase() + " kings on the board");
		}
		return (King) list.get(0);
	}

}
