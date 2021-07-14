package com.example.assignment2.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment2.R;
import com.example.assignment2.activities.MainActivity;
import com.example.assignment2.activities.ResultActivity;
import com.example.assignment2.adapters.OptionAdapter;
import com.example.assignment2.models.Question;
import com.example.assignment2.models.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiresApi(api = Build.VERSION_CODES.R)
public class QuestionActivity extends AppCompatActivity {
    Question question;
    Button next,prev,submit;
    List<Quiz> quiz=new ArrayList<>();
    FirebaseFirestore db;
    Map<String, Question> questions=Map.of();
    OptionAdapter optionAdapter;
    RecyclerView recyclerViewl;
    int index=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        recyclerViewl=findViewById(R.id.optionList);
        next=findViewById(R.id.btnNext);
        prev=findViewById(R.id.btnPrevious);
        submit=findViewById(R.id.btnSubmit);

        setUpFireStore();
        setUpEventLister();

    }

    private void setUpEventLister() {
        prev.setOnClickListener(v -> {
            index--;
            bindViews();
        });
        next.setOnClickListener(v -> {
            index++;
            bindViews();
        });
        submit.setOnClickListener(v -> {
            Log.d("FINAL", questions.toString());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json=gson.toJson(quiz);

            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            intent.putExtra("QUIZ", json);
            QuestionActivity.this.startActivity(intent);
            finish();
        });
    }

    private void setUpFireStore(){

        db= FirebaseFirestore.getInstance();
        String date= getIntent().getStringExtra("DATE");
        if(date!=null){
            db.collection("quizzes")
                    .whereEqualTo("title", date)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if(queryDocumentSnapshots!=null && !queryDocumentSnapshots.isEmpty()){
                                quiz.addAll(queryDocumentSnapshots.toObjects(Quiz.class));
                                questions = quiz.get(0).questions;
                                bindViews();
                            }
                            else{
                                Toast.makeText(QuestionActivity.this,"No Quiz for Given Date",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                                QuestionActivity.this.startActivity(intent);
                                finish();
                            }
                        }
                    });
        }

    }


    private void bindViews(){


        next.setVisibility(View.GONE);
        prev.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        if(index==1){
            next.setVisibility(View.VISIBLE);
        }
        else if(index==questions.size()) {
            submit.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
        }
        else{
            next.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
        }
        Question question = questions.get("question"+index);
        TextView discp=findViewById(R.id.description);
        discp.setText(question.description);
        Log.d("ZIA", question.description);
        optionAdapter= new OptionAdapter(this,question);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerViewl.setLayoutManager(layoutManager);
        recyclerViewl.setAdapter(optionAdapter);
        recyclerViewl.setHasFixedSize(true);

    }



}