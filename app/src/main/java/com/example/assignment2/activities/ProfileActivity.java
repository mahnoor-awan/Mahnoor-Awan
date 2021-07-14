package com.example.assignment2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment2.R;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    TextView textView;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        textView=findViewById(R.id.txtEmail);
        if(firebaseAuth.getCurrentUser()!=null){
            textView.setText(firebaseAuth.getCurrentUser().getEmail());
        }
        logout=findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(ProfileActivity.this, Login.class);
                ProfileActivity.this.startActivity(intent);
                finish();
            }
        });
    }
}