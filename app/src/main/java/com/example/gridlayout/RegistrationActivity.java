package com.example.gridlayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private final int GALLERY_REQ_CODE = 1000;
    ImageView imgGallery;

    Spinner spinner;
    String[] items = {"Select:", "Undergraduate", "Graduate", "Faculty", "Staff"};

    private EditText emailInput;
    private EditText passwordInput;
    private EditText nameInput;
    private EditText idInput;

    private Button submission;
    private Button goBack;

    private TextView errorEmail;
    private TextView errorPassword;
    private TextView errorName;
    private TextView errorID;
    private TextView errorAffiliation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        imgGallery = findViewById(R.id.imageView);
        Button buttonGallery = findViewById(R.id.button);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        emailInput = findViewById(R.id.editTextText);
        passwordInput = findViewById(R.id.editTextText4);
        nameInput = findViewById(R.id.editTextText5);
        idInput = findViewById(R.id.editTextText6);

        submission = findViewById(R.id.button2);
        goBack = findViewById(R.id.button9);

        errorEmail = findViewById(R.id.textView19);
        errorPassword = findViewById(R.id.textView20);
        errorName = findViewById(R.id.textView21);
        errorID = findViewById(R.id.textView22);
        errorAffiliation = findViewById(R.id.textView23);

        errorEmail.setTextColor(Color.WHITE);
        errorPassword.setTextColor(Color.WHITE);
        errorName.setTextColor(Color.WHITE);
        errorID.setTextColor(Color.WHITE);

        AppCompatActivity this_class = this;

        buttonGallery.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);

            }
        });

        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(this_class,Map.class);
                this_class.startActivity(intent);
            }
        });

        submission.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String grabEmail = emailInput.getText().toString();
                String grabPassword = passwordInput.getText().toString();
                String grabName = nameInput.getText().toString();
                String grabID = idInput.getText().toString();

                if(!checkPasswordGood(grabPassword)){
                    errorPassword.setText("Invalid Password Input");
                    errorPassword.setTextColor(Color.RED);
                }else{
                    errorPassword.setTextColor(Color.WHITE);
                }

                if(grabID.length() == 10 && grabID.matches("\\d+")){
                    errorID.setTextColor(Color.WHITE);
                }else{
                    errorID.setText("Invalid ID");
                    errorID.setTextColor(Color.RED);
                }

                if(grabName.length() == 0){
                    errorName.setText("Invalid Input");
                    errorName.setTextColor(Color.RED);
                }else{
                    errorName.setTextColor(Color.WHITE);
                }

                if(grabEmail.endsWith("@usc.edu")){
                    errorEmail.setTextColor(Color.WHITE);
                }else{
                    errorEmail.setText("Invalid Email");
                    errorEmail.setTextColor(Color.RED);
                }

                if((String) spinner.getSelectedItem() == "Select:"){
                    errorAffiliation.setText("Please Select An Affiliation");
                    errorAffiliation.setTextColor(Color.RED);
                }else{
                    errorAffiliation.setTextColor(Color.WHITE);
                }
                Intent intent = new Intent(this_class,Map.class);
                this_class.startActivity(intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            if(requestCode == GALLERY_REQ_CODE){
                imgGallery.setImageURI(data.getData());
            }

        }
    }

}
