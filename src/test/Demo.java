package test;

import model.boardgame.Position;
import model.chess.ChessGame;

public class Demo {
	
	public static void main(String[] args) {
		ChessGame game = new ChessGame();
		
		System.out.println("GAME #1");
		game.printBoardDiagram();
		game.printLegalMovesDiagram(new Position(0, 4));
		System.out.println("King can castle both sides");
		game.makeMove(new Position(0, 4), new Position(0, 6));
		game.printBoardDiagram();
		game.makeMove(new Position(7, 0), new Position(0, 0));
		game.printBoardDiagram();
		game.printLegalMovesDiagram(new Position(0, 5));
		System.out.println("The rook is pinned to the king");
		
		ChessGame game2 = new ChessGame();
		
		System.out.println("GAME #2");
		game2.printBoardDiagram();
		game2.makeMove(new Position(7, 0), new Position(6, 0));
		game2.printBoardDiagram();
		game2.makeMove(new Position(0, 0), new Position(1, 0));
		game2.printBoardDiagram();
		game2.makeMove(new Position(6, 0), new Position(6, 4));
		game2.printBoardDiagram();
		game2.printLegalMovesDiagram(new Position(1, 0));
		System.out.println("Black pieces are in check: only rook move is to cover the king from the check");
		game2.makeMove(new Position(1, 0), new Position(1, 4));
		game2.printBoardDiagram();
		game2.printLegalMovesDiagram(new Position(1, 4));
		System.out.println("The rook is pinned to the king");
		
		ChessGame game3 = new ChessGame();
		
		System.out.println("GAME #3");
		game3.printBoardDiagram();
		game3.makeMove(new Position(7, 0), new Position(0, 0));
		game3.printBoardDiagram();
		game3.printLegalMovesDiagram(new Position(0, 4));
		game3.printLegalMovesDiagram(new Position(0, 7));
		System.out.println("King is in check. Second rook can not move");
		game3.makeMove(new Position(0, 4), new Position(1, 5));
		game3.makeMove(new Position(0, 0), new Position(0, 7));
		game3.printBoardDiagram();
		game3.makeMove(new Position(1, 5), new Position(1, 6));
		game3.printBoardDiagram();
		game3.printLegalMovesDiagram(new Position(1, 6));
		System.out.println("King can not move to any of white rook legal moves");
		game3.makeMove(new Position(7, 7), new Position(7, 6));
		game3.printBoardDiagram();
		game3.printLegalMovesDiagram(new Position(1, 6));
		System.out.println("King is in check, but can capture undefended rook");
		game3.makeMove(new Position(1, 6), new Position(0, 7));
		game3.printBoardDiagram();
		
	}

}
