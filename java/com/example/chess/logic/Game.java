package com.example.chess.logic;

import java.util.LinkedList;

public class Game {
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	public static final char[][] defaultBoard = {
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{},
			{},
			{},
			{},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
	};
	private int currentPlayer;
	private Board chessBoard;
	private LinkedList<Piece> blackPieces;
	private LinkedList<Piece> whitePieces;
	private King whiteKing, blackKing;
	private MoveChecker moveChecker;
	private boolean isGameOver = false;


	public Game(){
		reset();
	}

	//TODO: controllare scacco matto al di fuori
	public boolean updateGame(int oldXLoc, int oldYLoc, int newXLoc, int newYLoc) {
		Piece piece = chessBoard.pieceAt(oldXLoc, oldYLoc);
		if (!isLegalMove(piece, newXLoc, newYLoc))
			return false;
		piece.moveTo(newXLoc, newYLoc);
		switchPlayerTurn();
		return true;
	}

	public void reset() {
		chessBoard = new Board(8,8);
		currentPlayer = WHITE;
		blackPieces = new LinkedList<>();
		whitePieces = new LinkedList<>();
		moveChecker = new MoveChecker(chessBoard);
		setupBoard();
		isGameOver = false;
	}

	private boolean isLegalMove(Piece piece, int xLoc, int yLoc) {
		if (piece == null)
			return false;
		if (piece.getColor() != currentPlayer)
			return false;
		if (!piece.canMoveTo(xLoc, yLoc))
			return false;

		boolean isLegal = true;
		moveChecker.testMove(piece, xLoc, yLoc);
		if (isKingInCheck(currentPlayer))
			isLegal = false;
		moveChecker.undoMove();
		return isLegal;
	}

	private void setupBoard() {
		for (int i = 0; i < defaultBoard.length; i++) {
			if (defaultBoard[i].length == 0)
				continue;
			for (int j = 0; j < defaultBoard[i].length; j++) {
				int color;
				char ch = defaultBoard[i][j];
				if (Character.isLowerCase(ch))
					color = BLACK;
				else
					color = WHITE;

				ch = Character.toLowerCase(ch);

				switch(ch) {
					case 'r':
						addRook(color, j, i);
						break;
					case 'n':
						addKnight(color, j, i);
						break;
					case 'b':
						addBishop(color, j, i);
						break;
					case 'q':
						addQueen(color, j, i);
						break;
					case 'k':
						King king = addKing(color, j, i);
						if (color == BLACK)
							blackKing = king;
						else
							whiteKing = king;
						break;
					case 'p':
						addPawn(color, j, i);
						break;
				}
			}
		}
	}
	public boolean isStalemate(int color) {
		if (!isKingInCheck(color)) {
			if (!canMove(color)) {
				isGameOver = true;
				return true;
			}
		}
		return false;
	}
	public boolean isCheckmate(int color){
		if (isKingInCheck(color)){
			if(!canMove(color)) {
				isGameOver = true;
				return true;
			}
		}
		
		return false;
	}

	// Se falso può implicare stallo
	public boolean canMove(int player){
		int oldX, oldY;
		Piece target;
		LinkedList<Piece> checkPieces;
		
		if (player == BLACK)
			checkPieces = blackPieces;
		else
			checkPieces = whitePieces;
		
		for (int x = 0; x < chessBoard.getXDimension(); x++){
			for (int y = 0; y < chessBoard.getYDimension(); y++){	
				for (Piece currentPiece : checkPieces){
					if (currentPiece.canMoveTo(x, y)){
						target = chessBoard.pieceAt(x, y);
						oldX = currentPiece.getXLocation();
						oldY = currentPiece.getYLocation();
						
						currentPiece.moveTo(x, y);
						
						if (!isKingInCheck(player)){
							currentPiece.moveTo(oldX, oldY);
							if (target != null)
								target.moveTo(x, y);
							return true;
						} else {
							currentPiece.moveTo(oldX, oldY);
							if (target != null)
								target.moveTo(x, y);
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean isKingInCheck(int color){
		boolean result = false;
		
		LinkedList<Piece> enemyPieceList;
		King kingInQuestion;
		
		if (color == BLACK){
			enemyPieceList = whitePieces;
			kingInQuestion = blackKing;
		} else {
			enemyPieceList = blackPieces;
			kingInQuestion = whiteKing;
		}
		
		int xKingLoc = kingInQuestion.getXLocation();
		int yKingLoc = kingInQuestion.getYLocation();
		
		for (Piece currentPiece : enemyPieceList){
			if (currentPiece.canMoveTo(xKingLoc, yKingLoc)){
				result = true;
			}
		}
		
		return result;
	}
	
	public void removePiece(Piece removeThisPiece){
		removeThisPiece.removePiece();
		int color = removeThisPiece.getColor();
		
		if (color == BLACK)
			blackPieces.remove(removeThisPiece);
		else
			whitePieces.remove(removeThisPiece);
	}
	
	public void switchPlayerTurn(){
		if (currentPlayer == WHITE)
			currentPlayer = BLACK;
		else currentPlayer = WHITE;
	}

	public King addKing(int color, int xloc, int yloc) {
		King king = new King(chessBoard, color, xloc, yloc);
		pieceToColorHelper(king, color);

		return king;
	}
	
	public Queen addQueen(int color, int xloc, int yloc){
		Queen queen = new Queen(chessBoard, color, xloc, yloc);
		pieceToColorHelper(queen, color);
		
		return queen;
	}
	
	public Knight addKnight(int color, int xloc, int yloc){
		Knight knight = new Knight(chessBoard, color, xloc, yloc);
		pieceToColorHelper(knight, color);
		
		return knight;
	}
	
	public Rook addRook(int color, int xloc, int yloc){
		Rook rook = new Rook(chessBoard, color, xloc, yloc);
		pieceToColorHelper(rook, color);
		
		return rook;
	}
	
	public Bishop addBishop(int color, int xloc, int yloc){
		Bishop bishop = new Bishop(chessBoard, color, xloc, yloc);
		pieceToColorHelper(bishop, color);
		
		return bishop;
	}
	
	public Pawn addPawn(int color, int xloc, int yloc){
		Pawn pawn = new Pawn(chessBoard, color, xloc, yloc);
		pieceToColorHelper(pawn, color);
		
		return pawn;
	}
	
	private void pieceToColorHelper(Piece piece, int color){
		if (color == BLACK)
			blackPieces.add(piece);
		else
			whitePieces.add(piece);
	}
	
	public int getPlayerTurn(){
		return currentPlayer;
	}
	
	public void setPlayer(int player){
		currentPlayer = player;
	}

	public boolean isGameOver() {
		return isGameOver;
	}
}
