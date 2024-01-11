package com.example.myapplication;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected void launchMenu() {
        Intent menuIntent = new Intent(this, MainActivity.class);
        startActivity(menuIntent);
        finish();  // Chiudi l'Activity corrente
    }
}