package com.example.gridlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class ReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        LinearLayout container = findViewById(R.id.container);

        for (int i = 0; i < 20; i++) { // Adjust the number of squares as needed
            View squareItem = LayoutInflater.from(this).inflate(R.layout.square_item, null);
            // Set the width and height of each square item
            squareItem.setLayoutParams(new FrameLayout.LayoutParams(100, 100)); // Adjust the size as needed
            container.addView(squareItem);
        }
    }
}
