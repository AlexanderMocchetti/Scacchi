package com.example.chess.logic;

import com.example.chess.logic.pieces.*;

public class Board {
    private Piece[][] chessBoard;
    private Piece[][] shadowBoard;
    public Board(int chessBoardHeight, int chessBoardWidth) {
        chessBoard = new Piece[chessBoardHeight][chessBoardWidth];
    }

    public void addPiece(Piece piece) {
        chessBoard[piece.getPosY()][piece.getPosX()] = piece;
    }
    public void addRook(int posX, int posY, int color) {
        addPiece(new Rook(posX, posY, color, this));
    }
    public void addQueen(int posX, int posY, int color) {
        addPiece(new Queen(posX, posY, color, this));
    }
    public void addKing(int posX, int posY, int color) {
        addPiece(new King(posX, posY, color, this));
    }
    public void addPawn(int posX, int posY, int color) {
        addPiece(new Pawn(posX, posY, color, this));
    }
    public void addKnight(int posX, int posY, int color) {
        addPiece(new Knight(posX, posY, color, this));
    }
    public void addBishop(int posX, int posY, int color) {
        addPiece(new Bishop(posX, posY, color, this));
    }
    public Piece pieceAt(int posX, int posY) {
        return chessBoard[posY][posX];
    }
    public int getChessBoardHeight() {
        return chessBoard.length;
    }
    public int getChessBoardWidth() {
        return chessBoard[0].length;
    }
}
