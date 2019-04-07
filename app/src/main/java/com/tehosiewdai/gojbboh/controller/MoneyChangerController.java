package com.tehosiewdai.gojbboh.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.tehosiewdai.gojbboh.activities.MoneyChangerActivity;
import com.tehosiewdai.gojbboh.adapters.MapInfoWindowAdapter;
import com.tehosiewdai.gojbboh.entity.MoneyChanger;
import com.tehosiewdai.gojbboh.utilities.ApiAsyncTask;
import com.tehosiewdai.gojbboh.utilities.MoneyChangerReader;

import java.util.ArrayList;

public class MoneyChangerController implements ControllerCallback {

    public static final int LOCATION_REQUEST_CODE = 991;
    private final String MONEY_CHANGER_LOCATION_URL = "https://geo.data.gov.sg/moneychanger/2019/01/02/geojson/moneychanger.geojson";

    private ArrayList<Marker> markers;
    private FusedLocationProviderClient fusedLocationClient;

    private GoogleMap map;

    private MoneyChangerActivity activity;

    public MoneyChangerController(MoneyChangerActivity activity) {
        this.activity = activity;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        markers = new ArrayList<>();
    }

    @Override
    public void onPreExecute() {
    }

    @Override
    public void onPostExecute(String results) {
        ArrayList<MoneyChanger> moneyChangers = MoneyChangerReader.getMoneyChangerLocations(results);

        for (MoneyChanger moneyChanger : moneyChangers) {
            Marker marker = activity.getMap().addMarker(new MarkerOptions()
                    .position(moneyChanger.getLatLng())
                    .title(moneyChanger.getName())
                    .snippet(moneyChanger.getPostalCode().concat(moneyChanger.getAddress())));
            markers.add(marker);
        }

        activity.getNearestButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
                } else {
                    map.setMyLocationEnabled(true);
                    fusedLocationClient.getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null && !markers.isEmpty()) {
                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(findNearest(location), 14.0f));
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public String getUrlString() {
        return MONEY_CHANGER_LOCATION_URL;
    }

    private void runApiQuery() {
        new ApiAsyncTask(this).execute();
    }

    public void setMap() {
        map = activity.getMap();
        runApiQuery();

        map.setInfoWindowAdapter(new MapInfoWindowAdapter(activity));

        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMinZoomPreference(12.0f);
        map.setMaxZoomPreference(20.0f);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.35, 103.8), 12));

        LatLngBounds SINGAPORE = new LatLngBounds(
                new LatLng(1.152761, 103.559083), new LatLng(1.487512, 104.113698));
        map.setLatLngBoundsForCameraTarget(SINGAPORE);

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            moveToLocation();
        }
    }

    @SuppressLint("MissingPermission")
    public void moveToLocation(){
        map.setMyLocationEnabled(true);
        fusedLocationClient.getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
                }
            }
        });
    }

    private LatLng findNearest(Location myLoc) {
        LatLng nearestLoc = new LatLng(0, 0);
        LatLng myLatLng = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
        Marker nearestMark = null;

        for (Marker marker : markers) {
            LatLng temp = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
            if (getDistance(temp, myLatLng) <= getDistance(nearestLoc, myLatLng)) {
                nearestLoc = temp;
                nearestMark = marker;
            }
        }

        if (nearestMark != null) {
            nearestMark.showInfoWindow();
        }
        return nearestLoc;
    }

    public double getDistance(LatLng LatLng1, LatLng LatLng2) {
        double distance;
        Location locationA = new Location("A");
        locationA.setLatitude(LatLng1.latitude);
        locationA.setLongitude(LatLng1.longitude);
        Location locationB = new Location("B");
        locationB.setLatitude(LatLng2.latitude);
        locationB.setLongitude(LatLng2.longitude);
        distance = locationA.distanceTo(locationB);
        return distance;
    }

}
