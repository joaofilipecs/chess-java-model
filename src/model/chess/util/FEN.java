package model.chess.util;

import model.boardgame.Position;
import model.chess.ChessColor;
import model.chess.ChessGame;
import model.chess.ChessPiece;
import model.chess.Square;
import model.chess.pieces.Bishop;
import model.chess.pieces.King;
import model.chess.pieces.Knight;
import model.chess.pieces.Pawn;
import model.chess.pieces.Queen;
import model.chess.pieces.Rook;

public class FEN {

	public static String getFen(ChessGame game) {
		String str = "";

		for (int i = 0; i < 8; i++) {
			int empty = 0;
			for (int j = 0; j < 8; j++) {
				if (game.getBoard().getPiece(i, j) != null) {
					if (empty > 0) {
						str += "" + empty;
						empty = 0;
					}
					str += ((ChessPiece) game.getBoard().getPiece(i, j)).toString();
				} else {
					empty++;
					if (j == 7) {
						str += "" + empty;
					}
				}
			}
			if (i < 7)
				str += "/";
		}
		str += " ";

		str += (game.getTurn() == ChessColor.WHITE) ? "w" : "b";
		str += " ";

		boolean anyCastle = false;
		if (game.canCastle(0)) {
			str += "K";
			anyCastle = true;
		}
		if (game.canCastle(1)) {
			str += "Q";
			anyCastle = true;
		}
		if (game.canCastle(2)) {
			str += "k";
			anyCastle = true;
		}
		if (game.canCastle(3)) {
			str += "q";
			anyCastle = true;
		}
		if (anyCastle == false)
			str += "-";
		str += " ";

		if (game.getEnPassant() != null) {
			str += Square.toSquare(game.getEnPassant());
		} else {
			str += "-";
		}
		str += " ";

		str += "" + game.getHalfMoves();
		str += " ";

		str += "" + game.getFullMoves();

		return str;
	}

	public static void loadFen(ChessGame game, String str) {
		// demo: r3k3/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/4K2R w KQkq e6 0 2

		game.cleanPosition();
		
		String[] fields = str.split(" ");

		String[] board = fields[0].split("/");

		ChessColor turn = (fields[1].contains("w")) ? ChessColor.WHITE : ChessColor.BLACK;

		String castle = fields[2];

		String enStr = fields[3];
		Position enPassant = (enStr != "-")
				? new Square(enStr.charAt(0), Integer.parseInt(enStr.substring(1))).toPosition()
				: null;

		int halfMoves = Integer.parseInt(fields[4]);
		int fullMoves = Integer.parseInt(fields[5]);

		for (int i = 0; i < 8; i++) {
			for (int j = 0, l = 0; j < 8; j++, l++) {
				String chr = String.valueOf(board[i].charAt(l));

				if (chr.matches("\\d+")) {
					j += Integer.parseInt(chr) - 1;
				} else {
					game.putChessPiece(toPiece(chr, castle, j, game), Square.toSquare(new Position(i, j)));
				}

			}
		}
		
		game.setTurn(turn);
		
		game.setCastle(0, castle.contains("K"));
		game.setCastle(1, castle.contains("Q"));
		game.setCastle(2, castle.contains("k"));
		game.setCastle(3, castle.contains("q"));
		
		game.setEnPassant(enPassant);
		
		game.setHalfMoves(halfMoves);
		game.setFullMoves(fullMoves);
		
	}

	private static ChessPiece toPiece(String piece, String castle, int column, ChessGame game) {
		ChessColor color = (Character.isUpperCase(piece.charAt(0))) ? ChessColor.WHITE : ChessColor.BLACK;

		switch (piece.toUpperCase()) {
		case "K":
			return new King(color, game.getCalculus(), game);
		case "Q":
			return new Queen(color, game.getCalculus());
		case "R":

			Integer side = null;

			if (castle != "-") {
				if (color == ChessColor.WHITE) {
					if (castle.contains("K") && column == 7) {
						side = 0;
					} else if (castle.contains("Q") && column == 0) {
						side = 1;
					}
				} else {
					if (castle.contains("k") && column == 7) {
						side = 2;
					} else if (castle.contains("q") && column == 0) {
						side = 3;
					}
				}
			}

			return new Rook(color, game.getCalculus(), side);
		case "N":
			return new Knight(color, game.getCalculus());
		case "B":
			return new Bishop(color, game.getCalculus());
		case "P":
			return new Pawn(color, game.getCalculus(), game);
		default:
			return null;
		}

	}

}
