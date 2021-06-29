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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.botarmy.tourwala.Utility.NetworkChangeListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class restaurant extends AppCompatActivity {
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    //initialize variable
    Spinner spType;
    Button btFind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    Button btnRouteBus;
    double currentLat = 0, currentLong = 0;
    private ConnectivityManager manager;
    private NetworkInfo networkInfo;
    private List<Address> addresses;
    private String selectedAddress;
    private Geocoder geocoder;
    private double selectedLat,selectedLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Assign variables

        spType = findViewById(R.id.sp_type);
        btFind = findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        btnRouteBus = findViewById(R.id.btnRoutebus);


        //Initialize array of place type
//        String[] placeTypeList = {"atm", "bank", "hospital", "movie_theater", "restaurant"};
        String[] placeTypeList = {"restaurants", "restaurant_places"};

        //Initialize array of place name
//        String[] placeNameList = {"ATM", "Bank", "Hospital", "Movie_Theater", "Restaurant"};
        String[] placeNameList = {"RESTAURANTS", "RESTAURANTS_PLACES"};

        //set adapter on spinner
        spType.setAdapter(new ArrayAdapter<>(restaurant.this, android.R.layout.simple_spinner_dropdown_item, placeNameList));

        //Initialize fused Location Provider Client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //Check permission
        if (ActivityCompat.checkSelfPermission(restaurant.this
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //when permission granted
            //call method
            getCurrentLocation();
        } else {
            //When permission denied
            //Request permission
            ActivityCompat.requestPermissions(restaurant.this
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        btFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get selected position of spinner
                int i = spType.getSelectedItemPosition();
                //Initialize url
                String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" + //url
                        "?location=" + currentLat + "," + currentLong + //Location Latitude and Longitude
                        "&radius=7000" + //Nearby radius
                        "&types=" + placeTypeList[i] + //Place type
                        "&sensor=true" + //Sensor
                        "&key=" + getResources().getString(R.string.map_key); //Google map key

                //Execute place task  method to download json data
                new restaurant.PlaceTask().execute(url);
            }
        });


        btnRouteBus.setOnClickListener(new View.OnClickListener() {
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

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

    private void getCurrentLocation() {
        //Initialize task location

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
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //when success
                if(location != null){
                    //when location is not equal to null
                    //Get current latitude
                    currentLat = location.getLatitude();
                    //Get current longitude
                    currentLong = location.getLongitude();



                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            //When map is ready
                            map = googleMap;

                            LatLng latLng = new LatLng(currentLat,currentLong);


                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You are here");

                            //Zoom current Location on map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(currentLat,currentLong),17
                            ));
                            googleMap.addMarker(markerOptions).showInfoWindow();
                            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                @Override
                                public void onMapClick(@NonNull LatLng latLng) {
                                    CheckConnection();

                                    if(networkInfo.isConnected() && networkInfo.isAvailable()){

                                        selectedLat = latLng.latitude;
                                        selectedLng = latLng.longitude;


                                        GetAddress(selectedLat,selectedLng);

                                        btnRouteBus.setVisibility(View.VISIBLE);



                                    }else {
                                        Toast.makeText(restaurant.this, "Please Check Connection", Toast.LENGTH_SHORT).show();
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
        geocoder = new Geocoder(restaurant.this, Locale.getDefault());

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

                    map.addMarker(markerOptions).showInfoWindow();
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

    private void CheckConnection() {
        manager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        networkInfo  = manager.getActiveNetworkInfo();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //When permission granted
                //call method
                getCurrentLocation();
            }
        }else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    private String downloadUrl(String string) throws IOException {
        //Initialize url
        URL url = new URL(string);
        //Initialize connection
        HttpURLConnection connection =(HttpURLConnection) url.openConnection();
        //Connect connection
        connection.connect();
        //Initialize input stream
        InputStream stream = connection.getInputStream();
        //Initialize buffer reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        //initialize string builder
        StringBuilder builder = new StringBuilder();
        //Initialize string variable
        String line = "";
        //Use while loop
        while ((line = reader.readLine()) != null ){
            //Append Line
            builder.append(line);
        }
        //Get append data
        String data = builder.toString();
        //Close Reader
        reader.close();
        //Return data
        return data;




    }

    private class PlaceTask extends AsyncTask<String,Integer,String> {


        @Override
        protected String doInBackground(String... strings) {
            String data = null;

            try {
                //Initialize data
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            //Execute parser task
            new restaurant.ParserTask().execute(s);
        }
    }

    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>> {
        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            //Create json parser class
            JsonParser jsonParser = new JsonParser();
            //Initialize hash map list
            List<HashMap<String,String>> mapList = null;
            JSONObject object = null;
            try {
                //Initialize  json object
                object = new JSONObject(strings[0]);
                //Parse json object
                mapList = jsonParser.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Return map list
            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            //Clear map
            map.clear();
            //use for loop
            for(int i=0; i<hashMaps.size(); i++){
                //Initialize hash map
                HashMap<String,String> hashMapList = hashMaps.get(i);
                //Get Latitude
                double lat = Double.parseDouble(hashMapList.get("lat"));
                //Get Longitude
                double lng = Double.parseDouble((hashMapList.get("lng")));
                //Get name
                String name = hashMapList.get("name");
                //Concat longitude and latitude
                LatLng latLng = new LatLng(lat,lng);
                //Initialize marker options
                MarkerOptions options = new MarkerOptions();
                //Set Position
                options.position(latLng);
                //Set title
                options.title(name);
                //Add marker on map
                map.addMarker(options);

            }
        }
    }
}