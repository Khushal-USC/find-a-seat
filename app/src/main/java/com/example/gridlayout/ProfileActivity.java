//base imports
package com.example.gridlayout;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//other imports under here

public class ProfileActivity extends ProfileSubActivity{
//public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        // Additional initialization and logic for your activity
    }
}

