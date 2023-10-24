package com.example.gridlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the layout resource
        View parentLayout = findViewById(R.id.parentView);

        // Find all the buttons by their IDs
        Button button1 = findViewById(R.id.button1);
        button1.setTag("two_tab");
        Button button2 = findViewById(R.id.button2);
        button2.setTag("make_acc");
        Button button3 = findViewById(R.id.button3);
        button3.setTag("profile");
        Button button4 = findViewById(R.id.button4);
        button4.setTag("map");
        Button button5 = findViewById(R.id.button5);
        button5.setTag("reservation");
        Button button6 = findViewById(R.id.button6);
        button6.setTag("login");
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);

        // Set a single OnClickListener for all buttons
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(" Clicked! ID: " + v.getTag());
                int layoutId = getResources().getIdentifier((String) v.getTag(), "layout", getPackageName());

                if (layoutId != 0) {
                    // The layout ID was found
                    setContentView(layoutId); // Set the content view to the layout
                } else {
                    System.out.println("layout not found");
                }
            }
        };
        // Apply the same click listener to all buttons
        button1.setOnClickListener(buttonClickListener);
        button2.setOnClickListener(buttonClickListener);
        button3.setOnClickListener(buttonClickListener);
        button4.setOnClickListener(buttonClickListener);
        button5.setOnClickListener(buttonClickListener);
        button6.setOnClickListener(buttonClickListener);
        button7.setOnClickListener(buttonClickListener);
        button8.setOnClickListener(buttonClickListener);

    }
}
