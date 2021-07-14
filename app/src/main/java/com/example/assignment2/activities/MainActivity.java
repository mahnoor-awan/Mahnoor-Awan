package com.example.assignment2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.R;
import com.example.assignment2.adapters.QuizAdapter;
import com.example.assignment2.models.Quiz;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle drawer;
    NavigationView navigationView;
    FloatingActionButton datepicker;
    QuizAdapter adapter;
    FirebaseFirestore db;
    ArrayList<Quiz> data = new ArrayList<>();
    RecyclerView rc_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc_view = findViewById(R.id.quizRecyclerView);
        datepicker=findViewById(R.id.btnDatePicker);
        setup_Views();

    }

    void setup_Views() {
        setUpFirestore();
        setUpDrawlayout();
        setUpRecyclerView();
        setUpDatePicker();
    }
    private void  setUpDatePicker(){
        datepicker.setOnClickListener(v -> {
            MaterialDatePicker<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker().build();
            materialDateBuilder.show(getSupportFragmentManager(),"DatePicker");
            materialDateBuilder.addOnPositiveButtonClickListener(selection -> {
                Log.d("DatePicker","postive"+ materialDateBuilder.getHeaderText());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String date = dateFormat.format(new Date(selection));
                Log.d("DatePicker", "Format"+date);
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("DATE", date);
                MainActivity.this.startActivity(intent);
                finish();
            });
            materialDateBuilder.addOnNegativeButtonClickListener(selection -> {
                Log.d("DatePicker","date neg" +materialDateBuilder.getHeaderText());
            });
            materialDateBuilder.addOnCancelListener(selection -> {
                Log.d("DatePicker", "date cancel"+"Date Picker Cancelled");
            });
        });
    }
    private  void setUpFirestore(){
        db=FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("quizzes");
        collectionReference.addSnapshotListener((value, error) -> {
            if(value == null || error!=null){
                Log.d("mahnoor", error.getMessage());
                return;
            }
            data.clear();
            data.addAll(value.toObjects(Quiz.class));
            adapter.notifyDataSetChanged();
        });
    }


    private void setUpRecyclerView() {
        adapter = new QuizAdapter(this, data);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rc_view.setAdapter(adapter);
        rc_view.setLayoutManager(gridLayoutManager);
    }

    public void setUpDrawlayout() {
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(item -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            MainActivity.this.startActivity(intent);
            finish();
            return true;
        });
        setSupportActionBar(findViewById(R.id.appBar));
        drawer = new ActionBarDrawerToggle(MainActivity.this, findViewById(R.id.mainDrawer), R.string.app_name, R.string.app_name);
        drawer.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawer.onOptionsItemSelected(item)) {
            return (true);
        }
        return super.onOptionsItemSelected(item);
    }
}