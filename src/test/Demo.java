package test;

import model.boardgame.Position;
import model.chess.ChessGame;
import model.chess.Square;

public class Demo {
	
	public static void main(String[] args) {
		ChessGame game1 = new ChessGame();
		System.out.println("Game #1: KNIGHT");
		
		game1.printBoardDiagram();
		makeMove(game1, new Square('b', 1), new Square('c', 3), false);
		makeMove(game1, new Square('b', 8), new Square('c', 6), false);
		makeMove(game1, new Square('c', 3), new Square('d', 5), true);
		makeMove(game1, new Square('d', 5), new Square('c', 7), false);
		
		ChessGame game2 = new ChessGame();
		
		System.out.println("Game #2: BISHOP");
		
		game2.printBoardDiagram();
		makeMove(game2, new Square('b', 2), new Square('b', 3), false);
		makeMove(game2, new Square('c', 1), new Square('b', 2), true);
		makeMove(game2, new Square('g', 7), new Square('g', 6), false);
		makeMove(game2, new Square('b', 2), new Square('h', 8), false);
		makeMove(game2, new Square('f', 8), new Square('g', 7), false);
		makeMove(game2, new Square('c', 2), new Square('c', 3), false);
		
		game2.printLegalMovesDiagram(new Position(1, 6));

		ChessGame game3 = new ChessGame();
		
		System.out.println("Game #3: QUEEN");
		
		game3.printBoardDiagram();
		makeMove(game3, new Square('e', 2), new Square('e', 4), false);
		makeMove(game3, new Square('d', 7), new Square('d', 5), false);
		makeMove(game3, new Square('e', 4), new Square('d', 5), false);
		makeMove(game3, new Square('d', 8), new Square('d', 5), false);
		makeMove(game3, new Square('b', 1), new Square('c', 3), false);
		makeMove(game3, new Square('d', 5), new Square('a', 5), false);
		
		
	}
	
	static void makeMove(ChessGame game, Square source, Square target, boolean value) {
		game.printLegalMovesDiagram(source.toPosition());
		game.makeMove(source.toPosition(), target.toPosition(), value);
		game.printBoardDiagram();
	}

}
