package test;

import model.boardgame.Position;
import model.chess.ChessGame;

public class Demo {
	
	public static void main(String[] args) {
		ChessGame game = new ChessGame();
		
		game.printBoardDiagram();
		game.printLegalMovesDiagram(new Position(7, 4));
		game.printLegalMovesDiagram(new Position(5, 4));
		
		game.makeMove(new Position(7, 4), new Position(7, 3));
		
		game.printBoardDiagram();
		game.printLegalMovesDiagram(new Position(7, 3));
		game.printLegalMovesDiagram(new Position(5, 4));
		
	}

}
