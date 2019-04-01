package com.tehosiewdai.gojbboh.activities;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlLayer;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlPoint;
import com.google.maps.android.data.kml.KmlPolygon;

import org.xmlpull.v1.XmlPullParserException;

import com.tehosiewdai.gojbboh.R;

import java.io.IOException;



public class MoneyChangerActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_changer);
//         Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(10.0f);
        mMap.setMaxZoomPreference(20.0f);

        final LatLngBounds SINGAPORE = new LatLngBounds(
                new LatLng(1.152761, 103.559083), new LatLng(1.487512, 104.113698));

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(SINGAPORE, 1));
        //new LatLng(1.35, 103.8
        final LatLngBounds Singapore = new LatLngBounds(
                new LatLng(1.152761, 103.559083), new LatLng(1.487512, 104.113698));



        try {
            KmlLayer layer = new KmlLayer(mMap, R.raw.locations_of_money_changer_kml, getApplicationContext());
            layer.addLayerToMap();
            for (KmlPlacemark placemark : layer.getPlacemarks()) {
                KmlPoint point = (KmlPoint) placemark.getGeometry();
                LatLng latLng = new LatLng(point.getGeometryObject().latitude, point.getGeometryObject().longitude);


                String snippet = "Address: " + placemark.getProperty("BUSINESS_ADDRESS1") + "\n" +
                        "Postal code: " + placemark.getProperty("BUSINESS_POSTALCODE") + "\n";

                mMap.addMarker(new MarkerOptions().position(latLng).title("hi").snippet(snippet));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

}
