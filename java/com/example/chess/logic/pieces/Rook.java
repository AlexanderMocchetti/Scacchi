package com.example.chess.logic.pieces;

import com.example.chess.logic.Board;

public class Rook extends Piece{
    public Rook(int posX, int posY, int color, Board chessBoard) {
        super(posX, posY, color, chessBoard);
    }
    @Override
    public boolean canMoveTo(int newPosX, int newPosY) {
        return canMoveStraight(newPosX, newPosY);
    }
}
