package com.example.connectgame3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // yellow = 0 and red = 1 and 2 = empty

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int ActivePLayer = 0;

    boolean gameActive = true;

    public void dropin(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = ActivePLayer;

            counter.setTranslationY(-1500);

            if (ActivePLayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                ActivePLayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                ActivePLayer = 0;
            }


            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    //someone has won

                    gameActive = false;

                    String winner = "";

                    if (ActivePLayer == 1) {

                        winner = "yellow";

                    } else {

                        winner = "red";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!!");

                    winnerTextView.setVisibility(View.VISIBLE);

                    playAgainButton.setVisibility(view.VISIBLE);
                }
            }
        }

    }

    public void  playAgain (View view){

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        winnerTextView.setVisibility(View.INVISIBLE);

        playAgainButton.setVisibility(view.INVISIBLE);

        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);


        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for(int i=0; i<gameState.length; i++){

            gameState[i] =2;
        }

         ActivePLayer = 0;

         gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
