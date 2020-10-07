package model.boardgame;

public class Board {
	
	private int rows;
	private int columns;

	private Piece[][] pieces;

	public Board(int rows, int columns) {

		if (rows < 1 || columns < 1) {
			throw new BoardException("Rows and columns must be higher than zero");
		}

		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public boolean isPosition(Position position) {
		return position.getRow() >= 0 && position.getRow() < rows && position.getColumn() >= 0
				&& position.getColumn() < columns;
	}

	public boolean isOccupied(Position position) {
		return getPiece(position) != null;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece getPiece(Position position) {

		if (!isPosition(position)) {
			throw new BoardException("The " + position + " does not exist on this board");
		}

		return pieces[position.getRow()][position.getColumn()];
	}

	public Piece getPiece(int row, int column) {
		return getPiece(new Position(row, column));
	}

	public void putPiece(Piece piece, Position position) {
		if (getPiece(position) != null) {
			throw new BoardException("There is already a piece on " + position);
		}

		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
		piece.board = this;
	}

	public Piece removePiece(Position position) {
		Piece removed = getPiece(position);

		pieces[position.getRow()][position.getColumn()] = null;

		if (removed != null) {
			removed.position = null;
		}

		return removed;
	}
}
