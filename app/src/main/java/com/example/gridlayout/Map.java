package com.example.gridlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import android.os.Bundle;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap myMap;
    private TextView infoTextView;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemaps_layout);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    //private GoogleMap myMap;

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        LatLng TommyTrojan = new LatLng(34.0206, -118.2854);
        LatLng Leavey = new LatLng(34.02166,-118.28263);
        LatLng Doheny = new LatLng(34.02016,-118.28377);
        Marker TommyMarker = myMap.addMarker(new MarkerOptions().position(TommyTrojan).title("Tommy Trojan"));
        Marker LeaveyMarker = myMap.addMarker(new MarkerOptions().position(Leavey).title("Leavey"));
        Marker DohenyMarker = myMap.addMarker(new MarkerOptions().position(Doheny).title("Doheny"));

        myMap.moveCamera(CameraUpdateFactory.newLatLng(Leavey));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(TommyTrojan));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(Doheny));
        //myMap.getUiSettings().setZoomControlsEnabled(true);
        //myMap.getUiSettings().setCompassEnabled(true);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(TommyTrojan);
        builder.include(Leavey);
        builder.include(Doheny);
        LatLngBounds bounds = builder.build();

        int padding = 50; // Adjust the padding as needed

        // Add an OnGlobalLayoutListener to wait for map layout
        ViewTreeObserver vto = mapFragment.getView().getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Once the map layout is ready, remove the listener and set the camera to show all the markers
                mapFragment.getView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                myMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
            }
        });
        myMap.getUiSettings().setZoomControlsEnabled(true);
        myMap.getUiSettings().setCompassEnabled(true);

        //TextView view = (TextView) TommyMarker;

        myMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Create a PopupWindow with the custom layout
                View customView = getLayoutInflater().inflate(R.layout.marker_popup_layout, null);
                PopupWindow popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

                // Find the TextView and Buttons in the custom layout
                TextView popupTitle = customView.findViewById(R.id.popup_title);
                Button option1Button = customView.findViewById(R.id.popup_option1);
                Button option2Button = customView.findViewById(R.id.popup_option2);
                Button button2 = customView.findViewById(R.id.button2);

                // Set click listeners for the buttons in the popup menu
                option1Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle Option 1 click
                        popupWindow.dismiss(); // Close the popup menu
                    }
                });

                option2Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle Option 2 click
                        popupWindow.dismiss(); // Close the popup menu
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle Option 2 click
                        popupWindow.dismiss(); // Close the popup menu
                    }
                });

                // Update the title of the popup menu
                popupTitle.setText(marker.getTitle());

                // Show the popup menu anchored to the marker's position
                popupWindow.showAtLocation(mapFragment.getView(), Gravity.CENTER, 0, 0);

                return true;
            }
        });
    }
}