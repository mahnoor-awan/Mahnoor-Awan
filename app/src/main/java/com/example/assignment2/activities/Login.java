package com.example.assignment2.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment2.R;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {
    FirebaseAuth user;
    String email,password;
    EditText user_email, user_password;
    TextView signup;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login7);
    }
}