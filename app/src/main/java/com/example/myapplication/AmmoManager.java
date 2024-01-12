package com.example.myapplication;

import android.widget.TextView;

public class AmmoManager {
    TextView ammoCounter;
    int ammo=15;

    public AmmoManager(TextView ammoCounter){
        this.ammoCounter = ammoCounter;
        ammoCounter.setText(Integer.toString(ammo));
    }
    public void decreaseAmmo(){
        ammo--;
        ammoCounter.setText(Integer.toString(ammo));
    }
    public int getAmmo() {
        return ammo;
    }
    public boolean hasAmmo(){
        if(ammo==0){
            return false;
        }else{
            return true;
        }
    }
}
