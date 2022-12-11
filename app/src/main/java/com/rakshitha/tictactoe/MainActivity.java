package com.rakshitha.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
boolean gameActive = true;
    // 0 - x
    // 1 - 0

    int activePLayer = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // state meaning
    // 0 - x
    // 1 - 0
    // 2 - Null

    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};

    public void playerTap(View view){
        ImageView img = (ImageView) view;
                int tappedImage = Integer.parseInt(img.getTag().toString());
                if(!gameActive){
                    gameReset(view);
                    return;
                }
                if(gameState[tappedImage]==2) {
                    gameState[tappedImage] = activePLayer;
                    img.setTranslationY(-1000f);
                    if (activePLayer == 0) {
                        img.setImageResource(R.drawable.x);
                        activePLayer = 1;

                        TextView status = findViewById(R.id.status);
                        status.setText("O's Turn - Tap to Play");
                    } else {
                        img.setImageResource(R.drawable.o);
                        activePLayer = 0;
                        TextView status = findViewById(R.id.status);
                        status.setText("X's Turn - Tap to Play");
                    }


                    img.animate().translationYBy(1000f).setDuration(300);
                }
                // Check if any player has won
        for( int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
            gameState[winPosition[1]] == gameState[winPosition[2]] &&
            gameState[winPosition[0]] != 2) {
                // somebody hs won! - Find out who!
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                    TextView status = findViewById(R.id.status);
                    status.setText("X has Won");
                } else {
                    winnerStr = "O has won";

                    // update the status bar for winner announcement
                    TextView status = findViewById(R.id.status);
                    status.setText("O has Won");
                }
            }
        }

    }

    public void gameReset(View view) {
        gameActive = true;
        activePLayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);


        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to Play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}