package com.tehosiewdai.crossthecauseway.activities;

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

import com.tehosiewdai.crossthecauseway.R;
import com.tehosiewdai.crossthecauseway.controller.TrafficImageController;
import com.tehosiewdai.crossthecauseway.controller.WeatherController;

/**
 * Activity that opens upon startup.
 * This Activity acts as the homepage for this application.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * ImageView variable to hold the Woodlands traffic image on the homepage.
     */
    private ImageView woodlandsHomeImage;

    /**
     * ImageView variable to hold the Tuas traffic image on the homepage.
     */
    private ImageView tuasHomeImage;

    /**
     * ProgressBar variable to hold the loading indicator for traffic images on the homepage.
     */
    private ProgressBar loadingIndicator;

    /**
     * ProgressBar variable to hold the loading indicator for weather for Woodlands on the homepage.
     */
    private ProgressBar woodlandsLoadingIndicator;

    /**
     * ProgressBar variable to hold the loading indicator for weather for Tuas on the homepage.
     */
    private ProgressBar tuasLoadingIndicator;

    /**
     * TextView variable to hold the weather description for Woodlands on the homepage.
     */
    private TextView woodlandsWeatherDescription;

    /**
     * TextView variable to hold the weather description for Tuas on the homepage.
     */
    private TextView tuasWeatherDescription;

    /**
     * ImageView variable to hold the image for the weather for Woodlands on the homepage.
     */
    private ImageView woodlandsWeatherIcon;

    /**
     * ImageView variable to hold the image for the weather for Tuas on the homepage.
     */
    private ImageView tuasWeatherIcon;

    /**
     * ImageView variable to hold the title of the word Woodlands, to indicate weather at Woodlands.
     */
    private TextView woodlandsTitle;

    /**
     * ImageView variable to hold the title of the word Tuas, to indicate weather at Tuas.
     */
    private TextView tuasTitle;

    /**
     * ImageView variable to hold the background image corresponding to the weather at Woodlands.
     */
    private ImageView woodlandsBackdrop;

    /**
     * ImageView variable to hold the background image corresponding to the weather at Tuas.
     */
    private ImageView tuasBackdrop;

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState This Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           This value may be null;
     */
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

        //creates a new instance of TrafficImageController and calls to retrieve the data from the API in the background.
        TrafficImageController trafficImageController = new TrafficImageController(this);
        trafficImageController.runApiQuery();

        //creates a new instance of WeatherController and calls to retrieve the data from the API in the background.
        WeatherController weatherController = new WeatherController(this);
        weatherController.runApiQuery();

        //Sets event listener for the Tuas button; Tuas traffic image is displayed.
        tuasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuasHomeImage.setVisibility(View.VISIBLE);
            }
        });

        //Sets event listener for the Woodlands button; Tuas traffic image is hidden and Woodlands traffic image is shown.
        woodlandsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuasHomeImage.setVisibility(View.INVISIBLE);
            }
        });

        //If device is not connected to the internet; User is informed to turn on the internet connection.
        if (!haveNetworkConnection()) {
            Toast.makeText(this, getString(R.string.no_internet_toast), Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Displays a menu on the action bar.
     *
     * @param menu to be inflated.
     * @return true once menu is inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Event Listener for menu options upon selected.
     *
     * @param item that is selected in the menu.
     * @return true once item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();

        //starts the activity for menu activity upon selection
        if (menuItemThatWasSelected == R.id.menu) {
            Intent startMenuActivity = new Intent(this, MenuActivity.class);
            startActivity(startMenuActivity);
        }
        return true;
    }

    /**
     * Checks if the device is connected to the internet.
     *
     * @return true is there is internet connection.
     */
    private boolean haveNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Gets the traffic image loading indicator ProgressBar variable.
     *
     * @return traffic image loading indicator ProgressBar variable.
     */
    public ProgressBar getLoadingIndicator() {
        return loadingIndicator;
    }

    /**
     * Gets the ProgressBar loading indicator variable for weather description for Woodlands.
     *
     * @return ProgressBar loading indicator variable for weather description for Woodlands.
     */
    public ProgressBar getWoodlandsLoadingIndicator() {
        return woodlandsLoadingIndicator;
    }

    /**
     * Gets the ProgressBar loading indicator variable for weather description for Tuas.
     *
     * @return ProgressBar loading indicator variable for weather description for Tuas.
     */
    public ProgressBar getTuasLoadingIndicator() {
        return tuasLoadingIndicator;
    }

    /**
     * Gets the ImageView variable for traffic image for Woodlands.
     *
     * @return ImageView variable for traffic image for Woodlands.
     */
    public ImageView getWoodlandsHomeImage() {
        return woodlandsHomeImage;
    }

    /**
     * Gets the ImageView variable for traffic image for Tuas.
     *
     * @return ImageView variable for traffic image for Tuas.
     */
    public ImageView getTuasHomeImage() {
        return tuasHomeImage;
    }

    /**
     * Gets the TextView variable for weather description at Woodlands.
     *
     * @return TextView variable for weather description at Woodlands.
     */
    public TextView getWoodlandsWeatherDescription() {
        return woodlandsWeatherDescription;
    }

    /**
     * Gets the TextView variable for weather description at Tuas.
     *
     * @return TextView variable for weather description at Tuas.
     */
    public TextView getTuasWeatherDescription() {
        return tuasWeatherDescription;
    }

    /**
     * Gets the ImageView variable for the image for the weather at Woodlands.
     *
     * @return ImageView variable for the image for the weather at Woodlands.
     */
    public ImageView getWoodlandsWeatherIcon() {
        return woodlandsWeatherIcon;
    }

    /**
     * Gets the ImageView variable for the image for the weather at Tuas.
     *
     * @return ImageView variable for the image for the weather at Tuas.
     */
    public ImageView getTuasWeatherIcon() {
        return tuasWeatherIcon;
    }

    /**
     * Gets the TextView variable for the title of the word Woodlands, to indicate weather at Woodlands.
     *
     * @return TextView variable for the title of the word Woodlands, to indicate weather at Woodlands.
     */
    public TextView getWoodlandsTitle() {
        return woodlandsTitle;
    }

    /**
     * Gets the TextView variable for the title of the word Tuas, to indicate weather at Tuas.
     *
     * @return TextView variable for the title of the word Tuas, to indicate weather at Tuas.
     */
    public TextView getTuasTitle() {
        return tuasTitle;
    }

    /**
     * Gets the ImageView variable for the background image for the weather at Woodlands.
     *
     * @return ImageView variable for the background image for the weather at Woodlands.
     */
    public ImageView getWoodlandsBackdrop() {
        return woodlandsBackdrop;
    }

    /**
     * Gets the ImageView variable for the background image for the weather at Tuas.
     *
     * @return ImageView variable for the background image for the weather at Tuas.
     */
    public ImageView getTuasBackdrop() {
        return tuasBackdrop;
    }
}
