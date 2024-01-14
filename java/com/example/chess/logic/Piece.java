package com.example.chess.logic;
abstract public class Piece {
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	protected int xPos;
	protected int yPos;
	protected final int color;
	protected boolean hasMoved;
	protected Board chessBoard;

	public Piece(Board board, int color, int xPos, int yPos){
		this.chessBoard = board;
		this.color = color;
		this.hasMoved = false;
		this.xPos = xPos;
		this.yPos = yPos;
		chessBoard.placePiece(this, xPos, yPos);
	}

	public boolean canMoveTo(int newXPos, int newYPos){
		return canMoveGenerics(newXPos, newYPos);
	}

	protected boolean canMoveGenerics(int newXPos, int newYPos){
		if (!chessBoard.isInBounds(newXPos, newYPos))
			return false;
		Piece piece = chessBoard.pieceAt(newXPos, newYPos);
		return (piece == null) || (color != piece.color);
	}

	public void moveTo(int newXPos, int newYPos){
			if (chessBoard.pieceAt(xPos, yPos) == this)
				chessBoard.removeFromBoard(this);

			xPos = newXPos;
			yPos = newYPos;

			Piece target = chessBoard.pieceAt(newXPos, newYPos);
			if (target != null)
				this.capturePiece(target);

			chessBoard.placePiece(this, newXPos, newYPos);
			hasMoved = true;
	}


	public void removePiece() {
		chessBoard.removeFromBoard(this);
		xPos = -1;
		yPos = -1;
	}

	public void capturePiece(Piece capturedPiece){
		capturedPiece.removePiece();
	}

	public boolean onBoard(){
		return chessBoard.isInBounds(xPos, yPos);
	}


	public int getXPos(){
		return xPos;
	}

	public int getYPos(){
		return yPos;
	}

	public int getColor(){
		return color;
	}

	protected boolean canMoveStraight(int newXPos, int newYPos) {
		int start, end;

		if (xPos != newXPos && yPos != newYPos)
			return false;

		if (xPos == newXPos && yPos == newYPos)
			return false;

		boolean isRouteVertical = xPos == newXPos;

		if (isRouteVertical) {
			if (yPos > newYPos) {
				start = newYPos;
				end = yPos;
			} else {
				start = yPos;
				end = newYPos;
			}
		} else {
			if (xPos > newXPos) {
				start = newXPos;
				end = xPos;
			} else {
				start = xPos;
				end = newXPos;
			}
		}

		for (int square = start + 1; square < end; square++) {
			if (isRouteVertical)
				if (chessBoard.pieceAt(xPos, square) == null)
					return true;
			else
				if (chessBoard.pieceAt(square, yPos) == null)
					return true;
		}

		return false;
	}

	protected boolean canMoveDiagonal(int newXPos, int newYPos) {
		int xTotal = Math.abs(newXPos - xPos);
		int yTotal = Math.abs(newYPos - yPos);

		if (xTotal != yTotal)
			return false;

		int x, y;
		x = xPos;
		y = yPos;

		int deltaX, deltaY;

		if (xPos < newXPos)
			deltaX = 1;
		else
			deltaX = -1;

		if (yPos < newYPos)
			deltaY = 1;
		else
			deltaY = -1;

		x += deltaX;
		y += deltaY;

		while (x != newXPos && y != newYPos) {
			if (chessBoard.pieceAt(x, y) != null)
				return false;
			x += deltaX;
			y += deltaY;
		}
		return true;
	}
}
