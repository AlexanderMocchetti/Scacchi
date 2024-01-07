package com.example.chess;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;

import com.example.chess.logic.Game;
import com.example.chess.logic.pieces.Piece;

public class SquareButtonListener implements View.OnClickListener{
    private ImageButton previouslyClickedButton = null;
    private ImageButton[][] chessBoard;
    private Game game;
    public SquareButtonListener(ImageButton[][] chessBoard, Game game) {
        this.chessBoard = chessBoard;
        this.game = game;
    }
    @Override
    public void onClick(View v) {
        if (previouslyClickedButton == null) {
            previouslyClickedButton = (ImageButton) v;
            return;
        }
        moveImageButton((ImageButton) v);
    }
    private void moveImageButton(ImageButton currentButton) {
        Drawable previousDrawable = previouslyClickedButton.getDrawable();
        previouslyClickedButton.setImageDrawable(null);
        currentButton.setImageDrawable(previousDrawable);
        previouslyClickedButton = null;
    }
}
