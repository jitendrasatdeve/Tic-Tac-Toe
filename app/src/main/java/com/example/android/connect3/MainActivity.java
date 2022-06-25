package com.example.android.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activeAccount =0;
    boolean IsGameActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public  void dropIn(View view){


        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && IsGameActive) {
            gameState[tappedCounter] = activeAccount;
            counter.setTranslationY(-1000f);
            if (activeAccount == 0) {

                counter.setImageResource(R.drawable.yellow);
                activeAccount = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeAccount = 0;
            }
            counter.animate().translationYBy(1000f).rotation(36).setDuration(300);
            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2){
                    System.out.println(gameState[winningPosition[0]]);

                    IsGameActive = false;
                    String win = "Red";
                    if(gameState[winningPosition[0]]==0){
                        win = "Yellow";
                    }
                    TextView winner = findViewById(R.id.textView);

                    winner.setText(win + " Has Won!!");
                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    public void playAgain(View view){
        IsGameActive = true;
        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activeAccount =0;
        for (int i=0;i<gameState.length;i++){
            gameState[i] = 2;
        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}