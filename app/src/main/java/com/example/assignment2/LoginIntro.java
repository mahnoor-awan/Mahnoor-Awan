package com.example.assignment2;

import android.view.View.OnClickListener;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

import com.example.assignment2.R;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;



public class LoginIntro extends AppCompatActivity {
    Button get_start;
    FirebaseAuth fireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_intro);
        fireBase = FirebaseAuth.getInstance();
        if(fireBase.getCurrentUser() != null)
        {
            Toast.makeText(LoginIntro.this,"Already logged in!",Toast.LENGTH_SHORT).show();
            redirect("MAIN");
        }
        get_start = findViewById(R.id.btnGetStarted);
        get_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect("LOGIN");
            }
        });
    }
    private void redirect(String name){
        if(name.equals("LOGIN"))
        {
            Intent intent = new Intent(LoginIntro.this, Login.class);
                startActivity(intent);
                finish();
        }
        else if (name.equals("MAIN")){
            Intent intent=new Intent(LoginIntro.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            throw new java.lang.RuntimeException("Not Bad");
        }
    }
}