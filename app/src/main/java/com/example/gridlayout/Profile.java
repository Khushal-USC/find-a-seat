//base imports
package com.example.gridlayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//other imports under here

public class Profile extends ProfileSubActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.profile);
        super.onCreate(savedInstanceState);
        System.out.println();
        // Additional initialization and logic for your activity
    }
}

