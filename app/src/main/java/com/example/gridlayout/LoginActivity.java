package com.example.gridlayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private boolean passError = false;
    private boolean emailError = false;
    private EditText emailText;
    private EditText passwordText;

    private Button submitter;
    private Button goBack;

    private TextView error;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        emailText = findViewById(R.id.editTextText2);
        passwordText = findViewById(R.id.editTextText3);

        submitter = findViewById(R.id.login_submit_tv);
        goBack = findViewById(R.id.login_goback_tv);

        error = findViewById(R.id.login_error_tv);
        error.setTextColor(Color.WHITE);

        AppCompatActivity this_class = this;

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(this_class,Map.class);
                this_class.startActivity(intent);
            }
        });

        submitter.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){

               String grabEmail = emailText.getText().toString();
               String grabPassword = passwordText.getText().toString();

               if(!checkPasswordGood(grabPassword)){
                   passError = true;
               }else{
                   passError = false;
               }
               if(!grabEmail.endsWith("@usc.edu")){
                   emailError = true;
               }else{
                   emailError = false;
               }

               if(passError && !emailError){
                   error.setText("Invalid Password");
                   error.setTextColor(Color.RED);
               }else if(!passError && emailError){
                   error.setText("Invalid Email");
                   error.setTextColor(Color.RED);
               }else if(passError && emailError){
                   error.setText("Both Username and Password are Invalid");
                   error.setTextColor(Color.RED);
               }else{
                   error.setTextColor(Color.WHITE);
                   Intent intent = new Intent(this_class,Map.class);
                   this_class.startActivity(intent);
               }

           }
        });
    }

    boolean checkPasswordGood(String s){

        if(s.length() < 8){
            return false;
        }

        String regex = "[^a-zA-Z0-9]";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            return true;
        } else {
            return false;
        }

    }




}
