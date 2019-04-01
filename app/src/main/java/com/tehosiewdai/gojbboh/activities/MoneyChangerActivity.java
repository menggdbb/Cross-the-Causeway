package com.tehosiewdai.gojbboh.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.entity.MoneyChanger;
import com.tehosiewdai.gojbboh.utilities.MoneyChangerAsyncTask;

import java.util.ArrayList;


public class MoneyChangerActivity extends FragmentActivity implements OnMapReadyCallback, OnRequestPermissionsResultCallback, MoneyChangerAsyncTask.MoneyChangerTaskCallback {

    private final String TAG = MoneyChangerActivity.class.getSimpleName();

    private GoogleMap mMap;

    private FusedLocationProviderClient mFusedLocationClient;

    private static final int LOCATION_REQUEST_CODE = 991;

    Button mapButtonClick;

    private ArrayList<Marker> mMarkerArray = new ArrayList<Marker>();

    class MyInfoWindowAdapter implements InfoWindowAdapter{

        private final View myContentsView;

        MyInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_windows, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.moneyChanger));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_changer);
//         Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mapButtonClick = findViewById(R.id.buttonMaps);



    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (permissions.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
                        }
                    }
                });
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());

        new MoneyChangerAsyncTask(this).execute();

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(12.0f);
        mMap.setMaxZoomPreference(20.0f);


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.35, 103.8), 12));

        LatLngBounds SINGAPORE = new LatLngBounds(
                new LatLng(1.152761, 103.559083), new LatLng(1.487512, 104.113698));
        mMap.setLatLngBoundsForCameraTarget(SINGAPORE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            mMap.setMyLocationEnabled(true);
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
                    }
                }
            });
        }

    }

    @Override
    public void onPreExecuteMoneyChangerTask() {
    }

    @Override
    public void onPostExecuteMoneyChangerTask(MoneyChanger[] moneyChangers) {
        for (MoneyChanger moneyChanger : moneyChangers) {
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(moneyChanger.getLatLng())
                    .title(moneyChanger.getName())
                    .snippet(moneyChanger.getPostalCode().concat(moneyChanger.getAddress())));
            mMarkerArray.add(marker);
        }
    }

    public void buttonClick(View v) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            mMap.setMyLocationEnabled(true);
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(findNearest(location), 14.0f));
                    }
                }
            });
        }

    }

    private LatLng findNearest(Location myLoc) {
        LatLng nearestLoc = new LatLng(0, 0);
        LatLng myLatLng = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
        Log.d(TAG, "my latlng = " + myLatLng.latitude + ", " + myLatLng.longitude);
        Marker nearestMark = null;

        for (Marker marker : mMarkerArray) {
            LatLng temp = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
            Log.d(TAG, "latlng = " + temp.latitude + ", " + temp.longitude);
            if (getDistance(temp, myLatLng) <= getDistance(nearestLoc, myLatLng)) {
                nearestLoc = temp;
                Log.d(TAG, "current nearest latlng = " + nearestLoc.latitude + ", " + nearestLoc.longitude);
                nearestMark = marker;
            }
        }
        Log.d(TAG, "my latlng = " + myLatLng.latitude + ", " + myLatLng.longitude);

        Log.d(TAG, "actual nearest latlng = " + nearestLoc.latitude + ", " + nearestLoc.longitude);
        if (nearestMark != null){
            nearestMark.showInfoWindow();
        }
        return nearestLoc;
    }

    public double getDistance(LatLng LatLng1, LatLng LatLng2) {
        double distance = 0;
        Location locationA = new Location("A");
        locationA.setLatitude(LatLng1.latitude);
        locationA.setLongitude(LatLng1.longitude);
        Location locationB = new Location("B");
        locationB.setLatitude(LatLng2.latitude);
        locationB.setLongitude(LatLng2.longitude);
        distance = locationA.distanceTo(locationB);
        Log.d(TAG, "distance = " + distance);
        return distance;
    }
}
