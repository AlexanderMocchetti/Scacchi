package com.example.chess.logic;
public class King extends Piece{

	public King(Board board, int color, int xPos, int yPos) {
		super(board, color, xPos, yPos);
	}

	public boolean canMoveTo(int newXPos, int newYPos){
		if(canMoveGenerics(newXPos, newYPos))
			return kingMovement(newXPos, newYPos);
		return false;
	}
	
	private boolean kingMovement(int newXPos, int newYPos){
		int absoluteX = Math.abs(newXPos - xPos);
		int absoluteY = Math.abs(newYPos - yPos);

		if (absoluteX <= 1 && absoluteY <= 1){
			return absoluteX != 0 || absoluteY != 0;
		}
		return false;
	}
}
