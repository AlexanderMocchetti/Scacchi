package com.example.chess.logic;

public class Bishop extends Piece{
	public Bishop(Board board, int color, int xPos, int yPos){
		super(board, color, xPos, yPos);
	}
	
	public boolean canMoveTo(int newXPos, int newYPos){
		if(canMoveGenerics(newXPos, newYPos))
			return bishopMovement(newXPos, newYPos);
		return false;
	}
	private boolean bishopMovement(int newXPos, int newYPos){
		return canMoveDiagonal(newXPos, newYPos);
	}
}