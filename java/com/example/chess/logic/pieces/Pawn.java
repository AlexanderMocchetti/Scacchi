package com.example.chess.logic.pieces;

import com.example.chess.logic.Board;

public class Pawn extends Piece{
    private boolean hasMoved = false;
    public Pawn(int posX, int posY, int color, Board chessBoard) {
        super(posX, posY, color, chessBoard);
    }

    @Override
    public boolean canMoveTo(int newPosX, int newPosY) {
        return false;
    }
}
