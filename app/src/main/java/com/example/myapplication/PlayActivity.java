package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    private ImageView man;
    private TextView counter;
    GameManager gameManager;
    ImageButton movingImage;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //assegnazioni
        initializeResources();
    }
    private void initializeResources(){
        movingImage = findViewById(R.id.movingImage);
        man = findViewById(R.id.man);
        counter = findViewById(R.id.counter);
        gameManager = new GameManager(movingImage, man, counter, this);
        gameManager.initializeAnimator();
        mediaPlayer = MediaPlayer.create(this, R.raw.shoot_sound);
    }
    public void checkImage(View view) {
        gameManager.checkImage(view);
    }

    public void playSound(View view) {
        mediaPlayer.start();
    }
}