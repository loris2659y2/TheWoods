package com.example.myapplication;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Random;

public class GameManager {
    private ObjectAnimator animatorX;
    private ImageButton movingImage;
    private int [] characters = {
            R.drawable.dog,
            R.drawable.elephant,
            R.drawable.happy,
            R.drawable.sad,
            R.drawable.hat_guy,
            R.drawable.horse,
            R.drawable.hunter,
            R.drawable.black_dog,
            R.drawable.monster
    };
    private int imageID;
    Random random;
    private ImageView man;
    private boolean dead = false;
    private boolean clicked = false;
    private TextView pointsCunter;
    private int ammo;
    private ScoreManager scoreManager;
    private AmmoManager ammoManager;
    MediaPlayer mediaPlayer;
    private int peopleKilled;
    private int animalsKilled;
    private Activity activity;
    private int[] chooser = {
            0,
            1,
            2,
            3
    };
    private int maxPoints=5000;
    private int maxKills=3;

    //costruttore
    public GameManager(ImageButton movingImage, ImageView man, TextView pointsCounter, AmmoManager ammoManager, Activity activity){
        this.movingImage=movingImage;
        this.man=man;
        this.pointsCunter = pointsCounter;
        this.ammoManager = ammoManager;
        this.activity =activity;
        random = new Random();
        scoreManager = new ScoreManager();
        peopleKilled = 0;
        animalsKilled = 0;
    }

    //inizzializazione parametri aniamtore asse X
    public void initializeAnimator(){
        animatorX = ObjectAnimator.ofFloat(movingImage, "translationX", -1500f, 1500f);
        animatorX.setDuration(2000);
        animatorX.start();

        animatorX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {

            }

            //alla fine di ogni animazione controlla se i cattivi sono stati uccisi
            //se non lo sono toglie punti
            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                if(!dead){
                    clicked = false;
                    if(imageID==R.drawable.black_dog){
                        handleBlackdog(clicked);
                    }
                    if(imageID==R.drawable.monster){
                        handleMonster(clicked);
                    }
                    if(imageID==R.drawable.hunter){
                        handleHunter(clicked);
                    }
                }
                setAnimator();
                animatorX.start();
                clicked = false;
                dead = false;
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });
    }

    //metodo che sceglie un indice casuale dall'array di indici di immagini
    public void chooseImage(){
        int casualImg = random.nextInt(characters.length);
        imageID = characters[casualImg];
    }

    //modifica randomicamente i valori dell'animatore
    public void setAnimator(){
        chooseImage();
        int duration = random.nextInt(3000) +1000;
        int right;
        int left;
        int rnd = random.nextInt(100)+1;
        if(rnd%2==0){
            left = -1500;
            right = 1500;
            man.setImageResource(R.drawable.man_left);
        }else{
            left = 1500;
            right = -1500;
            man.setImageResource(R.drawable.man_right);
        }

        movingImage.setImageResource(imageID);
        animatorX.setFloatValues(left, right);
        animatorX.setDuration(duration);
    }

    //controlla che tipo di immagine viene cliccata e utilizza i metodi appropriati
    public void checkImage(View view) {
        mediaPlayer = MediaPlayer.create(activity, R.raw.shot_sound);
        mediaPlayer.start();
        ammoManager.decreaseAmmo();
        if(!ammoManager.hasAmmo()&&scoreManager.getPoints()<maxPoints){
            mediaPlayer.release();
            launchGameOver(chooser[3]);
        }
        clicked = true;
        if(imageID==R.drawable.black_dog){
            if(!dead){
                handleBlackdog(clicked);
            }
            dead = true;
        }else if(imageID==R.drawable.monster) {
            if(!dead){
                handleMonster(clicked);
            }
            dead=true;
        }else if(imageID==R.drawable.hunter) {
            if (!dead) {
                handleHunter(clicked);
            }
            dead = true;
        }else if(imageID==R.drawable.dog) {
                if(!dead){
                    handleDog();
                }
                dead=true;
        }else if(imageID==R.drawable.elephant) {
            if(!dead){
                handleElephant();
            }
            dead=true;
        }else if(imageID==R.drawable.horse) {
            if(!dead){
                handleHorse();
            }
            dead=true;
        }else if(imageID==R.drawable.hat_guy) {
            if(!dead){
                handleHatGuy();
            }
            dead = true;
        } else{
            if(!dead){
                handleOthers();
            }
            dead=true;
        }
    }

    //metodi che gestiscono ciascuna immagine
    private void handleBlackdog(boolean clicked){
        int points = 500;
        if(clicked){
            scoreManager.increasePoints(points);
            pointsCunter.setText(Integer.toString(scoreManager.getPoints()));
            movingImage.setImageResource(R.drawable.blackdog_dead);
        }else{
            scoreManager.decreasePoints(points);
            pointsCunter.setText(Integer.toString(scoreManager.getPoints()));
        }
        int p = scoreManager.getPoints();
        if(p>=maxPoints){
            launchGameOver(chooser[2]);
        }
    }
    private void handleMonster(boolean clicked){
        int points = 200;
        if(clicked){
            scoreManager.increasePoints(points);
            pointsCunter.setText(Integer.toString(scoreManager.getPoints()));
            movingImage.setImageResource(R.drawable.monster_dead);
        }else{
            scoreManager.decreasePoints(points);
            pointsCunter.setText(Integer.toString(scoreManager.getPoints()));
        }
        int p = scoreManager.getPoints();
        if(p>=maxPoints){
            launchGameOver(chooser[2]);
        }
    }
    private void handleHunter(boolean clicked){
        int points = 1000;
        if(clicked){
            scoreManager.increasePoints(points);
            pointsCunter.setText(Integer.toString(scoreManager.getPoints()));
            movingImage.setImageResource(R.drawable.hunter_dead);
        }else{
            scoreManager.decreasePoints(points);
            pointsCunter.setText(Integer.toString(scoreManager.getPoints()));
        }
        int p = scoreManager.getPoints();
        if(p>=maxPoints){
            launchGameOver(chooser[2]);
        }
    }
    private void handleDog() {
        int points = 1000;
        scoreManager.decreasePoints(points);
        pointsCunter.setText(Integer.toString(scoreManager.getPoints()));
        movingImage.setImageResource(R.drawable.dog_dead);

        animalsKilled++;
        if(animalsKilled==maxKills){
            launchGameOver(chooser[1]);
        }
    }
    private void handleElephant(){
        int points = 200;
        scoreManager.decreasePoints(points);
        pointsCunter.setText(Integer.toString(scoreManager.getPoints()));

        movingImage.setImageResource(R.drawable.elephant_dead);

        animalsKilled++;
        if(animalsKilled==maxKills){
            launchGameOver(chooser[1]);
        }
    }
    private void handleHorse(){
        int points = 500;
        scoreManager.decreasePoints(points);
        pointsCunter.setText(Integer.toString(scoreManager.getPoints()));
        movingImage.setImageResource(R.drawable.horse_dead);

        animalsKilled++;
        if(animalsKilled==maxKills){
            launchGameOver(chooser[1]);
        }
    }
    private void handleHatGuy(){
        int points = 1000;
        scoreManager.decreasePoints(points);
        pointsCunter.setText(Integer.toString(scoreManager.getPoints()));
        movingImage.setImageResource(R.drawable.hatguy_dead);

        peopleKilled++;
        if(peopleKilled==maxKills){
            launchGameOver(chooser[0]);
        }
    }
    private void handleOthers(){
        int points = 1000;
        scoreManager.decreasePoints(points);
        pointsCunter.setText(Integer.toString(scoreManager.getPoints()));
        movingImage.setImageResource(R.drawable.dead);

        peopleKilled++;
        if(peopleKilled==maxKills){
            launchGameOver(chooser[0]);
        }
    }
    //sceglie quale schermata di game over mostrare
    private Intent setIntent(int chooser){
        Intent intent = new Intent(activity, GameOverActivity.class);
        intent.putExtra("points", scoreManager.getPoints());
        switch (chooser) {
            case 0:
                intent.putExtra("background", R.drawable.arrested_background);
                intent.putExtra("sound", R.raw.arrested_sound);
                return intent;
            case 1:
                intent.putExtra("background", R.drawable.kicked_background);
                intent.putExtra("sound", R.raw.kicked_sound);
                return intent;
            case 2:
                intent.putExtra("background", R.drawable.win_background);
                intent.putExtra("sound", R.raw.win_sound);
                return intent;
            case 3:
                intent.putExtra("background", R.drawable.noammo_background);
                intent.putExtra("sound", R.raw.noammo_sound);
                return intent;
        }
        return intent;
    }

    //attiva la schermata di gameover
    public void launchGameOver(int num){

        Intent gameOverIntent = setIntent(num);

        activity.startActivity(gameOverIntent);
        activity.finish();
    }
}
