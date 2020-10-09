package model.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import model.boardgame.Position;
import model.chess.ChessColor;
import model.chess.ChessGame;
import model.chess.ChessPiece;
import model.chess.move.MoveCalculus;
import model.chess.util.ChessPieceType;

public class King extends ChessPiece {

	private ChessGame game;

	public King(ChessColor color, MoveCalculus calculus, ChessGame game) {
		super(color, ChessPieceType.KING, calculus);
		this.game = game;
	}

	@Override
	protected boolean[][] legalMoves() {

		boolean[][] moves = new boolean[8][8];

		Position next = new Position(position.getRow() - 1, position.getColumn() - 1);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next)) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			} else if (((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next.setColumn(position.getColumn());
		if (board.isPosition(next)) {
			if (!board.isOccupied(next)) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			} else if (((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next.setColumn(position.getColumn() + 1);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next)) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			} else if (((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next = new Position(position.getRow(), position.getColumn() - 1);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next)) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			} else if (((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next.setColumn(position.getColumn() + 1);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next)) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			} else if (((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next = new Position(position.getRow() + 1, position.getColumn() - 1);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next)) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			} else if (((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next.setColumn(position.getColumn());
		if (board.isPosition(next)) {
			if (!board.isOccupied(next)) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			} else if (((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		next.setColumn(position.getColumn() + 1);
		if (board.isPosition(next)) {
			if (!board.isOccupied(next)) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			} else if (((ChessPiece) board.getPiece(next)).getColor() != getColor()) {
				if (!calculus.willCheck(position, next)) {
					moves[next.getRow()][next.getColumn()] = true;
				}
			}
		}

		int i = (getColor() == ChessColor.WHITE) ? 0 : 2;

		// kingside castle
		
		if (!calculus.isChecked(getColor())) {
			if (game.canCastle(0 + i)) {
				Position passing = new Position(position.getRow(), position.getColumn() + 1);
				Position castleTarget = new Position(position.getRow(), position.getColumn() + 2);

				if (!board.isOccupied(passing) && !board.isOccupied(castleTarget)) {
					if (!calculus.willCheck(position, passing)
							&& !calculus.willCheck(position, castleTarget)) {
						moves[castleTarget.getRow()][castleTarget.getColumn()] = true;
					}
				}
			}
		}
		
		// queenside castle
		
		if (!calculus.isChecked(getColor())) {
			if (game.canCastle(1 + i)) {
				Position passing = new Position(position.getRow(), position.getColumn() - 1);
				Position passingRook = new Position(position.getRow(), position.getColumn() - 3);
				Position castleTarget = new Position(position.getRow(), position.getColumn() - 2);

				if (!board.isOccupied(passing) && !board.isOccupied(passingRook) && !board.isOccupied(castleTarget)) {
					if (!calculus.willCheck(position, passing)
							&& !calculus.willCheck(position, castleTarget)) {
						moves[castleTarget.getRow()][castleTarget.getColumn()] = true;
					}
				}
			}
		}
		return moves;
	}

	@Override
	public List<ChessPiece> targets() {
		List<ChessPiece> targets = new ArrayList<>();

		Position next = new Position(position.getRow() - 1, position.getColumn() - 1);
		if (board.isPosition(next) && board.isOccupied(next)) {
			targets.add((ChessPiece) board.getPiece(next));
		}

		next.setColumn(position.getColumn());
		if (board.isPosition(next) && board.isOccupied(next)) {
			targets.add((ChessPiece) board.getPiece(next));
		}

		next.setColumn(position.getColumn() + 1);
		if (board.isPosition(next) && board.isOccupied(next)) {
			targets.add((ChessPiece) board.getPiece(next));
		}

		next = new Position(position.getRow(), position.getColumn() - 1);
		if (board.isPosition(next) && board.isOccupied(next)) {
			targets.add((ChessPiece) board.getPiece(next));
		}

		next.setColumn(position.getColumn() + 1);
		if (board.isPosition(next) && board.isOccupied(next)) {
			targets.add((ChessPiece) board.getPiece(next));
		}

		next = new Position(position.getRow() + 1, position.getColumn() - 1);
		if (board.isPosition(next) && board.isOccupied(next)) {
			targets.add((ChessPiece) board.getPiece(next));
		}

		next.setColumn(position.getColumn());
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
		return (getColor() == ChessColor.WHITE) ? "K" : "k";
	}
}
