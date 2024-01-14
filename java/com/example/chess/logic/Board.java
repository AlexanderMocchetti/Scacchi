package com.example.chess.logic;

public class Board {
	private final Piece[][] chessBoard;

	public Board(Piece[][] chessBoard) {
		this.chessBoard = chessBoard;
	}
	public Board(int xDimension, int yDimension){
		chessBoard = new Piece[xDimension][yDimension];
	}
	
	public boolean isEmptyPosition(int xPosition, int yPosition){
		if (isInBounds(xPosition, yPosition))
			return chessBoard[xPosition][yPosition] == null;
		return false;
	}
	
	public boolean isInBounds(int xPosition, int yPosition){
		return xPosition < getXDimension() && xPosition >= 0 &&
				yPosition < getYDimension() && yPosition >= 0;
	}
	
	public Piece pieceAt(int xPosition, int yPosition){
		if (isInBounds(xPosition, yPosition)){
			return chessBoard[xPosition][yPosition];
		}
		return null;
	}
	
	public int getXDimension(){
		return chessBoard[0].length;
	}
	
	public int getYDimension(){
		return chessBoard.length;
	}

	public void removeFromBoard(Piece removePiece){
		int oldXLocation = removePiece.getXPos();
		int oldYLocation = removePiece.getYPos();
		
		chessBoard[oldXLocation][oldYLocation] = null;
	}

	public void placePiece(Piece chessPiece, int xPosition, int yPosition){
		if (isInBounds(xPosition, yPosition))
			chessBoard[xPosition][yPosition] = chessPiece;
	}
}
