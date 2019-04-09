package com.tehosiewdai.gojbboh.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.view.View;

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

/**
 * Controller class for logic about the map and money changer locations.
 */
public class MoneyChangerController implements ControllerCallback {

    /**
     * Unique identifier for location services request.
     */
    public static final int LOCATION_REQUEST_CODE = 991;

    /**
     * URL string for API access to location of money changers.
     */
    private final String MONEY_CHANGER_LOCATION_URL = "https://geo.data.gov.sg/moneychanger/2019/01/02/geojson/moneychanger.geojson";

    /**
     * Array list for Google Map markers.
     */
    private ArrayList<Marker> markers;

    /**
     * Client to get location services.
     */
    private FusedLocationProviderClient fusedLocationClient;

    /**
     * Variable to hold GoogleMap map.
     */
    private GoogleMap map;

    /**
     * MoneyChangerActivity that uses this controller.
     */
    private MoneyChangerActivity activity;

    /**
     * Instantiates the controller.
     *
     * @param activity the activity that instantiated it.
     */
    public MoneyChangerController(MoneyChangerActivity activity) {
        this.activity = activity;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        markers = new ArrayList<>();
    }

    /**
     * Implements ControllerCallback method.
     * Does nothing.
     */
    @Override
    public void onPreExecute() {
    }

    /**
     * Implements ControllerCallback method.
     * Actions done after querying the JSON result in the background.
     *
     * @param results JSON string queried.
     */
    @Override
    public void onPostExecute(String results) {
        //gets an array list of MoneyChanger after using MoneyChangerReader to read the JSON string result,
        ArrayList<MoneyChanger> moneyChangers = MoneyChangerReader.getMoneyChangerLocations(results);

        //adds markers to the map from the list of money changer locations.
        for (MoneyChanger moneyChanger : moneyChangers) {
            Marker marker = activity.getMap().addMarker(new MarkerOptions()
                    .position(moneyChanger.getLatLng())
                    .title(moneyChanger.getName())
                    .snippet(moneyChanger.getPostalCode().concat(moneyChanger.getAddress())));
            markers.add(marker);
        }

        //set up an event listener for the nearest button upon the availability of the location of money changers.
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

    /**
     * Gets the URL string for API access to location of money changers.
     *
     * @return URL string for API access to location of money changers.
     */
    @Override
    public String getUrlString() {
        return MONEY_CHANGER_LOCATION_URL;
    }

    /**
     * Executes a background process to retrieve JSON string from the API.
     */
    private void runApiQuery() {
        new ApiAsyncTask(this).execute();
    }

    /**
     * sets parameters to the map.
     */
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

    /**
     * moves the view to the user's location if location service permissions is granted.
     */
    @SuppressLint("MissingPermission")
    public void moveToLocation() {
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

    /**
     * Finds nearest money changer location.
     *
     * @param myLocation user's location.
     * @return nearest LatLng coordinates of the nearest money changer location.
     */
    private LatLng findNearest(Location myLocation) {
        LatLng nearestLocation = new LatLng(0, 0);
        LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        Marker nearestMark = null;

        for (Marker marker : markers) {
            LatLng temp = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
            if (getDistance(temp, myLatLng) <= getDistance(nearestLocation, myLatLng)) {
                nearestLocation = temp;
                nearestMark = marker;
            }
        }

        if (nearestMark != null) {
            nearestMark.showInfoWindow();
        }
        return nearestLocation;
    }

    /**
     * Gets the displacement between 2 locations.
     *
     * @param LatLng1 coordinates of first location.
     * @param LatLng2 coordinates of second location.
     * @return the distance.
     */
    private double getDistance(LatLng LatLng1, LatLng LatLng2) {
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
