package com.example.chess.logic;
public class King extends Piece{
	public King(Board board, int color, int xLoc, int yLoc){
		super(board, color, xLoc, yLoc);
	}
	
	public boolean canMoveTo(int xPosition, int yPosition){
		if(canMoveGenerics(xPosition,yPosition)){
			return kingMovement(xPosition, yPosition);
		}
		return false;
	}
	
	private boolean kingMovement(int xPosition, int yPosition){
		int absoluteX = Math.abs(xPosition - this.getXLocation());
		int absoluteY = Math.abs(yPosition - this.getYLocation());
		
		if (absoluteX <= 1 && absoluteY <= 1){
			if (absoluteX == 0 && absoluteY == 0){
				return false;
			}
			return true;
		}
		return false;
	}
}
