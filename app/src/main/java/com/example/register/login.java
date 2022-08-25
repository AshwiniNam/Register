package com.example.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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


public class login extends AppCompatActivity {

    TextView emailid;
    TextView firstname;
    TextView secondname;
    TextView signuppwd;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firstname = findViewById(R.id.firstname);
        secondname = findViewById(R.id.secondname);
        emailid = findViewById(R.id.emailid);
        signuppwd = findViewById(R.id.signuppwd);

        MaterialButton signinbutton = (MaterialButton) findViewById(R.id.signupbutton);
        TextView alreadyamem = (TextView)findViewById(R.id.alreadyamember);
        mAuth = FirebaseAuth.getInstance();
        signinbutton.setOnClickListener(view -> {
            createUser();
        });
        alreadyamem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }



    private void createUser(){
        String regemail = emailid.getText().toString();
        String reggpwd = signuppwd.getText().toString();

        //error if email is empty
        if(TextUtils.isEmpty(regemail)){
            emailid.setError("Email cannot be empty");
            emailid.requestFocus();
        }
        else if(TextUtils.isEmpty(reggpwd)){
            signuppwd.setError("Password cannot be empty");
            signuppwd.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(regemail,reggpwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(login.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(login.this, "Registeration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}