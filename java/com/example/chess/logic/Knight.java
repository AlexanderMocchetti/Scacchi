package com.example.chess.logic;
public class Knight extends Piece{
	public Knight(Board board, int color, int xLoc, int yLoc){
		super(board, color, xLoc, yLoc);
	}
	
	public boolean canMoveTo(int xPosition, int yPosition){
		if(canMoveGenerics(xPosition,yPosition)){
			return knightMovement(xPosition, yPosition);
		}
		return false;
	}
	
	private boolean knightMovement(int xPosition, int yPosition){
		if (Math.abs(this.getXLocation() - xPosition) == 2 && Math.abs(this.getYLocation() - yPosition) == 1)
			return true;
		if (Math.abs(this.getXLocation() - xPosition) == 1 && Math.abs(this.getYLocation() - yPosition) == 2)
			return true;
		return false;
	}
}
