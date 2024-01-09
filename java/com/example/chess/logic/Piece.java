package com.example.chess.logic;
abstract public class Piece {
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	private int xLocation;
	private int yLocation;
	private int color;
	protected boolean hasMoved;
	protected Board chessBoard;

	public Piece(Board board, int color){
		this.chessBoard = board;
		this.color = color;
		hasMoved = false;
		xLocation = -1;
		yLocation = -1;
	}

	public Piece(Board board, int color, int xLoc, int yLoc){
		this.chessBoard = board;
		this.color = color;
		this.hasMoved = false;
		this.xLocation = xLoc;
		this.yLocation = yLoc;

		chessBoard.placePiece(this, xLoc, yLoc);
	}

	public boolean canMoveTo(int xPosition, int yPosition){
		return canMoveGenerics(xPosition, yPosition);
	}

	protected boolean canMoveGenerics(int xPosition, int yPosition){
		if (chessBoard.isInBounds(xPosition, yPosition)){
			Piece location = chessBoard.pieceAt(xPosition, yPosition);

			if (location == null) return true;
			if (location.getColor() != this.color) return true;
		}
		return false;
	}

	public void moveTo(int xPosition, int yPosition){
			if (chessBoard.pieceAt(xLocation, yLocation) == this)
				chessBoard.removeFromBoard(this);
			this.xLocation = xPosition;
			this.yLocation = yPosition;

			Piece target = chessBoard.pieceAt(xPosition, yPosition);
			if (target != null){
				this.capturePiece(target);
			}
			chessBoard.placePiece(this, xPosition, yPosition);
			hasMoved = true;
	}


	public void removePiece() {
		chessBoard.removeFromBoard(this);
		xLocation = -1;
		yLocation = -1;
	}

	public void capturePiece(Piece capturedPiece){
		capturedPiece.removePiece();
	}

	public boolean onBoard(){
		if (chessBoard.isInBounds(xLocation, yLocation))
			return true;
		return false;
	}


	public int getXLocation(){
		return xLocation;
	}

	public int getYLocation(){
		return yLocation;
	}

	public int getColor(){
		return color;
	}

	public Board getBoard(){
		return chessBoard;
	}

	protected boolean isMovingStraight(int xPosition, int yPosition) {
		int currX = this.getXLocation();
		int currY = this.getYLocation();

		int smallerVal;
		int largerVal;

		if (currX == xPosition){
			if (currY > yPosition){
				smallerVal = yPosition;
				largerVal = currY;
			}
			else if (yPosition > currY){
				smallerVal = currY;
				largerVal = yPosition;
			}
			else return false;

			smallerVal++;
			for(; smallerVal < largerVal; smallerVal++){
				if (chessBoard.pieceAt(currX, smallerVal) != null){
					return false;
				}
			}
			return true;
		}

		if (currY == yPosition){
			if (currX > xPosition){
				smallerVal = xPosition;
				largerVal = currX;
			}
			else if (xPosition > currX){
				smallerVal = currX;
				largerVal = xPosition;
			}
			else return false;

			smallerVal++;
			for(; smallerVal < largerVal; smallerVal++){
				if (chessBoard.pieceAt(smallerVal, currY) != null){
					return false;
				}
			}
			return true;
		}

		return false;
	}

	protected boolean isMovingDiagonal(int xPosition, int yPosition) {
		int xTotal = Math.abs(xPosition - this.getXLocation());
		int yTotal = Math.abs(yPosition - this.getYLocation());

		if (xTotal != yTotal) {
			return false;
		}

		int x, y;
		x = xLocation;
		y = yLocation;
		int deltaX, deltaY;
		if (xLocation < xPosition)
			deltaX = 1;
		else
			deltaX = -1;

		if (yLocation < yPosition)
			deltaY = 1;
		else
			deltaY = -1;

		x += deltaX;
		y += deltaY;

		while (x != xPosition && y != yPosition) {
			if (chessBoard.pieceAt(x, y) != null)
				return false;
			x += deltaX;
			y += deltaY;
		}
		return true;
	}
}
