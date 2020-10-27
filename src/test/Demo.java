package test;

import model.boardgame.Position;
import model.chess.ChessGame;

public class Demo {
	
	public static void main(String[] args) {
		ChessGame game = new ChessGame();
		
		System.out.println("Game #1: two square move");
		game.printBoardDiagram();
		game.printLegalMovesDiagram(new Position(6, 4));
		game.makeMove(new Position(6, 4), new Position(4, 4), false);
		game.printBoardDiagram();
		game.makeMove(new Position(1, 3), new Position(3, 3), false);
		game.printBoardDiagram();
		game.printLegalMovesDiagram(new Position(4, 4));
		game.printLegalMovesDiagram(new Position(6, 0));
		
		ChessGame game2 = new ChessGame();
		System.out.println("Game #2: en Passant");
		
		game2.printBoardDiagram();
		game2.printLegalMovesDiagram(new Position(1, 6));
		game2.makeMove(new Position(7, 0), new Position(7, 1), false);
		game2.makeMove(new Position(1, 6), new Position(3, 6), false);
		game2.printBoardDiagram();
		game2.printLegalMovesDiagram(new Position(3, 7));
		game2.makeMove(new Position(3, 7), new Position(2, 6), false);
		game2.printBoardDiagram();
		
		ChessGame game3 = new ChessGame();
		
		System.out.println("Game #3: promotion");
		game3.printBoardDiagram();
		game3.makeMove(new Position(1, 1), new Position(0, 1), false);
		game3.printBoardDiagram();
		
	}

}
