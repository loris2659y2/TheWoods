package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void launchGame(View view) {
        Intent gameIntent = new Intent(this, PlayActivity.class);
        startActivity(gameIntent);
    }
    public void launchInstructions(View view) {
        Intent instructionsIntent = new Intent(this, InstructionsActivity.class);
        startActivity(instructionsIntent);
    }
}