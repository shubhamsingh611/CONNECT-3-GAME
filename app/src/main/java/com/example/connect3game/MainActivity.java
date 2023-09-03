package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red

    // To change player & Counter Colour
    int activePlayer = 0;
    //To stop dropIn of counters after game is over
    boolean gameIsActive = true;
    // winning positions
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    // For unplayed view (2 means unplayed)
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        // As here we don't want a specific image it can be anyone that's why we just write view

        //System.out.println(counter.getTag().toString());
        //used to print something in log
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        /*
        here we are getting the tag of the counter of view and then converting its string value
        to integer and storing in the tappedCounter variable
         */

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;
            // now that particular view state get replaced to active player and now it become played i.e (activePlayer = o or 1)
            counter.setTranslationY(-1000f);
            // for moving view off the screen or top of the screen(not on the screen as it has to come down)

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                // and then applying yellow imageView to it or yellow counter will drop if this condition satisfies
                activePlayer = 1;
                // for changing player 2 turn
            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            // using this if else condition two player can play and this will help them to changer the colour of the counter

            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
            // the image will come 1000dp down with 360 degree rotation in 3 mile seconds

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    gameIsActive = false;

                    String winner = "Red";

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";

                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has Won !");
                    //winner message printing

                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    // when someone one won's to display the LinearLayout where message get displayed

                }/* this if condition is to check which player has won in order to print it on screen and game will now be active
                if someone has won*/ else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2)
                            gameIsOver = false;

                    }// if none of gameState remains to it means all the ImageViews get filled and no one has won
                    if (gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a Draw !");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }

                }// this whole process is to check whether it's draw or not


            } /* winningPosition will contain mini array each time in for loop of those 8 arrays of
            winningPositions 2D array and we are comparing position 0,1,2 of the mini array with the
            gameState value to know whether red or yellow player is won
            */


        }    // as we only want to bring the counter down at text view when gameState is 2 or unplayed

    }

    public void playAgain(View view) {

        gameIsActive = true;
        // to check whether game is active or get over and then message get displayed

        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        //if user want to play again we need to make invisible the  Displaying liner layout

        activePlayer = 0;
        // we will again set activePlayer to 0 means yellow as we cant that o will be assigned to yellow

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        // now assigning 2 means unplayed view to all emements of gameState

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
            // this way accessing each image view of grid by index i and setting its resource to 0 means empty

        }// making each and every grid layout empty so that user can play again

    }// this function is used to allow user to play again after game get finished

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}