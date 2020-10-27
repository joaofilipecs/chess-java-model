package model.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import model.boardgame.Position;
import model.chess.ChessColor;
import model.chess.ChessGame;
import model.chess.ChessPiece;
import model.chess.move.MoveCalculus;
import model.chess.util.ChessPieceType;

public class Pawn extends ChessPiece {
	
	ChessGame game;
	
	public Pawn(ChessColor color,  MoveCalculus calculus, ChessGame game) {
		super(color, ChessPieceType.PAWN, calculus);
		this.game = game;
	}

	@Override
	protected boolean[][] legalMoves() {
		
		boolean[][] moves = new boolean[8][8];
		
		int n = (getColor() == ChessColor.WHITE) ? -1 : 1;
		int start = (getColor() == ChessColor.WHITE) ? 6 : 1;

		Position next = new Position(position.getRow() + 1 * n, position.getColumn());

		if (!board.isOccupied(next)) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
		}
		
		if (!board.isOccupied(next) && position.getRow() == start) {
			next = new Position(position.getRow() + 2 * n, position.getColumn());
			if (!board.isOccupied(next)) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next = new Position(position.getRow() + 1 * n, position.getColumn() - 1);
		if (board.isPosition(next) && board.isOccupied(next)
				&& ((ChessPiece) board.getPiece(next)).getOppositeColor() == getColor()) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
		}

		next = new Position(position.getRow() + 1 * n, position.getColumn() + 1);
		if (board.isPosition(next) && board.isOccupied(next)
				&& ((ChessPiece) board.getPiece(next)).getOppositeColor() == getColor()) {
			if (!calculus.willCheck(position, next)) {
				moves[next.getRow()][next.getColumn()] = true;
			}
		}

		Position enPassant = game.getEnPassant();

		if (enPassant != null) {
			if (enPassant.getRow() == 2) {
				if (getColor() == ChessColor.WHITE) {
					if (position.getRow() - enPassant.getRow() == 1
							&& Math.abs(position.getColumn() - enPassant.getColumn()) == 1) {
						if (!calculus.willCheck(position, enPassant)) {
							moves[enPassant.getRow()][enPassant.getColumn()] = true;
						}
					}
				}
			} else if (enPassant.getRow() == 5) {
				if (getColor() == ChessColor.BLACK) {
					if (enPassant.getRow() - position.getRow() == 1
							&& Math.abs(position.getColumn() - enPassant.getColumn()) == 1) {
						if (!calculus.willCheck(position, enPassant)) {
							moves[enPassant.getRow()][enPassant.getColumn()] = true;
						}
					}
				}
			}
		}

		return moves;
	}

	@Override
	public List<ChessPiece> targets() {
		
		List<ChessPiece> targets = new ArrayList<>();
		
		int n = (getColor() == ChessColor.WHITE) ? -1 : 1;
		
		Position next = new Position(position.getRow() + 1*n, position.getColumn() - 1);
		if (board.isPosition(next) && board.isOccupied(next)) {
			targets.add((ChessPiece) board.getPiece(next));
		}

		next.setColumn(position.getColumn() + 1);
		if (board.isPosition(next) && board.isOccupied(next)) {
			targets.add((ChessPiece) board.getPiece(next));
		}

		return targets;

	}
	
	@Override
	public String toString() {
		return (getColor() == ChessColor.WHITE) ? "P" : "p";
	}


}
