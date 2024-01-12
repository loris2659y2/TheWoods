package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayActivity extends BaseActivity {

    private ImageView man;
    private TextView pointsCounter;
    private TextView ammoCounter;
    private GameManager gameManager;
    private ImageButton movingImage;
    private MediaPlayer mediaPlayer;
    public AmmoManager ammoManager;
    int points;
    int maxPoints = 5000;

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
        pointsCounter = findViewById(R.id.pointsCounter);
        points = Integer.parseInt(pointsCounter.getText().toString());
        ammoCounter = findViewById(R.id.ammoCounter);
        ammoManager = new AmmoManager(ammoCounter);
        gameManager = new GameManager(movingImage, man, pointsCounter, ammoManager, this);
        gameManager.initializeAnimator();
    }
    public void checkImage(View view) {
        gameManager.checkImage(view);
    }

    public void playSound(View view) {
        mediaPlayer = MediaPlayer.create(this, R.raw.shot_sound);
        mediaPlayer.start();
        ammoManager.decreaseAmmo();
        if(!ammoManager.hasAmmo()&&points<maxPoints){
            gameManager.launchGameOver(3);
        }
    }
    public void launchMenu(View view) {
        launchMenu();
    }
}