package model.chess;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import model.boardgame.Board;
import model.boardgame.Piece;
import model.boardgame.Position;
import model.chess.move.MoveCalculus;
import model.chess.move.MoveException;
import model.chess.move.MoveType;
import model.chess.pieces.Bishop;
import model.chess.pieces.King;
import model.chess.pieces.Knight;
import model.chess.pieces.Pawn;
import model.chess.pieces.Queen;
import model.chess.pieces.Rook;
import model.chess.util.ChessPieceType;

public class ChessGame {

	private Board board;

	private ChessColor turn;
	private boolean[] castle;
	private Position enPassant;
	private int halfMoves;
	private int fullMoves;

	private Set<ChessPiece> piecesOnBoard = new HashSet<>();
	private Set<ChessPiece> capturedPieces = new HashSet<>();
	private ChessPieceType promotionSelection;

	private MoveCalculus calculus;

	public ChessGame() {
		board = new Board(8, 8);
		turn = ChessColor.WHITE;
		castle = new boolean[4];
		halfMoves = 0;
		fullMoves = 1;
		promotionSelection = ChessPieceType.QUEEN;
		calculus = new MoveCalculus(piecesOnBoard, board);
		initialPosition();
	}

	public Board getBoard() {
		return board;
	}

	public ChessColor getTurn() {
		return turn;
	}

	public void setTurn(ChessColor turn) {
		this.turn = turn;
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

	public void setEnPassant(Position enPassant) {
		this.enPassant = enPassant;
	}

	public int getHalfMoves() {
		return halfMoves;
	}

	public void setHalfMoves(int halfMoves) {
		this.halfMoves = halfMoves;
	}

	public int getFullMoves() {
		return fullMoves;
	}
	
	public void setFullMoves(int fullMoves) {
		this.fullMoves = fullMoves;
	}

	public Set<ChessPiece> getPiecesOnBoard() {
		return piecesOnBoard;
	}

	public Set<ChessPiece> getCapturedPieces() {
		return capturedPieces;
	}

	public ChessPieceType getPromotionSelection() {
		return promotionSelection;
	}

	public void setPromotionSelection(ChessPieceType promotionSelection) {
		this.promotionSelection = promotionSelection;
	}

	public MoveCalculus getCalculus() {
		return calculus;
	}

	public void putChessPiece(ChessPiece piece, Square square) {
		board.putPiece(piece, square.toPosition());
		piecesOnBoard.add(piece);
	}

	public ChessPiece removePiece(Position position) {
		ChessPiece removed = (ChessPiece) board.removePiece(position);
		if (removed != null) {
			piecesOnBoard.remove(removed);
			capturedPieces.add(removed);
		}
		return removed;
	}

	public void initialPosition() {
		cleanPosition();
		putChessPiece(new King(ChessColor.WHITE, calculus, this), new Square('e', 1));
		putChessPiece(new Rook(ChessColor.WHITE, calculus, 0), new Square('h', 1));
		putChessPiece(new Rook(ChessColor.WHITE, calculus, 1), new Square('a', 1));
		putChessPiece(new Pawn(ChessColor.WHITE, calculus, this), new Square('a', 2));
		putChessPiece(new Pawn(ChessColor.WHITE, calculus, this), new Square('b', 2));
		putChessPiece(new Pawn(ChessColor.WHITE, calculus, this), new Square('c', 2));
		putChessPiece(new Pawn(ChessColor.WHITE, calculus, this), new Square('d', 2));
		putChessPiece(new Pawn(ChessColor.WHITE, calculus, this), new Square('e', 2));
		putChessPiece(new Pawn(ChessColor.WHITE, calculus, this), new Square('f', 2));
		putChessPiece(new Pawn(ChessColor.WHITE, calculus, this), new Square('g', 2));
		putChessPiece(new Pawn(ChessColor.WHITE, calculus, this), new Square('h', 2));
		putChessPiece(new Knight(ChessColor.WHITE, calculus), new Square('b', 1));
		putChessPiece(new Knight(ChessColor.WHITE, calculus), new Square('g', 1));
		putChessPiece(new Bishop(ChessColor.WHITE, calculus), new Square('c', 1));
		putChessPiece(new Bishop(ChessColor.WHITE, calculus), new Square('f', 1));
		putChessPiece(new Queen(ChessColor.WHITE, calculus), new Square('d', 1));

		putChessPiece(new King(ChessColor.BLACK, calculus, this), new Square('e', 8));
		putChessPiece(new Rook(ChessColor.BLACK, calculus, 2), new Square('h', 8));
		putChessPiece(new Rook(ChessColor.BLACK, calculus, 3), new Square('a', 8));
		putChessPiece(new Pawn(ChessColor.BLACK, calculus, this), new Square('a', 7));
		putChessPiece(new Pawn(ChessColor.BLACK, calculus, this), new Square('b', 7));
		putChessPiece(new Pawn(ChessColor.BLACK, calculus, this), new Square('c', 7));
		putChessPiece(new Pawn(ChessColor.BLACK, calculus, this), new Square('d', 7));
		putChessPiece(new Pawn(ChessColor.BLACK, calculus, this), new Square('e', 7));
		putChessPiece(new Pawn(ChessColor.BLACK, calculus, this), new Square('f', 7));
		putChessPiece(new Pawn(ChessColor.BLACK, calculus, this), new Square('g', 7));
		putChessPiece(new Pawn(ChessColor.BLACK, calculus, this), new Square('h', 7));
		putChessPiece(new Knight(ChessColor.BLACK, calculus), new Square('b', 8));
		putChessPiece(new Knight(ChessColor.BLACK, calculus), new Square('g', 8));
		putChessPiece(new Bishop(ChessColor.BLACK, calculus), new Square('c', 8));
		putChessPiece(new Bishop(ChessColor.BLACK, calculus), new Square('f', 8));
		putChessPiece(new Queen(ChessColor.BLACK, calculus), new Square('d', 8));

		initialCastle();
	}

	private void initialCastle() {
		if (board.getPiece(7, 7) != null && board.getPiece(7, 7) instanceof Rook
				&& ((Rook) board.getPiece(7, 7)).getSide() == 0 && !((Rook) board.getPiece(7, 7)).hasMoved()) {
			castle[0] = true;
		}
		if (board.getPiece(7, 0) != null && board.getPiece(7, 0) instanceof Rook
				&& ((Rook) board.getPiece(7, 0)).getSide() == 1 && !((Rook) board.getPiece(7, 0)).hasMoved()) {
			castle[1] = true;
		}
		if (board.getPiece(0, 7) != null && board.getPiece(0, 7) instanceof Rook
				&& ((Rook) board.getPiece(0, 7)).getSide() == 2 && !((Rook) board.getPiece(0, 7)).hasMoved()) {
			castle[2] = true;
		}
		if (board.getPiece(0, 0) != null && board.getPiece(0, 0) instanceof Rook
				&& ((Rook) board.getPiece(0, 0)).getSide() == 3 && !((Rook) board.getPiece(0, 0)).hasMoved()) {
			castle[3] = true;
		}
	}

	public void cleanPosition() {
		for (int i =0; i<8;i++) {
			for (int j =0; j<8;j++) {
				board.removePiece(new Position(i, j));
			}
		}
		
		turn = ChessColor.WHITE;
		
		castle = new boolean[4];
		
		enPassant = null;
		
		halfMoves = 0;
		
		fullMoves = 1;
		
		piecesOnBoard.clear();
		capturedPieces.clear();
		
		promotionSelection = ChessPieceType.QUEEN;

	}

	public MoveType makeMove(Position source, Position target, boolean test) {
		if (board.getPiece(source) == null) {
			throw new MoveException("There is no source piece");
		}

		MoveType type = MoveType.DEFAULT;
		ChessPiece moved = (ChessPiece) board.getPiece(source);
		ChessPiece captured = (ChessPiece) board.getPiece(target);

		if (moved.isLegalMove(target.getRow(), target.getColumn())) {
			if (moved.getColor() == turn || test) {

				// move settings

				if (getMoveType(source, target) == MoveType.DEFAULT) {
					board.removePiece(source);
					removePiece(target);
					board.putPiece(moved, target);

				} else if (getMoveType(source, target) == MoveType.KINGSIDE_CASTLE) {
					board.putPiece(board.removePiece(source), target);
					Piece rook = board.removePiece(new Position(source.getRow(), 7));
					board.putPiece(rook, new Position(source.getRow(), 5));
					type = MoveType.KINGSIDE_CASTLE;

				} else if (getMoveType(source, target) == MoveType.QUEENSIDE_CASTLE) {
					board.putPiece(board.removePiece(source), target);
					Piece rook = board.removePiece(new Position(source.getRow(), 0));
					board.putPiece(rook, new Position(source.getRow(), 3));
					type = MoveType.QUEENSIDE_CASTLE;
				} else if (getMoveType(source, target) == MoveType.EN_PASSANT) {
					board.removePiece(source);
					removePiece(calculus.getCapturedEnPassantPosition(moved, target));
					board.putPiece(moved, target);
				} else if (getMoveType(source, target) == MoveType.PROMOTION) {

					board.removePiece(source);
					removePiece(target);
					board.putPiece(moved, target);

					removePiece(target);
					choosePromotion();
					if (promotionSelection == ChessPieceType.QUEEN) {
						board.putPiece(new Queen(moved.getColor(), calculus), target);
					} else if (promotionSelection == ChessPieceType.ROOK) {
						board.putPiece(new Rook(moved.getColor(), calculus, null), target);
					} else if (promotionSelection == ChessPieceType.BISHOP) {
						board.putPiece(new Bishop(moved.getColor(), calculus), target);
					} else if (promotionSelection == ChessPieceType.KNIGHT) {
						board.putPiece(new Knight(moved.getColor(), calculus), target);
					}
				}

				// castle settings

				if (moved instanceof Rook)
					((Rook) moved).setMoved(true);
				if (moved instanceof King) {
					int castleDiff = ((moved.getColor() == ChessColor.WHITE) ? 0 : 2);

					castle[castleDiff + 0] = false;
					castle[castleDiff + 1] = false;
				}

				// en passant settings

				enPassant = null;
				if (moved instanceof Pawn && Math.abs(source.getRow() - target.getRow()) == 2) {
					enPassant = new Position(((moved.getColor() == ChessColor.WHITE) ? 5 : 2),
							moved.getPosition().getColumn());
				}

				// game settings

				if (!test) {
					if (turn == ChessColor.BLACK) {
						fullMoves++;
						turn = ChessColor.WHITE;
					} else {
						turn = ChessColor.BLACK;
					}

					if (captured != null || moved instanceof Pawn) {
						halfMoves = 0;
					} else {
						halfMoves++;
					}
				}
			} else {
				throw new MoveException("That is not " + moved.getColor().toString().toLowerCase() + "'s turn");
			}
		} else {
			throw new MoveException("That's not a legal move");
		}

		return type;
	}

	private void choosePromotion() {
	}

	public MoveType getMoveType(Position source, Position target) {
		ChessPiece moving = (ChessPiece) board.getPiece(source);
		ChessPiece capture = (ChessPiece) board.getPiece(target);

		MoveType type = MoveType.DEFAULT;

		if (moving instanceof King) {
			if (Math.abs(source.getColumn() - target.getColumn()) == 2) {
				if (target.getColumn() == 6) {
					type = MoveType.KINGSIDE_CASTLE;
				} else if (target.getColumn() == 2) {
					type = MoveType.QUEENSIDE_CASTLE;
				}
			}
		}

		if (moving instanceof Pawn) {
			int end = (moving.getColor() == ChessColor.WHITE) ? 0 : 7;

			if (source.getColumn() != target.getColumn() && capture == null) {
				type = MoveType.EN_PASSANT;
			} else if (target.getRow() == end) {
				type = MoveType.PROMOTION;
			}
		}

		return type;
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

					String pieceStr = (arg) ? piece.toString() : ((piece.isLegalMove(i, j)) ? "*" : " ");
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
	
	public boolean isCheckmated() {
		Set<ChessPiece> list = piecesOnBoard.stream().filter(x -> x.getColor() == turn).collect(Collectors.toSet());
		
		for (ChessPiece p : list) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (p.isLegalMove(i, j)) {
						return false;
					}
				}
					
			}
		}
		return true;
	}
}
