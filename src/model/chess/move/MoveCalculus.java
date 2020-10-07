package model.chess.move;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.boardgame.Board;
import model.boardgame.Piece;
import model.boardgame.Position;
import model.chess.ChessColor;
import model.chess.ChessException;
import model.chess.ChessPiece;
import model.chess.pieces.King;

public class MoveCalculus {
	
	private List<ChessPiece> pieces = new ArrayList<>();
	
	public MoveCalculus(List<ChessPiece> pieces) {
		this.pieces = pieces;
	}

	public boolean willCheck(Board board, Position source, Position target) {

		Piece moved = board.removePiece(source);
		Piece captured = board.removePiece(target);
		
		board.putPiece(moved, target);

		boolean check = isChecked(board, ((ChessPiece) moved).getColor());
		
		board.putPiece(board.removePiece(target), source);
		
		if (captured != null)board.putPiece(captured, target);

		return check;
	}

	public boolean isChecked(Board board, ChessColor color) {
		
		List<ChessPiece> list = pieces.stream().filter(x -> x.getOppositeColor() == color).collect(Collectors.toList());
		
		for (ChessPiece p : list) {
			if (p.targets().contains(king(color))) {
				return true;
			}
		}
		
		return false;
	}

	private King king(ChessColor color) {
		List<ChessPiece> list = pieces.stream().filter(x -> x.getColor() == color && x instanceof King).collect(Collectors.toList());
		if (list.isEmpty()) {
			throw new ChessException("There is no " + color.toString().toLowerCase() + " king on the board");
		}
		if (list.size() > 1) {
			throw new ChessException("There is multiple " + color.toString().toLowerCase() + " kings on the board");
		}
		return (King)list.get(0);		
	}

}
