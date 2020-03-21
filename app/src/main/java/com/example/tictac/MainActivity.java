package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


        enum player
         {
        ONE, TWO,NONE
         }
        player currentplayer = player.ONE;

        player[] playerchoice = new player[9];
        int [][] WinnerRowsColumns = {{8,7,6},{5,4,3,},{2,1,0},{8,5,2},{7,4,1},{6,3,0},{8,4,0},{2,4,6}};

        private boolean gameover = false;
        private  Button buttonid;
        private GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerchoice[0]= player.NONE;
        playerchoice[1]= player.NONE;
        playerchoice[2]= player.NONE;
        playerchoice[3]= player.NONE;
        playerchoice[4]= player.NONE;
        playerchoice[5]= player.NONE;
        playerchoice[6]= player.NONE;
        playerchoice[7]= player.NONE;
        playerchoice[8]= player.NONE;

        buttonid =findViewById(R.id.buttonid);
        gridLayout = findViewById(R.id.gridlayout);


        buttonid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetthegame();

            }
        });
    }


    public void ImageIsclicked(View imageView) {
        //casting of type image view
        ImageView tappedImageView = (ImageView) imageView;

        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());

        if(playerchoice[tiTag]== player.NONE && gameover == false) {


            tappedImageView.setTranslationX(-2000);


            playerchoice[tiTag] = currentplayer;

            if (currentplayer == player.ONE) {
                tappedImageView.setImageResource(R.drawable.x);
                currentplayer = player.TWO;
            } else if (currentplayer == player.TWO) {
                tappedImageView.setImageResource(R.drawable.o);
                currentplayer = player.ONE;
            }

            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(300);

            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : WinnerRowsColumns) {

                if (playerchoice[winnerColumns[0]] ==
                        playerchoice[winnerColumns[1]]
                        && playerchoice[winnerColumns[1]]
                        == playerchoice[winnerColumns[2]]

                        && playerchoice[winnerColumns[0]] != player.NONE)
                {
                    buttonid.setVisibility(View.VISIBLE);
                    gameover = true;

                    String WinnerOfTheGame = "";

                    if (currentplayer == player.ONE)
                    {

                        WinnerOfTheGame = "Player 2 Wins the game";

                    }
                    else if (currentplayer == player.TWO)
                    {
                        WinnerOfTheGame = "Player 1 Wins the game";
                    }

                    Toast.makeText(this, WinnerOfTheGame, Toast.LENGTH_SHORT).show();
                }

            }

        }
    }

    private void resetthegame()
    {
        for(int index = 0;index < gridLayout.getChildCount();index++)
        {
        ImageView imageView = (ImageView) gridLayout.getChildAt(index);
        imageView.setImageDrawable(null);
        }
        currentplayer = player.ONE;

        playerchoice[0]= player.NONE;
        playerchoice[1]= player.NONE;
        playerchoice[2]= player.NONE;
        playerchoice[3]= player.NONE;
        playerchoice[4]= player.NONE;
        playerchoice[5]= player.NONE;
        playerchoice[6]= player.NONE;
        playerchoice[7]= player.NONE;
        playerchoice[8]= player.NONE;

        gameover = false;
        buttonid.setVisibility(View.VISIBLE);
    }
}
