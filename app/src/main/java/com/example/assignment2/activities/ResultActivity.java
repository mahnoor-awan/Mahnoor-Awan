package com.example.assignment2.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.TextView;

import com.example.assignment2.R;
import com.example.assignment2.models.Question;
import com.example.assignment2.models.Quiz;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@RequiresApi(api = Build.VERSION_CODES.R)

public class ResultActivity extends AppCompatActivity {
    Map<String, Question> questions=Map.of();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView=findViewById(R.id.txtScore);
        setUpViews();
    }

    private void setUpViews() {
        String quizData= getIntent().getStringExtra("QUIZ");
        Gson gson = new Gson();
        Quiz[] quiz=gson.fromJson(quizData,Quiz[].class);
        questions=quiz[0].questions;
        calculateScore();
        setAnwserView();
    }

    private void setAnwserView() {
        StringBuilder stringBuilder = new StringBuilder();
        int index=1;
        TextView textans;
        textans=findViewById(R.id.txtAnswer);
        for(int i=0; i<questions.size(); i++){
            Question question=questions.get("question"+index);
            index++;
            stringBuilder.append("<font color '#18206f'><b>Question<br/>"+question.description+"</b></font><br/><br/>");
            stringBuilder.append("<font color '#18206f'>Answer<br/>"+question.answer+"</font><br/><br/><br/>");
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            textans.setText(Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_COMPACT));
        }
        else{
            textans.setText(Html.fromHtml(stringBuilder.toString()));
        }

    }

    private void calculateScore() {
        int score=0;
        int index=1;
        for(int i=0; i<questions.size(); i++){
            Question question = questions.get("question"+index);
            index++;
            if(question.answer.equals(question.user_answer)){
                score+=10;
            }
        }
        textView.setText("Your Score: "+score);
    }
}