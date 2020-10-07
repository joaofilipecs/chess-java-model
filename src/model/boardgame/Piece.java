package model.boardgame;

public abstract class Piece {
	
	protected Position position;
	protected Board board;
	
	public Piece() {
	}

	public Position getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "Piece [position=" + position + "]";
	}
}
