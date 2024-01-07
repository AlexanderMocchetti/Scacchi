package com.example.chess.logic.pieces;

import com.example.chess.logic.Board;

public class Queen extends Piece{
    public Queen(int posX, int posY, int color, Board chessBoard) {
        super(posX, posY, color, chessBoard);
    }
    @Override
    public boolean canMoveTo(int newPosX, int newPosY) {
        return canMoveDiagonal(newPosX, newPosY) || canMoveStraight(newPosX, newPosY);
    }
}
