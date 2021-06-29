package com.botarmy.tourwala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.botarmy.tourwala.Utility.NetworkChangeListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Cycle extends AppCompatActivity {
    //Network change checking
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    private FusedLocationProviderClient client;
    private SupportMapFragment mapFragment;
    private int REQUEST_CODE = 111;
    private ConnectivityManager manager;
    private NetworkInfo networkInfo;
    private GoogleMap mMap;
    private Geocoder geocoder;
    private double selectedLat,selectedLng;
    private List<Address> addresses;
    private String selectedAddress;
//private button btnSave, btnRoute, btnHist;

    private String showRoute = null;
    private int myId;
    private Button btnRoute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);

        btnRoute = findViewById(R.id.btnRoute);

        client = LocationServices.getFusedLocationProviderClient(Cycle.this);

        if (ActivityCompat.checkSelfPermission(Cycle.this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            getCurrentLocation();

        }
        else{
            ActivityCompat.requestPermissions(Cycle.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
        }

    btnRoute.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "google.navigation:q="+selectedLat+","+selectedLng+"&mode=d";
            Intent mapIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            mapIntent.setPackage("com.google.android.apps.maps");
            if(mapIntent.resolveActivity(getPackageManager()) != null){

                startActivity(mapIntent);
            }
        }
    });


    }

    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location != null){

                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {

                            mMap = googleMap;

                            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You are here");

                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));

                            googleMap.addMarker(markerOptions).showInfoWindow();

                            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                @Override
                                public void onMapClick(@NonNull LatLng latLng) {
                                    CheckConnection();

                                    if(networkInfo.isConnected() && networkInfo.isAvailable()){

                                        selectedLat = latLng.latitude;
                                        selectedLng = latLng.longitude;


                                        GetAddress(selectedLat,selectedLng);

                                        btnRoute.setVisibility(View.VISIBLE);



                                    }else {
                                        Toast.makeText(Cycle.this, "Please Check Connection", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });
    }


    private void GetAddress(double mLat, double mLng) {
        geocoder = new Geocoder(Cycle.this, Locale.getDefault());

        if (mLat != 0 ) {
            try {
                addresses = geocoder.getFromLocation(mLat, mLng, 1);
            } catch (IOException e){
                e.printStackTrace();
            }

            if (addresses != null){
                String mAddress = addresses.get(0).getAddressLine(0);

                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();
                String dis = addresses.get(0).getSubAdminArea();

                selectedAddress = mAddress;

                if(mAddress != null){
                    MarkerOptions markerOptions = new MarkerOptions();

                    LatLng latLng = new LatLng(mLat,mLng);


                    markerOptions.position(latLng).title(selectedAddress);

                    mMap.addMarker(markerOptions).showInfoWindow();
                }
                else{
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(this, "LatLng null", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }

    private void CheckConnection(){
        manager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        networkInfo  = manager.getActiveNetworkInfo();
    }

//    private void GetAddress(double mLat,double mLng) throws IOException {
//        geocoder = new Geocoder(Cycle.this, Locale.getDefault());
//
//        if (mLat != 0 ) {
//            try {
//                addresses = geocoder.getFromLocation(mLat, mLng, 1);
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//
//            if (addresses != null){
//                String mAddress = addresses.get(0).getAddressLine(0);
//
//                String city = addresses.get(0).getLocality();
//                String state = addresses.get(0).getAdminArea();
//                String country = addresses.get(0).getCountryName();
//                String postalCode = addresses.get(0).getPostalCode();
//                String knownName = addresses.get(0).getFeatureName();
//                String dis = addresses.get(0).getSubAdminArea();
//
//                selectedAddress = mAddress;
//
//                if(mAddress != null){
//                    MarkerOptions markerOptions = new MarkerOptions();
//
//                    LatLng latLng = new LatLng(mLat,mLng);
//
//                    markerOptions.position(latLng).title(selectedAddress);
//
//                    mMap.addMarker(markerOptions).showInfoWindow();
//                }
//                else{
//                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//            }
//            else {
//                Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//        else{
//            Toast.makeText(this, "LatLng null", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}