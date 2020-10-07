package model.chess;

import model.boardgame.Position;

public class Square {

	private char file;
	private int rank;
	
	public Square() {
	}

	public Square(char file, int rank) {
		this.file = file;
		this.rank = rank;
	}

	public char getFile() {
		return file;
	}

	public void setFile(char file) {
		this.file = file;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public Position toPosition() {
		return new Position(8 - rank, file - 'a');
	}
	
	public static Square toSquare(Position position) {
		return new Square((char)(position.getColumn() + 'a'), 8 - position.getRow());
	}

	@Override
	public String toString() {
		return "" + file + rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + file;
		result = prime * result + rank;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (file != other.file)
			return false;
		if (rank != other.rank)
			return false;
		return true;
	}
}
