package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

public class InstructionsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable);
    }
    public void launchMenu(View view) {
        launchMenu();
    }
}