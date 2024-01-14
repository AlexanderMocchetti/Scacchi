package com.example.chess.logic;
public class Pawn extends Piece{
	
	public Pawn(Board board, int color, int xPos, int yPos) {
		super(board, color, xPos, yPos);
	}

	public boolean canMoveTo(int newXPos, int newYPos){
		if(canMoveGenerics(newXPos,newYPos)){
			return pawnMovement(newXPos, newYPos);
		}
		return false;
	}
	
	private boolean pawnMovement(int newXPos, int newYPos){
		int oneStep, twoStep;

		Piece target = chessBoard.pieceAt(newXPos, newYPos);
		
		if (color == BLACK) {
			oneStep = -1;
			twoStep = -2;
		} else {
			oneStep = 1;
			twoStep = 2;
		}
		
		if (newYPos - yPos == oneStep) {
			// Movimento
			if (newXPos == xPos && target == null)
				return true;

			// Cattura
			if (Math.abs(xPos - newXPos) == 1 && target != null)
				return true;
		}
		if (!hasMoved && newYPos - yPos == twoStep){
			return newXPos == xPos && target == null;
		}
		return false;
	}
}
