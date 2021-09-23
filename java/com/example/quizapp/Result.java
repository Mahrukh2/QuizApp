package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    Button btPlayAgain,btPlayScreen;
    TextView totalQuestions,TotalCoins,WrongQuestions,CorrectQuestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btPlayAgain= findViewById(R.id.btplayAgainR);
        btPlayScreen=findViewById(R.id.btPlaySR);
        TotalCoins=findViewById(R.id.coinR);
        CorrectQuestions=findViewById(R.id.correctR);
        WrongQuestions=findViewById(R.id.wrongR);
        totalQuestions=findViewById(R.id.totalQR);
        Intent intent = getIntent();
        int totQuestions=intent.getIntExtra(Constants.TOTAL_QUESTIONS,0);
        int coins=intent.getIntExtra(Constants.COINS,0);
        int correct=intent.getIntExtra(Constants.CORRECT,0);
        int wrong=intent.getIntExtra(Constants.WRONG,0);

        totalQuestions.setText(Constants.TOTAL_QUESTIONS + String.valueOf(totQuestions));
        TotalCoins.setText(Constants.COINS + String.valueOf(coins));
       CorrectQuestions.setText(Constants.CORRECT + String.valueOf(correct));
       WrongQuestions.setText(Constants.WRONG + String.valueOf(wrong));

       btPlayScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this,PlayScreen.class);
                startActivity(intent);
            }
        });
        btPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}