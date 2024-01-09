package com.example.chess.logic;
public class Pawn extends Piece{
	
	public Pawn(Board board, int color, int xLoc, int yLoc){
		super(board, color, xLoc, yLoc);
	}
	
	public boolean canMoveTo(int xPosition, int yPosition){
		if(canMoveGenerics(xPosition,yPosition)){
			return pawnMovement(xPosition, yPosition);
		}
		return false;
	}
	
	private boolean pawnMovement(int xPosition, int yPosition){
		int one_step;
		int two_step;
		Piece target = chessBoard.pieceAt(xPosition, yPosition);
		
		if (this.getColor() == BLACK){
			one_step = -1;
			two_step = -2;
		}
		else{
			one_step = 1;
			two_step = 2;
		}
		
		if (yPosition - this.getYLocation() == one_step){
			if (xPosition == this.getXLocation() && target == null){
				return true;
			}
			if (Math.abs(this.getXLocation() - xPosition) == 1 && target != null){
				return true;
			}

		}
		else if (!hasMoved){
			if (yPosition - this.getYLocation() == two_step){
				if (xPosition == this.getXLocation() && target == null){
					return true;
				}
			}
		}

		return false;
	}
}
