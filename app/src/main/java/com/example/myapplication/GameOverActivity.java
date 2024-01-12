package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        //ottiene l'id dello sfondo da inserire nella schermata di game over
        int backgroundID = getIntent().getIntExtra("background", 0);
        View view = this.getWindow().getDecorView();
        view.setBackgroundResource(backgroundID);

        //ottiene i punti fatti dall'utente e li mostra nella schermata di game over
        int points = getIntent().getIntExtra("points", 0);
        TextView counter = findViewById(R.id.counter);
        counter.setText(Integer.toString(points));

        //ottiene il suono da riprodurre
        int soundID = getIntent().getIntExtra("sound", 0);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, soundID);
        mediaPlayer.start();
    }
    public void launchMenu(View view) {
        launchMenu();
    }
}