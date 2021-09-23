package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.quizapp.Controller.StopSound;

public class PlayScreen extends AppCompatActivity implements  View.OnClickListener{
    Button btPlay , btSettings;
    public static Context context;
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);
        btPlay= findViewById(R.id.bt_play);
        btSettings=findViewById(R.id.bt_set);
        btSettings.setOnClickListener(this);
        btPlay.setOnClickListener(this);
        context=getApplicationContext();
        Controller.currentActivity=this;
        if(SettingsPreferences.getMusicEnableDisable(context)){
            try {
                Controller.playMusic();
            }catch(IllegalStateException e){
                e.printStackTrace();
            }
        }

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_play:
                Intent playIntent=new Intent(PlayScreen.this,MainActivity.class);
                startActivity(playIntent);
                break;
            case R.id.bt_set:
                Intent setIntent=new Intent(PlayScreen.this,Settings.class);
                startActivity(setIntent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        StopSound();
        finish();
    }
}