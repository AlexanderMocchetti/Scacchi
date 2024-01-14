package com.example.chess.logic;

public class MoveChecker {
    private Piece piece, pieceAtNewLocation;
    private int xLocToBeTested, yLocToBeTested, oldXLoc, oldYLoc;
    private final Board chessBoard;

    public MoveChecker(Board chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void testMove(Piece piece, int xLoc, int yLoc) {
        this.piece = piece;
        xLocToBeTested = xLoc;
        yLocToBeTested = yLoc;
        pieceAtNewLocation = chessBoard.pieceAt(xLoc, yLoc);
        oldXLoc = piece.getXPos();
        oldYLoc = piece.getYPos();
        piece.moveTo(xLoc, yLoc);
    }

    public void undoMove() {
        piece.moveTo(oldXLoc, oldYLoc);
        if (pieceAtNewLocation != null) {
            pieceAtNewLocation.moveTo(xLocToBeTested, yLocToBeTested);
            pieceAtNewLocation.hasMoved = false;
        }
        piece.hasMoved = false;
    }
}
