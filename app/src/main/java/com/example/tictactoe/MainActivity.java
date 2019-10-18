package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    final static int YELLOW_PIECE = 0;
    final static int RED_PIECE = 1;
    final static int EMPTY = 2;

    int[] gameState = {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = YELLOW_PIECE;
    boolean gameActive = true;

    public void dropin(View view)
    {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == EMPTY && gameActive)
        {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == YELLOW_PIECE)
            {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = RED_PIECE;
            }
            else
            {
                counter.setImageResource(R.drawable.red);
                activePlayer = YELLOW_PIECE;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions)
            {
                if (gameState[winningPosition[YELLOW_PIECE]] == gameState[winningPosition[RED_PIECE]] && gameState[winningPosition[RED_PIECE]] == gameState[winningPosition[EMPTY]] && gameState[winningPosition[YELLOW_PIECE]] != EMPTY)
                {
                    gameActive = false;
                    String winner = "";

                    if (activePlayer == RED_PIECE)
                    {
                        winner = "Yellow player ";
                    }
                    else
                    {
                        winner = "Red player ";
                    }
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + "has won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i <gridLayout.getChildCount(); i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i = 0; i < gameState.length; i++)
        {
            gameState[i] = EMPTY;
        }
        activePlayer = YELLOW_PIECE;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
