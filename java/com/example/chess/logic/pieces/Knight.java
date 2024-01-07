package com.example.chess.logic.pieces;

import com.example.chess.logic.Board;

public class Knight extends Piece{
    public Knight(int posX, int posY, int color, Board chessBoard) {
        super(posX, posY, color, chessBoard);
    }

    @Override
    public boolean canMoveTo(int newPosX, int newPosY) {
        return false;
    }
}
