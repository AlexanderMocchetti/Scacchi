package com.example.chess.logic;

import java.util.ArrayList;

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
	public static final int defaultNumberOfPieces = 16;
	private int currentPlayer;
	private Board chessBoard;
	private ArrayList<Piece> blackPieces;
	private ArrayList<Piece> whitePieces;
	private King whiteKing, blackKing;
	private MoveChecker moveChecker;
	private boolean isGameOver = false;


	public Game(){
		reset();
	}

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
		blackPieces = new ArrayList<>(defaultNumberOfPieces);
		whitePieces = new ArrayList<>(defaultNumberOfPieces);
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

	public boolean canMove(int player){
		ArrayList<Piece> checkPieces;

		if (player == BLACK)
			checkPieces = blackPieces;
		else
			checkPieces = whitePieces;

		for (int x = 0; x < chessBoard.getXDimension(); x++) {
			for (int y = 0; y < chessBoard.getYDimension(); y++) {
				for (Piece currentPiece : checkPieces) {
					if (!currentPiece.canMoveTo(x, y))
						continue;
					moveChecker.testMove(currentPiece, x, y);
					if (!isKingInCheck(player))
						return true;
					moveChecker.undoMove();
				}
			}
		}
		return false;
	}
	
	public boolean isKingInCheck(int color){
		boolean result = false;
		
		ArrayList<Piece> enemyPieceList;
		King kingInQuestion;
		
		if (color == BLACK){
			enemyPieceList = whitePieces;
			kingInQuestion = blackKing;
		} else {
			enemyPieceList = blackPieces;
			kingInQuestion = whiteKing;
		}
		
		int xKingLoc = kingInQuestion.getXPos();
		int yKingLoc = kingInQuestion.getYPos();
		
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

	public King addKing(int color, int xPos, int yPos) {
		King king = new King(chessBoard, color, xPos, yPos);
		pieceToColorHelper(king, color);

		return king;
	}
	
	public Queen addQueen(int color, int xPos, int yPos){
		Queen queen = new Queen(chessBoard, color, xPos, yPos);
		pieceToColorHelper(queen, color);
		
		return queen;
	}
	
	public Knight addKnight(int color, int xPos, int yPos){
		Knight knight = new Knight(chessBoard, color, xPos, yPos);
		pieceToColorHelper(knight, color);
		
		return knight;
	}
	
	public Rook addRook(int color, int xPos, int yPos){
		Rook rook = new Rook(chessBoard, color, xPos, yPos);
		pieceToColorHelper(rook, color);
		
		return rook;
	}
	
	public Bishop addBishop(int color, int xPos, int yPos){
		Bishop bishop = new Bishop(chessBoard, color, xPos, yPos);
		pieceToColorHelper(bishop, color);
		
		return bishop;
	}
	
	public Pawn addPawn(int color, int xPos, int yPos){
		Pawn pawn = new Pawn(chessBoard, color, xPos, yPos);
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

	public boolean isGameOver() {
		return isGameOver;
	}
}
