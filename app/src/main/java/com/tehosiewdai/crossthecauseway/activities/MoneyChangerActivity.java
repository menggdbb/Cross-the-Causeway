package com.tehosiewdai.crossthecauseway.activities;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.tehosiewdai.crossthecauseway.R;
import com.tehosiewdai.crossthecauseway.controller.MoneyChangerController;

/**
 * Activity that opens upon selection at the menu.
 * This activity acts as the Location of Money Changers Page for this application.
 */
public class MoneyChangerActivity extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    /**
     * Controller class variable for logic behind this activity.
     */
    MoneyChangerController moneyChangerController;

    /**
     * GoogleMap variable to hold the map.
     */
    private GoogleMap map;

    /**
     * Button variable to hold the button to get nearest money changer location.
     */
    private Button nearestButton;

    /**
     * Called when activity is starting.
     *
     * @param savedInstanceState This Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           This value may be null;
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_changer);

        nearestButton = findViewById(R.id.buttonMaps);

        //obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        //creates an instance of the MoneyChangerController.
        moneyChangerController = new MoneyChangerController(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        moneyChangerController.setMap();
    }

    /**
     * Gets the GoogleMap map variable.
     *
     * @return GoogleMap map variable.
     */
    public GoogleMap getMap() {
        return map;
    }

    /**
     * Gets the Button for nearest money changer location.
     *
     * @return Button for nearest money changer location.
     */
    public Button getNearestButton() {
        return nearestButton;
    }

    /**
     * Event listener for user's response to location services permission.
     *
     * @param requestCode  request code from requesting permissions.
     * @param permissions  the permissions queried by system.
     * @param grantResults the result of permission queried by system.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MoneyChangerController.LOCATION_REQUEST_CODE) {
            if (permissions.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                moneyChangerController.moveToLocation();
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
