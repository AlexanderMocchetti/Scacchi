package com.example.chess.logic.pieces;

import com.example.chess.logic.Board;

public abstract class Piece {
    public static int
        BLACK = 0,
        WHITE = 1;
    protected int posX, posY;
    protected int color;
    protected Board chessBoard;
    public Piece(int posX, int posY, int color, Board chessBoard) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.chessBoard = chessBoard;
    }
    public abstract boolean canMoveTo(int newPosX, int newPosY);
    protected boolean canMoveToGeneric(int newPosX, int newPosY) {
        Piece location = chessBoard.pieceAt(newPosX, newPosY);
        if (location == null)
            return true;
        return location.color != color;
    }
    private boolean isRouteStraight(int newPosX, int newPosY) {
        return posX == newPosX || posY == newPosY;
    }

    protected boolean canMoveStraight(int newPosX, int newPosY) {

        if (!isRouteStraight(newPosX, newPosY) && !canMoveToGeneric(newPosX, newPosY))
            return false;

        int fixedSquare, startingSquare, endingSquare, step;
        boolean isRouteVertical = (posX == newPosX);

        if (isRouteVertical) {
            fixedSquare = posX;
            startingSquare = posY;
            endingSquare = newPosY;
        } else {
            fixedSquare = posY;
            startingSquare = posX;
            endingSquare = newPosX;
        }

        if (startingSquare < endingSquare)
            step = 1;
        else
            step = -1;

        for (int square = startingSquare; square != endingSquare; square += step) {
            boolean canMoveToSquare;

            if (isRouteVertical)
                canMoveToSquare = canMoveToGeneric(fixedSquare, square);
            else
                canMoveToSquare = canMoveToGeneric(square, fixedSquare);

            if (!canMoveToSquare)
                return false;
        }
        return true;
    }

    private boolean isRouteDiagonal(int newPosX, int newPosY) {
        // coefficiente angolare uguale ad 1
        return Math.abs(posX - newPosX) == Math.abs(posY - newPosY);
    }

    protected boolean canMoveDiagonal(int newPosX, int newPosY) {
        if (!isRouteDiagonal(newPosX, newPosY) && !canMoveToGeneric(newPosX, newPosY))
            return false;
        int x, y;
        x = posX;
        y = posY;
        int deltaX, deltaY;
        if (posX < newPosX)
            deltaX = 1;
        else
            deltaX = -1;

        if (posY < newPosY)
            deltaY = 1;
        else
            deltaY = -1;

        x += deltaX;
        y += deltaY;

        while (x != newPosX && y != newPosY) {
            if (!canMoveToGeneric(x, y))
                return false;
            x += deltaX;
            y += deltaY;
        }
        return true;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
