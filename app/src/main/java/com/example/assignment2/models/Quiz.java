package com.example.assignment2.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.R)

public class Quiz {

    public String id;
    public String title;
    public Map<String, Question> questions= Map.of();
    public Quiz() {
    }
    public Quiz(String id, String title, Map<String, Question> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }


    public Map<String, Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, Question> questions) {
        this.questions = questions;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}