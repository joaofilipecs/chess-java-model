package model.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import model.boardgame.Position;
import model.chess.ChessColor;
import model.chess.ChessPiece;
import model.chess.move.MoveCalculus;
import model.chess.util.ChessPieceType;

public class Rook extends ChessPiece {

	private Integer side;
	private boolean moved;

	public Rook(ChessColor color, MoveCalculus calculus, Integer side) {
		super(color, ChessPieceType.ROOK, calculus);
		this.side = side;
	}

	public Integer getSide() {
		return side;
	}

	public boolean hasMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	@Override
	protected boolean[][] legalMoves() {
		boolean[][] moves = new boolean[8][8];

		// up

		Position next = new Position(position.getRow() - 1, position.getColumn());
		while (board.isPosition(next)
				&& (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor())) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
			next.setRow(next.getRow() - 1);
		}

		// left

		next = new Position(position.getRow(), position.getColumn() - 1);
		while (board.isPosition(next)
				&& (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor())) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
			next.setColumn(next.getColumn() - 1);
		}

		// right

		next = new Position(position.getRow(), position.getColumn() + 1);
		while (board.isPosition(next)
				&& (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor())) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
			next.setColumn(next.getColumn() + 1);
		}

		// down

		next = new Position(position.getRow() + 1, position.getColumn());
		while (board.isPosition(next)
				&& (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor())) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
			next.setRow(next.getRow() + 1);
		}

		return moves;
	}

	@Override
	public List<ChessPiece> targets() {

		List<ChessPiece> targets = new ArrayList<>();

		// up
		ChessPiece target = null;
		Position next = new Position(position.getRow() - 1, position.getColumn());
		while (target == null && board.isPosition(next)) {

			if (board.isOccupied(next)) {
				target = (ChessPiece) board.getPiece(next);
				targets.add(target);
			}

			next.setRow(next.getRow() - 1);
		}

		// left
		target = null;
		next = new Position(position.getRow(), position.getColumn() - 1);
		while (target == null && board.isPosition(next)) {

			if (board.isOccupied(next)) {
				target = (ChessPiece) board.getPiece(next);
				targets.add(target);
			}

			next.setColumn(next.getColumn() - 1);
		}

		// right
		target = null;
		next = new Position(position.getRow(), position.getColumn() + 1);
		while (target == null && board.isPosition(next)) {

			if (board.isOccupied(next)) {
				target = (ChessPiece) board.getPiece(next);
				targets.add(target);
			}
			next.setColumn(next.getColumn() + 1);

		}

		// down
		target = null;
		next = new Position(position.getRow() + 1, position.getColumn());
		while (target == null && board.isPosition(next)) {

			if (board.isOccupied(next)) {
				target = (ChessPiece) board.getPiece(next);
				targets.add(target);
			}
			next.setRow(next.getRow() + 1);
		}

		return targets;
	}

	@Override
	public String toString() {
		return (getColor() == ChessColor.WHITE) ? "R" : "r";
	}

}
