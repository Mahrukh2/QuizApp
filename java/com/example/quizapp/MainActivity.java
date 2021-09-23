package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttona,buttonb,buttonc,buttond;
    TextView quest,totalQ,timer,coin,correct,wrong;

    QuizHelper quizHelper;
    Question currentQuestion;
    List<Question> list;

    int qid=1;
    int sizeofQuiz=6;
    private final Handler handler= new Handler();
    private final Handler handler2= new Handler();
    CountDownTimer countDownTimer;
    int timeValue=30;
    private  TimerDialog timerDialog;
    int corr=0;
    int wr=0;
    int coins=0;

    int FLAG = -1;
    PlayAudio playAudio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quest=findViewById(R.id.quest);
        totalQ=findViewById(R.id.totalQ);
        buttona=findViewById(R.id.buttona);
        buttonb=findViewById(R.id.buttonb);
        buttonc=findViewById(R.id.buttonc);
        buttond=findViewById(R.id.buttond);
        timer=findViewById(R.id.timer);
        coin=findViewById(R.id.coin);
        correct=findViewById(R.id.correct);
        wrong=findViewById(R.id.wrong);

       timerDialog=new TimerDialog(this);

       playAudio=new PlayAudio(this);

        quizHelper = new QuizHelper(this);
        quizHelper.getReadableDatabase();
        list=quizHelper.getAllQuestions();
        Collections.shuffle(list);
        currentQuestion = list.get(qid);
        totalQ.setText(qid + "/" + sizeofQuiz);
        correct.setText(String.valueOf(corr));
        wrong.setText(String.valueOf(wr));
        coin.setText(String.valueOf(coins));

        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(timeValue));
                timeValue =timeValue-1;
                if (timeValue == -1) {
                    disableOptions();
                    FLAG=3;
                    playAudio.setAudioForEvent(FLAG);
                    timerDialog.timerDialog();

                }

            }

            @Override
            public void onFinish() {
             timerDialog.timerDialog();
            }
        }.start();
        updateQueAnsOptions();
    }
    private void updateQueAnsOptions() {

      buttona.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.buttonBg));
        buttonb.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.buttonBg));
        buttonc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.buttonBg));
        buttond.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.buttonBg));

        quest.setText(currentQuestion.getQuestion());
        buttona.setText(currentQuestion.getOption1());
        buttonb.setText(currentQuestion.getOption2());
        buttonc.setText(currentQuestion.getOption3());
        buttond.setText(currentQuestion.getOption4());
        countDownTimer.cancel();
        countDownTimer.start();
    }
        public void SetNewQuestion(){
        qid++;
          totalQ.setText(qid + "/" + sizeofQuiz);
          currentQuestion = list.get(qid);
          enableOptions();
          updateQueAnsOptions();

    }

   private void correctTextUpdate(int corr){
        correct.setText(String.valueOf(corr));

   }
   private void wrongTextUpdate(int wr){
        wrong.setText(String.valueOf(wr));
   }
   private void coinsUpdateText(int coins){
        coin.setText(String.valueOf(coins));
   }


    public void buttona(View view) {

        countDownTimer.cancel();
        disableOptions();

        handler.postDelayed(() -> {
            if(currentQuestion.getOption1().equals(currentQuestion.getAnswerNr())){
                buttona.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                corr++;
                correctTextUpdate(corr);

                FLAG=1;
                playAudio.setAudioForEvent(FLAG);
                coins=coins+10;
                coinsUpdateText(coins);
                Log.i("QuizInfo","Correct");
                handler2.postDelayed(() -> {
                   if(qid !=sizeofQuiz){
                       SetNewQuestion();
                   }else{
                    finalResult();

                   }
                },1000);
            }else{
                buttona.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                wr++;
                wrongTextUpdate(wr);
                FLAG=2;
                playAudio.setAudioForEvent(FLAG);
              Handler handler3=new Handler();
                handler3.postDelayed(() -> {
                    if(currentQuestion.getOption2().equals(currentQuestion.getAnswerNr())){
                        buttonb.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }else if (currentQuestion.getOption3().equals(currentQuestion.getAnswerNr())){
                        buttonc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }else{
                        buttond.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }
                },1000);
                Handler handler4 = new Handler();
                handler4.postDelayed(() -> {
                    if(qid!= sizeofQuiz){
                        SetNewQuestion();
                    }else{
                    finalResult();

                    }
                },2000);
            }
        },500);
    }
    public void buttonb(View view) {
        countDownTimer.cancel();
        disableOptions();
        handler.postDelayed(() -> {
            if(currentQuestion.getOption2().equals(currentQuestion.getAnswerNr())){
                buttonb.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                corr++;
                correctTextUpdate(corr);
                FLAG=1;
                playAudio.setAudioForEvent(FLAG);
                coins=coins+10;
                coinsUpdateText(coins);
                Log.i("QuizInfo","Correct");
                handler2.postDelayed(() -> {
                    if(qid !=sizeofQuiz){
                        SetNewQuestion();
                    }else{
                   finalResult();

                    }
                },1000);
            }else{
                buttonb.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                wr++;
                wrongTextUpdate(wr);
                FLAG=2;
                playAudio.setAudioForEvent(FLAG);
                 Handler handler3=new Handler();
                handler3.postDelayed(() -> {
                    if(currentQuestion.getOption1().equals(currentQuestion.getAnswerNr())){
                        buttona.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }else if (currentQuestion.getOption3().equals(currentQuestion.getAnswerNr())){
                        buttonc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }else{
                        buttond.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }
                },1000);
                Handler handler4 = new Handler();
                handler4.postDelayed(() -> {
                    if(qid!= sizeofQuiz){
                        SetNewQuestion();
                    }else{
                      finalResult();

                    }
                },2000);
            }
        },500);
    }
    public void buttonc(View view) {
        countDownTimer.cancel();
        disableOptions();
        handler.postDelayed(() -> {
            if(currentQuestion.getOption3().equals(currentQuestion.getAnswerNr())){
                buttonc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                corr++;
                correctTextUpdate(corr);
                FLAG=1;
                playAudio.setAudioForEvent(FLAG);
                coins=coins+10;
                coinsUpdateText(coins);
                Log.i("QuizInfo","Correct");
                handler2.postDelayed(() -> {
                    if(qid !=sizeofQuiz){
                        SetNewQuestion();
                    }else{
                      finalResult();

                    }
                },1000);
            }else{
                buttonc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                wr++;
                wrongTextUpdate(wr);
                FLAG=2;
                playAudio.setAudioForEvent(FLAG);
                  Handler handler3=new Handler();
                handler3.postDelayed(() -> {
                    if(currentQuestion.getOption2().equals(currentQuestion.getAnswerNr())){
                        buttonb.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }else if (currentQuestion.getOption1().equals(currentQuestion.getAnswerNr())){
                        buttona.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }else{
                        buttond.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }
                },1000);
                Handler handler4 = new Handler();
                handler4.postDelayed(() -> {
                    if(qid!= sizeofQuiz){
                        SetNewQuestion();
                    }else{
                    finalResult();

                    }
                },2000);
            }
        },500);
    }
    public void buttond(View view) {
        countDownTimer.cancel();
        disableOptions();
        handler.postDelayed(() -> {
            if(currentQuestion.getOption4().equals(currentQuestion.getAnswerNr())){
                buttond.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                corr++;
                correctTextUpdate(corr);
                FLAG=1;
                playAudio.setAudioForEvent(FLAG);
                coins=coins+10;
                coinsUpdateText(coins);
                Log.i("QuizInfo","Correct");
                handler2.postDelayed(() -> {
                    if(qid !=sizeofQuiz){
                        SetNewQuestion();
                    }else{
                       finalResult();

                    }
                },1000);
            }else{
                buttond.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                wr++;
                wrongTextUpdate(wr);
                FLAG=2;
                playAudio.setAudioForEvent(FLAG);
                 Handler handler3=new Handler();
                handler3.postDelayed(() -> {
                    if(currentQuestion.getOption2().equals(currentQuestion.getAnswerNr())){
                        buttonb.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }else if (currentQuestion.getOption3().equals(currentQuestion.getAnswerNr())){
                        buttonc.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }else{
                        buttona.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    }
                },1000);
                Handler handler4 = new Handler();
                handler4.postDelayed(() -> {
                    if(qid!= sizeofQuiz){
                        SetNewQuestion();
                    }else{
                   finalResult();

                    }
                },2000);
            }
        },500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        countDownTimer.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
        finish();
    }
    private void disableOptions(){
        buttona.setEnabled(false);
        buttonb.setEnabled(false);
        buttonc.setEnabled(false);
        buttond.setEnabled(false);
    }
    private void enableOptions(){
        buttona.setEnabled(true);
        buttonb.setEnabled(true);
        buttonc.setEnabled(true);
        buttond.setEnabled(true);
    }
    private void finalResult(){
        Intent resultIntent = new Intent(MainActivity.this,Result.class);
        resultIntent.putExtra(Constants.TOTAL_QUESTIONS,sizeofQuiz);
        resultIntent.putExtra(Constants.COINS,coins);
        resultIntent.putExtra(Constants.WRONG,wr);
        resultIntent.putExtra(Constants.CORRECT,corr);

        startActivity(resultIntent);
    }
}
