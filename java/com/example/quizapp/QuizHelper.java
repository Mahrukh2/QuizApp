package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.quizapp.QuizContract.*;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuizHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quiz.db";
    private SQLiteDatabase db;
    QuizHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " TEXT " +
                " ) ";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
        return;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillQuestionsTable() {
        Question q1 = new Question("Who was the inventor of Ctrl+C (copy), Ctrl+V (Paste ) and Ctrl+X (Cut)?", "Bill Gates", "Larry Tesler", "Christopher Latham Sholes", "David Sundstrand", "Larry Tesler");
        addQuestions(q1);
        Question q2 = new Question("Which of the following countries has the largest area in the world?", "Canada", "China", "USA", "Russia", "Russia");
        addQuestions(q2);
        Question q3 = new Question("The tallest tree in the world is", "Cedar", "Redwood", "Eucalyptus", "Date palm", "Redwood");
        addQuestions(q3);
        Question q4 = new Question("Name the river in the world which carries the maximum volume of water", "Amazon", "Nile", "Mississippi", "Indus", "Amazon");
        addQuestions(q4);
        Question q5 = new Question("First computer virus is known as", "Rabbit", "Creeper Virus", "Elk Cloner", "SCA Virus", "Creeper Virus");
        addQuestions(q5);
        Question q6 = new Question("Which flower is national flower of Pakistan", "Jasmine", "Rose", "Sun Flower", "None f these", "Jasmine");
        addQuestions(q6);
        Question q7 = new Question("Which is the smallest country in the world?", "Dubai", "Monaco", "Vatican City", "Naura", "Vatican City");
        addQuestions(q7);
        Question q8 = new Question("Which is not a search Engine?", "Google", "Microsoft", "Search Bug", "Alta Vista", "Microsoft");
        addQuestions(q8);
    }
    private void addQuestions(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionTable.TABLE_NAME, null, cv);

    }
    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        db=getReadableDatabase();

        String Projection[] = {
                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR
        };
            Cursor c = db.query(QuestionTable.TABLE_NAME,
                    Projection,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        if(c.moveToFirst())
        {
            do{
                Question question=new  Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getString(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            }while(c.moveToNext()); }
        c.close();
        return questionList;
    }
}
