package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chess.logic.Game;

public class GameActivity extends AppCompatActivity {
    private ImageButton[][] board;
    private Button resetButton;
    private Game game;
    private TextView resultBar;
    private int chessBoardWidth, chessBoardHeight;
    public static final int BLACK = 0;
    public static final int WHITE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        chessBoardWidth = getResources().getInteger(R.integer.chessBoardWidth);
        chessBoardHeight = getResources().getInteger(R.integer.chessBoardHeight);
        board = new ImageButton[chessBoardHeight][chessBoardWidth];
        resetButton = findViewById(R.id.resetButton);
        resultBar = findViewById(R.id.resultBar);
        game = new Game();
        SquareButtonListener squareButtonListener = new SquareButtonListener(board, game, resultBar);
        for (int i = 0; i < chessBoardHeight; i++) {
            for(int j = 0; j < chessBoardWidth; j++) {
                int indexFlattened = (i * chessBoardWidth) + j;
                board[i][j] = (ImageButton) findViewById(getResources().getIdentifier(
                         "square" + (indexFlattened + 1), "id", "com.example.chess"));
                board[i][j].setOnClickListener(squareButtonListener);
                Log.d("Board Check", getResources().getResourceName(board[i][j].getId()));
            }
        }
        resetButton.setOnClickListener(v -> {
            game.reset();
            resetBoard();
            initializeBoard(WHITE);
            resultBar.setText((CharSequence) "Turno bianco");
        });
        initializeBoard(WHITE);
    }
    private void resetBoard() {
        for (int i = 0; i < chessBoardHeight; i++) {
            for (int j = 0; j < chessBoardWidth; j++) {
                board[i][j].setImageDrawable(null);
            }
        }
    }
    private void initializeBoard(int color) {
        String friendlyColor = null;
        String enemyColor = null;
        String centerLeft = null;
        switch (color) {
            case BLACK:
                friendlyColor = "black";
                enemyColor = "white";
                centerLeft = "king";
                break;
            case WHITE:
                friendlyColor = "white";
                enemyColor = "black";
                centerLeft = "queen";
                break;
        }
        String centerRight = centerLeft.equals("queen") ? "king" : "queen";
        for (int i = 0; i < chessBoardWidth; i++) {
            board[1][i].setImageDrawable(getDrawable(
                    getResources().getIdentifier(
                            friendlyColor + "_pawn", "drawable", "com.example.chess"
                    )
            ));
            board[chessBoardHeight - 2][i].setImageDrawable(getDrawable(
                    getResources().getIdentifier(
                            enemyColor + "_pawn", "drawable", "com.example.chess"
                    )
            ));
        }
        board[0][0].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        friendlyColor + "_rook", "drawable", "com.example.chess"
                )
        ));
        board[0][1].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        friendlyColor + "_knight", "drawable", "com.example.chess"
                )
        ));
        board[0][2].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        friendlyColor + "_bishop", "drawable", "com.example.chess"
                )
        ));
        board[0][3].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        friendlyColor + "_" + centerLeft, "drawable", "com.example.chess"
                )
        ));
        board[0][4].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        friendlyColor + "_" + centerRight, "drawable", "com.example.chess"
                )
        ));
        board[0][5].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        friendlyColor + "_bishop", "drawable", "com.example.chess"
                )
        ));
        board[0][6].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        friendlyColor + "_knight", "drawable", "com.example.chess"
                )
        ));
        board[0][7].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        friendlyColor + "_rook", "drawable", "com.example.chess"
                )
        ));
        board[chessBoardHeight - 1][0].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        enemyColor + "_rook", "drawable", "com.example.chess"
                )
        ));
        board[chessBoardHeight - 1][1].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        enemyColor + "_knight", "drawable", "com.example.chess"
                )
        ));
        board[chessBoardHeight - 1][2].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        enemyColor + "_bishop", "drawable", "com.example.chess"
                )
        ));
        board[chessBoardHeight - 1][3].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        enemyColor + "_" + centerLeft, "drawable", "com.example.chess"
                )
        ));
        board[chessBoardHeight - 1][4].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        enemyColor + "_" + centerRight, "drawable", "com.example.chess"
                )
        ));
        board[chessBoardHeight - 1][5].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        enemyColor + "_bishop", "drawable", "com.example.chess"
                )
        ));
        board[chessBoardHeight - 1][6].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        enemyColor + "_knight", "drawable", "com.example.chess"
                )
        ));
        board[chessBoardHeight - 1][7].setImageDrawable(getDrawable(
                getResources().getIdentifier(
                        enemyColor + "_rook", "drawable", "com.example.chess"
                )
        ));
    }
}
