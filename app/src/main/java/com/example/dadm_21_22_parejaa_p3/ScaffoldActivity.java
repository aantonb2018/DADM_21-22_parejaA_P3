package com.example.dadm_21_22_parejaa_p3;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.dadm_21_22_parejaa_p3.counter.GameFragment;
import com.example.dadm_21_22_parejaa_p3.counter.MainMenuFragment;
import com.example.dadm_21_22_parejaa_p3.counter.ResultFragment;
import com.example.dadm_21_22_parejaa_p3.counter.ShipFragment;
import com.example.dadm_21_22_parejaa_p3.counter.StoryFragment;
import com.example.dadm_21_22_parejaa_p3.sound.SoundManager;

public class ScaffoldActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "content";

    private SoundManager soundManager;

    private boolean result;

    private int selectedShip = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaffold);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainMenuFragment(), TAG_FRAGMENT)
                    .commit();
        }
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundManager = new SoundManager(getApplicationContext());
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public void startGame() {
        // Navigate the the game fragment, which makes the start automatically
        soundManager.changeTheme(1);
        navigateToFragment(new GameFragment());
    }

    public void startStory() {
        // Navigate the the game fragment, which makes the start automatically
        navigateToFragment(new StoryFragment());
    }

    public void startShip() {
        // Navigate the the game fragment, which makes the start automatically
        navigateToFragment(new ShipFragment());
    }

    public void resultGame(boolean newResult) {
        // Navigate the the game fragment, which makes the start automatically
        result = newResult;
        if(result){
            soundManager.changeTheme(3);
        }else{
            soundManager.changeTheme(4);
        }
        navigateToFragment(new ResultFragment());
    }

    public void exitGame() {
        // Navigate the the game fragment, which makes the start automatically
        soundManager.changeTheme(0);
        navigateToFragment(new MainMenuFragment());
    }

    public boolean getResult(){
        return result;
    }

    public void setSelectedShip(int ship){
        selectedShip = ship;
    }

    public int getSelectedShip(){
        return selectedShip;
    }

    public void onPause() {
        super.onPause();
        soundManager.pauseMusic();
    }

    public void onResume() {
        super.onResume();
        soundManager.resumeMusic();
    }

    private void navigateToFragment(BaseFragment dst) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, dst, TAG_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        final BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (fragment == null || !fragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void navigateBack() {
        // Do a push on the navigation history
        super.onBackPressed();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE);
            }
            else {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }

}
