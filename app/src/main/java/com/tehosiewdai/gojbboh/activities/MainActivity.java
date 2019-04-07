package com.tehosiewdai.gojbboh.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.controller.TrafficImageController;
import com.tehosiewdai.gojbboh.controller.WeatherController;

public class MainActivity extends AppCompatActivity {

    private ImageView woodlandsHomeImage;
    private ImageView tuasHomeImage;
    private ProgressBar loadingIndicator;
    private ProgressBar woodlandsLoadingIndicator;
    private ProgressBar tuasLoadingIndicator;
    private TextView woodlandsWeatherDescription;
    private TextView tuasWeatherDescription;
    private ImageView woodlandsWeatherIcon;
    private ImageView tuasWeatherIcon;
    private TextView woodlandsTitle;
    private TextView tuasTitle;
    private ImageView woodlandsBackdrop;
    private ImageView tuasBackdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        woodlandsHomeImage = findViewById(R.id.woodlands_home_image);
        tuasHomeImage = findViewById(R.id.tuas_home_image);

        Button woodlandsButton = findViewById(R.id.woodlands_button);
        Button tuasButton = findViewById(R.id.tuas_button);

        loadingIndicator = findViewById(R.id.loading_indicator_traffic);
        woodlandsLoadingIndicator = findViewById(R.id.woodlands_loading_indicator);
        tuasLoadingIndicator = findViewById(R.id.tuas_loading_indicator);

        woodlandsWeatherDescription = findViewById(R.id.woodlands_description);
        tuasWeatherDescription = findViewById(R.id.tuas_description);

        woodlandsWeatherIcon = findViewById(R.id.woodlands_weather_image);
        tuasWeatherIcon = findViewById(R.id.tuas_weather_image);

        woodlandsTitle = findViewById(R.id.woodlands);
        tuasTitle = findViewById(R.id.tuas);

        woodlandsBackdrop = findViewById(R.id.woodlands_backdrop);
        tuasBackdrop = findViewById(R.id.tuas_backdrop);

        TrafficImageController trafficImageController = new TrafficImageController(this);
        trafficImageController.runApiQuery();

        WeatherController weatherController = new WeatherController(this);
        weatherController.runApiQuery();


        tuasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuasHomeImage.setVisibility(View.VISIBLE);
            }
        });

        woodlandsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuasHomeImage.setVisibility(View.INVISIBLE);
            }
        });

        if (!haveNetworkConnection()) {
            Toast.makeText(this, getString(R.string.no_internet_toast), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == R.id.menu) {

            Context context = MainActivity.this;
            Class destinationActivity = MenuActivity.class;
            Intent startMenuActivity = new Intent(context, destinationActivity);

            startActivity(startMenuActivity);
        }
        return true;
    }

    private boolean haveNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public ProgressBar getLoadingIndicator() {
        return loadingIndicator;
    }

    public ImageView getWoodlandsHomeImage() {
        return woodlandsHomeImage;
    }

    public ProgressBar getWoodlandsLoadingIndicator() {
        return woodlandsLoadingIndicator;
    }

    public ProgressBar getTuasLoadingIndicator() {
        return tuasLoadingIndicator;
    }

    public ImageView getTuasHomeImage() {
        return tuasHomeImage;
    }

    public TextView getWoodlandsWeatherDescription() {
        return woodlandsWeatherDescription;
    }

    public TextView getTuasWeatherDescription() {
        return tuasWeatherDescription;
    }

    public ImageView getWoodlandsWeatherIcon() {
        return woodlandsWeatherIcon;
    }

    public ImageView getTuasWeatherIcon() {
        return tuasWeatherIcon;
    }

    public TextView getWoodlandsTitle() {
        return woodlandsTitle;
    }

    public TextView getTuasTitle() {
        return tuasTitle;
    }

    public ImageView getWoodlandsBackdrop() {
        return woodlandsBackdrop;
    }

    public ImageView getTuasBackdrop() {
        return tuasBackdrop;
    }
}
