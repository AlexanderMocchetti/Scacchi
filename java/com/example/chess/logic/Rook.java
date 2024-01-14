package com.example.chess.logic;
public class Rook extends Piece{

	public Rook(Board board, int color, int xPos, int yPos) {
		super(board, color, xPos, yPos);
	}

	public boolean canMoveTo(int newXPos, int newYPos){
		if(canMoveGenerics(newXPos,newYPos)){
			return rookMovement(newXPos, newYPos);
		}
		return false;
	}
	private boolean rookMovement(int newXPos, int newYPos){
		return isMovingStraight(newXPos, newYPos);
	}
}