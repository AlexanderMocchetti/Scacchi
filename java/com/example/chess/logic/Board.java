package com.example.chess.logic;

public class Board {
	private Piece[][] chessBoard;

	public Board(Piece[][] chessBoard) {
		this.chessBoard = chessBoard;
	}
	public Board(int xDimension, int yDimension){
		chessBoard = new Piece[xDimension][yDimension];
	}
	
	public boolean isEmptyPosition(int xPosition, int yPosition){
		if (isInBounds(xPosition, yPosition)){
			if (chessBoard[xPosition][yPosition] == null)
				return true;
		}
		return false;
	}
	
	public boolean isInBounds(int xPosition, int yPosition){
		if (xPosition < getXDimension() && xPosition >= 0 &&
				yPosition < getYDimension() && yPosition >= 0)
			return true;
		return false;
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
	
	public Piece[][] getChessBoard(){
		return chessBoard;
	}

	public void removeFromBoard(Piece removePiece){
		int oldXLocation = removePiece.getXLocation();
		int oldYLocation = removePiece.getYLocation();
		
		chessBoard[oldXLocation][oldYLocation] = null;
	}

	public void placePiece(Piece chessPiece, int xPosition, int yPosition){
		if (isInBounds(xPosition, yPosition))
			chessBoard[xPosition][yPosition] = chessPiece;
	}
}
