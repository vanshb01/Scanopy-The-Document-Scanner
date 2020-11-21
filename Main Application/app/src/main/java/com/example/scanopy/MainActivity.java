package com.example.scanopy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mysong;
    private static int WELCOME_TIMEOUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mysong = MediaPlayer.create(MainActivity.this,R.raw.soundy);
        mysong.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome = new Intent(MainActivity.this,Ui_2.class);
                startActivity(welcome);
                finish();


            }
        }, WELCOME_TIMEOUT);}
        @Override
        protected void onPause() {
        super.onPause();
        mysong.release();
        finish();

    }
}