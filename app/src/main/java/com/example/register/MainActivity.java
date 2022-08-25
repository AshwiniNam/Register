package com.example.register;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView username;
    TextView password;
    TextView createnewacc;
    MaterialButton loginbutton;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbutton = (MaterialButton)findViewById(R.id.loginbutton);
        createnewacc = (TextView)findViewById(R.id.createanewaccount);

        createnewacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        loginbutton.setOnClickListener(view -> {
            loginUser();
        });

    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user  = mAuth.getCurrentUser();
//        if(user == null){
//            startActivity(new Intent(MainActivity.this,login.class));
//        }
//    }
    private void loginUser(){
        String loginemail = username.getText().toString();
        String logingpwd = password.getText().toString();

        //error if email is empty
        if(TextUtils.isEmpty(loginemail)){
            username.setError("Email cannot be empty");
            username.requestFocus();
        }
        else if(TextUtils.isEmpty(logingpwd)){
            password.setError("Password cannot be empty");
            password.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(loginemail,logingpwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,Form.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Login Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}