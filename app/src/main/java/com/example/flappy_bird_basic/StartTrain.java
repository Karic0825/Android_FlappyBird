package com.example.flappy_bird_basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

import utils.Utils;

public class StartTrain extends Activity {

    public static int onescore = 0;

    TrainAIView gameView;
    private Handler handler = new Handler(); // handler object used for handler thread
    private boolean hasDied = false; // Used to help re-set game loop when user has died
    private final static long TIMER_INTERVAL = 30; // used to define delay time

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
        Utils.toast(this.getApplicationContext(), "bbbb");
    }

    public void startGame(View view){
        gameView = new TrainAIView(this);
        setContentView(gameView);

        onescore = 0;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Running the Gameview class in handler
                handler.post(() -> {
                    gameView.invalidate();

                    if (!TrainAIView.gameState && !hasDied) // if user dies
                    {

                        Intent intent=new Intent(StartTrain.this, result.class);// show results screen
                        startActivity(intent);

                        hasDied = true; // set this boolean to true for two reasons. 1. allows the user to use "try again" button multiple times
                        // 2. shows result screen once, instead of re-printing it continually while the if loop conditions are met
                    }

                });

            }
        }, 0, TIMER_INTERVAL);

    }


}

