package model.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import model.boardgame.Position;
import model.chess.ChessColor;
import model.chess.ChessPiece;
import model.chess.move.MoveCalculus;
import model.chess.util.ChessPieceType;

public class Knight extends ChessPiece {

	public Knight(ChessColor color, MoveCalculus calculus) {
		super(color, ChessPieceType.KNIGHT, calculus);
	}

	@Override
	protected boolean[][] legalMoves() {
		boolean[][] moves = new boolean[8][8];

		Position next = new Position(position.getRow() - 2, position.getColumn() - 1);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next.setColumn(position.getColumn() + 1);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next = new Position(position.getRow() - 1, position.getColumn() - 2);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next.setColumn(position.getColumn() + 2);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next = new Position(position.getRow() + 1, position.getColumn() - 2);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next.setColumn(position.getColumn() + 2);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next = new Position(position.getRow() + 2, position.getColumn() - 1);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next.setColumn(position.getColumn() + 1);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next) || ((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}
		return moves;
	}

	@Override
	public List<ChessPiece> targets() {
		List<ChessPiece> targets = new ArrayList<>();

		ChessPiece target = null;
		Position next = new Position(position.getRow() - 2, position.getColumn() - 1);
		if (board.isPosition(next) && board.isOccupied(next)) {
			target = (ChessPiece) board.getPiece(next);
			targets.add(target);
		}

		next.setColumn(position.getColumn() + 1);
		if (board.isPosition(next) && board.isOccupied(next)) {
			target = (ChessPiece) board.getPiece(next);
			targets.add(target);
		}

		next = new Position(position.getRow() - 1, position.getColumn() - 2);
		if (board.isPosition(next) && board.isOccupied(next)) {
			target = (ChessPiece) board.getPiece(next);
			targets.add(target);
		}

		next.setColumn(position.getColumn() + 2);
		if (board.isPosition(next) && board.isOccupied(next)) {
			target = (ChessPiece) board.getPiece(next);
			targets.add(target);
		}

		next = new Position(position.getRow() + 1, position.getColumn() - 2);
		if (board.isPosition(next) && board.isOccupied(next)) {
			target = (ChessPiece) board.getPiece(next);
			targets.add(target);
		}


		next.setColumn(position.getColumn() + 2);
		if (board.isPosition(next) && board.isOccupied(next)) {
			target = (ChessPiece) board.getPiece(next);
			targets.add(target);
		}
		
		next = new Position(position.getRow() + 2, position.getColumn() -1);
		if (board.isPosition(next) && board.isOccupied(next)) {
			target = (ChessPiece) board.getPiece(next);
			targets.add(target);
		}

		next.setColumn(position.getColumn() + 1);
		if (board.isPosition(next) && board.isOccupied(next)) {
			target = (ChessPiece) board.getPiece(next);
			targets.add(target);
		}

		return targets;
	}
	
	@Override
	public String toString() {
		return (getColor() == ChessColor.WHITE) ? "N" : "n";
	}

}
