package test;

import java.util.Scanner;

import model.chess.ChessGame;
import model.chess.Square;
import model.chess.move.MoveException;
import model.chess.util.FEN;

public class Demo {

	static ChessGame game = new ChessGame();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		FEN.loadFen(game, "8/4n3/4kN1P/8/1pp1N1P1/8/1PP2PK1/8 w - - 0 31");
		
		game.printBoardDiagram();
		System.out.println(FEN.getFen(game));
		
		while (!game.isCheckmated()) {
			try {
				System.out.print("Source: ");
				Square source = getPosition(sc.next());
				System.out.print("Target: ");
				Square target = getPosition(sc.next());
				makeMove(source, target, false);
			} catch (MoveException e) {
				System.out.println(e.getMessage());
			}
		}
		sc.close();

	}

	static void makeMove(Square source, Square target, boolean value) {
		game.printLegalMovesDiagram(source.toPosition());
		game.makeMove(source.toPosition(), target.toPosition(), value);
		game.printBoardDiagram();
		System.out.println(FEN.getFen(game));
		System.out.println();
	}

	static Square getPosition(String str) {
		return new Square(str.charAt(0), Integer.parseInt(str.substring(1)));
	}

}
