package model.chess.move;

public class MoveException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MoveException(String message) {
		super(message);
	}
}
