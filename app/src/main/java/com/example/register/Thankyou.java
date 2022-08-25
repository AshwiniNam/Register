package com.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Thankyou extends AppCompatActivity {
    Button logout;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

        logout = findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();

        TextView addnewentry = (TextView) findViewById(R.id.newentry);
        addnewentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Thankyou.this,Form.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(Thankyou.this,MainActivity.class));
        });
    }



}