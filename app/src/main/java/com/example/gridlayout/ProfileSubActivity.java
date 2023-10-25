package com.example.gridlayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileSubActivity extends NavBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setContentView(R.layout.two_tab); // Replace with the correct layout resource name
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.tab_profile);
        super.onCreate(savedInstanceState);
    }
}