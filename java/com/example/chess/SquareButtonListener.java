package com.example.chess;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chess.logic.Game;
import com.example.chess.logic.Piece;

public class SquareButtonListener implements View.OnClickListener{
    private ImageButton previouslyClickedButton = null;
    private final ImageButton[][] chessBoard;
    private final Game game;
    private final TextView resultBar;
    public SquareButtonListener(ImageButton[][] chessBoard, Game game, TextView resultBar) {
        this.chessBoard = chessBoard;
        this.game = game;
        this.resultBar = resultBar;
    }
    @Override
    public void onClick(View v) {
        if (game.isGameOver()) {
            previouslyClickedButton = null;
            return;
        }
        if (previouslyClickedButton == null) {
            previouslyClickedButton = (ImageButton) v;
            return;
        }
        int currentPlayer = game.getPlayerTurn();
        int enemyPlayer = currentPlayer == Game.WHITE ? Game.BLACK : Game.WHITE;
        String player = currentPlayer == Game.WHITE ? "bianco" : "nero";
        String enemy = player.equals("bianco") ? "nero" : "bianco";
        String message = "Turno " + enemy;

        int[] oldCoordinates, newCoordinates;
        oldCoordinates = getCoordinates(previouslyClickedButton);
        newCoordinates = getCoordinates((ImageButton) v);
        if (game.updateGame(oldCoordinates[0], oldCoordinates[1], newCoordinates[0], newCoordinates[1])) {
            moveImageButton((ImageButton) v);
            if (game.isCheckmate(enemyPlayer))
                message = "Scacco matto, vince " + player;
            else if (game.isStalemate(enemyPlayer))
                message = "Stallo, non vince nessuno";
        }
        previouslyClickedButton = null;
        resultBar.setText(message);
    }
    private void moveImageButton(ImageButton currentButton) {
        Drawable previousDrawable = previouslyClickedButton.getDrawable();
        previouslyClickedButton.setImageDrawable(null);
        currentButton.setImageDrawable(previousDrawable);
    }

    // index 0: x; index 1: y
    private int[] getCoordinates(ImageButton button) {
        int[] coordinates = new int[2];
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[0].length; j++) {
                if (chessBoard[i][j] == button) {
                    coordinates[0] = j;
                    coordinates[1] = i;
                }
            }
        }
        return coordinates;
    }
}
