//base imports
package com.example.gridlayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//other imports under here

//public class ProfileActivity extends ProfileSubActivity{
public class ProfileActivity extends AppCompatActivity {
    AppCompatActivity this_class = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        // Additional initialization and logic for your activity
        TextView leftTab = findViewById(R.id.l_tab);
        TextView rightTab = findViewById(R.id.r_tab);

        // Add click listeners to the TextViews
        leftTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click on the left tab
                // For example, switch to a different tab or perform some action
                // Here, we'll show a toast message as an example
                System.out.println("LEFT TAB CLICKED");
                Intent intent = new Intent(this_class, Map.class);
                this_class.startActivity(intent);
            }
        });

        rightTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click on the right tab
                // For example, switch to a different tab or perform some action
                // Here, we'll show a toast message as an example
                System.out.println("RIGHT TAB CLICKED");
                Intent intent = new Intent(this_class, ProfileActivity.class);
                this_class.startActivity(intent);
            }
        });
    }
}

