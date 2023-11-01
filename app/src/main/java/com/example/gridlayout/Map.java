package com.example.gridlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import android.os.Bundle;
import android.widget.ImageView;
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Map extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap myMap;
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
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap bitmap = null;

            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                // Display the downloaded image in the ImageView
                imageView.setImageBitmap(result);
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        LatLng Fertitta = new LatLng(34.0187, -118.2824);
        LatLng Leavey = new LatLng(34.02166,-118.28263);
        LatLng Doheny = new LatLng(34.02016,-118.28377);
        myMap.addMarker(new MarkerOptions().position(Fertitta).title("Fertitta"));
        myMap.addMarker(new MarkerOptions().position(Leavey).title("Leavey"));
        myMap.addMarker(new MarkerOptions().position(Doheny).title("Doheny"));

        myMap.moveCamera(CameraUpdateFactory.newLatLng(Leavey));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(Fertitta));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(Doheny));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(Fertitta);
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
                ImageView popupImage = customView.findViewById(R.id.imageView3);

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

                // Define the image URL (replace with your actual image URL)
                String dohenny = "https://kckarchitects.com/wp-content/uploads/2015/09/doheny.jpg";
                String leviathan = "https://libraries.usc.edu/sites/default/files/styles/16_9_large_2x/public/2019-07/leavey-dsc_0106.jpg?itok=5SPgA4w-";
                String fertits = "https://today.usc.edu/wp-content/uploads/2016/09/Fertitta_toned_web2-1280x720.jpg";

                // Use an AsyncTask to download the image and display it in the ImageView
                if(popupTitle.getText().equals("Doheny")){
                    new DownloadImageTask(popupImage).execute(dohenny);
                }
                else if(popupTitle.getText().equals("Leavey")){
                    new DownloadImageTask(popupImage).execute(leviathan);
                }
                else if(popupTitle.getText().equals("Fertitta")){
                    new DownloadImageTask(popupImage).execute(fertits);
                }


                // Show the popup menu anchored to the marker's position
                popupWindow.showAtLocation(mapFragment.getView(), Gravity.CENTER, 0, 0);

                return true;
            }
        });
    }
}