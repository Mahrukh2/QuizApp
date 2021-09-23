package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import static com.example.quizapp.Controller.StopSound;

public class Settings extends AppCompatActivity {
    private Context mContext;
    private Switch mMusicCheckBox;
    private Button ok_btn;
    private boolean isMusicOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mContext=Settings.this;
        Controller.currentActivity = this;
        initViews();
        populateMusicEnableContents();
    }
    private void initViews() {
        mMusicCheckBox = findViewById(R.id.switch1);
        mMusicCheckBox.setChecked(true);
        ok_btn=findViewById(R.id.okS);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTOPlayScreen();
            }
        });
    }
    private void goTOPlayScreen(){
        Intent intentPlayScreen = new Intent(Settings.this,PlayScreen.class);
        startActivity(intentPlayScreen);
    }
    public void viewClickHandler(View view ){
        switch(view.getId()){
            case R.id.switch1:
                switchMusicEnableCheckbox();
                break;
        } }
        private void switchMusicEnableCheckbox() {
        isMusicOn= !isMusicOn;
        if(isMusicOn){
            SettingsPreferences.setMusicEnableDisable(mContext,true);
            Controller.playMusic();
        }else{
            SettingsPreferences.setMusicEnableDisable(mContext,false);
            StopSound();
        }
        populateMusicEnableContents();
    }
    protected void populateMusicEnableContents(){
        if(SettingsPreferences.getMusicEnableDisable(mContext)){
            Controller.playMusic();
            mMusicCheckBox.setChecked(true);
        }else{
            StopSound();
            mMusicCheckBox.setChecked(false);
        }
        isMusicOn=SettingsPreferences.getMusicEnableDisable(mContext);
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish(); }
        @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

}