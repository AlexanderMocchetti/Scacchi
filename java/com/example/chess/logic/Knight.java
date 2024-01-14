package com.example.chess.logic;
public class Knight extends Piece{

	public Knight(Board board, int color, int xPos, int yPos) {
		super(board, color, xPos, yPos);
	}

	public boolean canMoveTo(int newXPos, int newYPos){
		if(canMoveGenerics(newXPos, newYPos))
			return knightMovement(newXPos, newYPos);
		return false;
	}
	
	private boolean knightMovement(int newXPos, int newYPos){
		if (Math.abs(xPos - newXPos) == 2 && Math.abs(yPos - newYPos) == 1)
			return true;
		if (Math.abs(xPos - newXPos) == 1 && Math.abs(yPos - newYPos) == 2)
			return true;
		return false;
	}
}
