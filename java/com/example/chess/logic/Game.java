package com.example.chess.logic;

import com.example.chess.logic.pieces.*;

public class Game {
    private Board board;
    public Game(int chessBoardHeight, int chessBoardWidth) {
        board = new Board(chessBoardHeight, chessBoardWidth);
        initializeBoard();
    }

    private void initializeBoard() {
        board.addRook(0, 0, Piece.BLACK);
    }
    public Piece pieceAt(int posX, int posY) {
        return board.pieceAt(posX, posY);
    }

    public boolean canMoveTo(Piece piece, int posX, int posY) {
        return piece.canMoveTo(posX, posY);
    }
    public int[] getScreenCoordinates(int worldX, int worldY) {
        return new int[]{board.getChessBoardWidth() - worldX - 1, board.getChessBoardHeight() - worldY - 1};
    }
}
