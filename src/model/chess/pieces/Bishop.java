package model.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import model.boardgame.Position;
import model.chess.ChessColor;
import model.chess.ChessPiece;
import model.chess.move.MoveCalculus;
import model.chess.util.ChessPieceType;

public class Bishop extends ChessPiece {

	public Bishop(ChessColor color, MoveCalculus calculus) {
		super(color, ChessPieceType.BISHOP, calculus);
	}

	@Override
	protected boolean[][] legalMoves() {
		boolean[][] moves = new boolean[8][8];

		// up-left

		Position next = new Position(position.getRow() - 1, position.getColumn() - 1);
		while (board.isPosition(next) && !board.isOccupied(next)) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
			next.setRow(next.getRow() - 1);
			next.setColumn(next.getColumn() - 1);
		} if (board.isPosition(next) && ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
		}

		// up-right

		next = new Position(position.getRow() - 1, position.getColumn() + 1);
		while (board.isPosition(next) && !board.isOccupied(next)) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
			next.setRow(next.getRow() - 1);
			next.setColumn(next.getColumn() + 1);
		} if (board.isPosition(next) && ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
		}

		// down-left

		next = new Position(position.getRow() + 1, position.getColumn() - 1);
		while (board.isPosition(next) && !board.isOccupied(next)) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
			next.setRow(next.getRow() + 1);
			next.setColumn(next.getColumn() - 1);
		} if (board.isPosition(next) && ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
		}

		// down-right

		next = new Position(position.getRow() + 1, position.getColumn() + 1);
		while (board.isPosition(next) && !board.isOccupied(next)) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
			next.setRow(next.getRow() + 1);
			next.setColumn(next.getColumn() + 1);
		} if (board.isPosition(next) && ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
		}

		return moves;
	}

	@Override
	public List<ChessPiece> targets() {
		List<ChessPiece> targets = new ArrayList<>();

		ChessPiece target = null;
		Position next = new Position(position.getRow() - 1, position.getColumn() - 1);
		while (target == null && board.isPosition(next)) {
			
			if (board.isPosition(next) && board.isOccupied(next) && target == null) {
				target = (ChessPiece) board.getPiece(next);
				targets.add(target);
			}
			
			next.setRow(next.getRow() - 1);
			next.setColumn(next.getColumn() - 1);
		}

		target = null;
		next = new Position(position.getRow() - 1, position.getColumn() + 1);
		while (target == null && board.isPosition(next)) {

			
			if (board.isPosition(next) && board.isOccupied(next) && target == null) {
				target = (ChessPiece) board.getPiece(next);
				targets.add(target);
			}
			
			next.setRow(next.getRow() - 1);
			next.setColumn(next.getColumn() + 1);
		}

		target = null;
		next = new Position(position.getRow() + 1, position.getColumn() - 1);
		while (target == null && board.isPosition(next)) {
		
			if (board.isPosition(next) && board.isOccupied(next) && target == null) {
				target = (ChessPiece) board.getPiece(next);
				targets.add(target);
			}
			
			next.setRow(next.getRow() + 1);
			next.setColumn(next.getColumn() - 1);
		}

		target = null;
		next = new Position(position.getRow() + 1, position.getColumn() + 1);
		while (target == null && board.isPosition(next)) {

			if (board.isPosition(next) && board.isOccupied(next) && target == null) {
				target = (ChessPiece) board.getPiece(next);
				targets.add(target);
			}
			next.setRow(next.getRow() + 1);
			next.setColumn(next.getColumn() + 1);
		}

		return targets;
	}

	@Override
	public String toString() {
		return (getColor() == ChessColor.WHITE) ? "B" : "b";
	}

}
