package com.example.chess.logic;

public class Queen extends Piece{

	public Queen(Board board, int color, int xPos, int yPos) {
		super(board, color, xPos, yPos);
	}

	public boolean canMoveTo(int newXPos, int newYPos){
		if(canMoveGenerics(newXPos,newYPos)){
			return queenMovement(newXPos, newYPos);
		}
		return false;
	}
	
	private boolean queenMovement(int newXPos, int newYPos){
		return canMoveStraight(newXPos, newYPos) ||
				canMoveDiagonal(newXPos, newYPos);
	}
}
