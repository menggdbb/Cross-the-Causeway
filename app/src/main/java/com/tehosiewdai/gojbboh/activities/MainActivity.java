package com.tehosiewdai.gojbboh.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.entity.TrafficImage;
import com.tehosiewdai.gojbboh.utilities.TrafficImageAsyncTask;
import com.tehosiewdai.gojbboh.utilities.WeatherAsyncTask;

public class MainActivity extends AppCompatActivity implements
        TrafficImageAsyncTask.TrafficImageTaskCallback, WeatherAsyncTask.WeatherTaskCallback {

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

        new TrafficImageAsyncTask(this).execute();
        new WeatherAsyncTask(this).execute();

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

        woodlandsHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destinationActivity = WoodlandsTrafficImageActivity.class;
                Intent startTrafficActivityIntent = new Intent(context, destinationActivity);
                startActivity(startTrafficActivityIntent);
            }
        });

        tuasHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destinationActivity = TuasTrafficImageActivity.class;
                Intent startTrafficActivityIntent = new Intent(context, destinationActivity);
                startActivity(startTrafficActivityIntent);
            }
        });

        if (!haveNetworkConnection()){
            Toast.makeText(this, "Please turn on your internet connection", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onPreExecuteTrafficTask() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPostExecuteTrafficTask(TrafficImage[] result) {
        loadingIndicator.setVisibility(View.INVISIBLE);
        for (TrafficImage trafficImage : result) {
            if (trafficImage.getCameraId().equals("2701")) {
                Picasso
                        .with(this)
                        .load(trafficImage.getImageUrl())
                        .placeholder(R.drawable.fff)
                        .into(woodlandsHomeImage);
            } else if (trafficImage.getCameraId().equals("4703")) {
                Picasso
                        .with(this)
                        .load(trafficImage.getImageUrl())
                        .placeholder(R.drawable.fff)
                        .into(tuasHomeImage);
            }
        }
    }

    @Override
    public void onPreExecuteWeatherTask() {
        woodlandsLoadingIndicator.setVisibility(View.VISIBLE);
        tuasLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPostExecuteWeatherTask(String[] result) {
        tuasLoadingIndicator.setVisibility(View.INVISIBLE);
        woodlandsLoadingIndicator.setVisibility(View.INVISIBLE);
        displayWeather(result[0], woodlandsWeatherDescription, woodlandsWeatherIcon, woodlandsTitle, woodlandsBackdrop);
        displayWeather(result[1], tuasWeatherDescription, tuasWeatherIcon, tuasTitle, tuasBackdrop);
    }

    private void displayWeather(String description, TextView textView, ImageView imageView, TextView title, ImageView backdrop) {
        switch (description) {
            case "Fair (Night)":
                textView.setText(R.string.fair);
                imageView.setImageResource(R.drawable.fair_night);
                backdrop.setImageResource(R.drawable.night_backdrop);
                break;
            case "Fair (Day)":
            case "Fair":
                textView.setText(R.string.fair);
                imageView.setImageResource(R.drawable.fair_day);
                imageView.setColorFilter(0);
                textView.setTextColor(ContextCompat.getColor(this, R.color.black));
                title.setTextColor(ContextCompat.getColor(this, R.color.black));
                backdrop.setImageResource(R.drawable.day_backdrop);
                break;
            case "Fair & Warm":
                textView.setText(R.string.fair_and_warm);
                imageView.setImageResource(R.drawable.fair_and_warm);
                imageView.setColorFilter(0);
                textView.setTextColor(ContextCompat.getColor(this, R.color.black));
                title.setTextColor(ContextCompat.getColor(this, R.color.black));
                backdrop.setImageResource(R.drawable.day_backdrop);
                break;
            case "Partly Cloudy (Night)":
                textView.setText(R.string.partly_cloudy);
                imageView.setImageResource(R.drawable.partly_cloudy_night);
                backdrop.setImageResource(R.drawable.night_backdrop);
                break;
            case "Partly Cloudy (Day)":
            case "Partly Cloudy":
                textView.setText(R.string.partly_cloudy);
                imageView.setImageResource(R.drawable.partly_cloudy_day);
                imageView.setColorFilter(0);
                textView.setTextColor(ContextCompat.getColor(this, R.color.black));
                title.setTextColor(ContextCompat.getColor(this, R.color.black));
                backdrop.setImageResource(R.drawable.day_backdrop);
                break;
            case "Cloudy":
                textView.setText(R.string.cloudy);
                imageView.setImageResource(R.drawable.cloudy);
                imageView.setColorFilter(0);
                textView.setTextColor(ContextCompat.getColor(this, R.color.black));
                title.setTextColor(ContextCompat.getColor(this, R.color.black));
                backdrop.setImageResource(R.drawable.cloudy_backdrop);
                break;
            case "Hazy":
                textView.setText(R.string.hazy);
                imageView.setImageResource(R.drawable.haze);
                backdrop.setImageResource(R.drawable.cloudy_backdrop);
                break;
            case "Slightly Hazy":
                textView.setText(R.string.slightly_hazy);
                imageView.setImageResource(R.drawable.haze);
                backdrop.setImageResource(R.drawable.cloudy_backdrop);
                break;
            case "Windy":
                textView.setText(R.string.windy);
                imageView.setImageResource(R.drawable.windy);
                imageView.setColorFilter(0);
                textView.setTextColor(ContextCompat.getColor(this, R.color.black));
                title.setTextColor(ContextCompat.getColor(this, R.color.black));
                backdrop.setImageResource(R.drawable.day_backdrop);
                break;
            case "Mist":
                textView.setText(R.string.mist);
                imageView.setImageResource(R.drawable.mist);
                imageView.setColorFilter(0);
                textView.setTextColor(ContextCompat.getColor(this, R.color.black));
                title.setTextColor(ContextCompat.getColor(this, R.color.black));
                backdrop.setImageResource(R.drawable.day_backdrop);
                break;
            case "Light Rain":
                textView.setText(R.string.light_rain);
                imageView.setImageResource(R.drawable.rain);
                backdrop.setImageResource(R.drawable.rain_backdrop);
                break;
            case "Rain":
                textView.setText(R.string.light_rain);
                imageView.setImageResource(R.drawable.rain);
                backdrop.setImageResource(R.drawable.rain_backdrop);
                break;
            case "Moderate Rain":
                textView.setText(R.string.moderate_rain);
                imageView.setImageResource(R.drawable.rain);
                backdrop.setImageResource(R.drawable.rain_backdrop);
                break;
            case "Heavy Rain":
                textView.setText(R.string.heavy_rain);
                imageView.setImageResource(R.drawable.rain);
                backdrop.setImageResource(R.drawable.rain_backdrop);
                break;
            case "Passing Showers":
                textView.setText(R.string.passing_showers);
                imageView.setImageResource(R.drawable.showers);
                backdrop.setImageResource(R.drawable.rain_backdrop);
                break;
            case "Light Showers":
                textView.setText(R.string.light_showers);
                imageView.setImageResource(R.drawable.showers);
                backdrop.setImageResource(R.drawable.rain_backdrop);
                break;
            case "Showers":
                textView.setText(R.string.showers);
                imageView.setImageResource(R.drawable.showers);
                backdrop.setImageResource(R.drawable.rain_backdrop);
                break;
            case "Heavy Showers":
                textView.setText(R.string.heavy_showers);
                imageView.setImageResource(R.drawable.showers);
                backdrop.setImageResource(R.drawable.rain_backdrop);
                break;
            case "Thundery Showers":
                textView.setText((R.string.thundery_showers));
                imageView.setImageResource(R.drawable.thundery_showers);
                backdrop.setImageResource(R.drawable.rain_backdrop);
                break;
            case "Heavy Thundery Showers":
                textView.setText((R.string.thundery_showers));
                imageView.setImageResource(R.drawable.thundery_showers);
                backdrop.setImageResource(R.drawable.rain_backdrop);
                break;
            case "Heavy Thundery Showers with Gusty Winds":
                textView.setText((R.string.thundery_showers));
                imageView.setImageResource(R.drawable.thundery_showers);
                backdrop.setImageResource(R.drawable.rain_backdrop);
                break;
            default:
                textView.setText(R.string.weather_error);
                imageView.setImageResource(R.drawable.fair_day);
                imageView.setColorFilter(0);
                textView.setTextColor(ContextCompat.getColor(this, R.color.black));
                title.setTextColor(ContextCompat.getColor(this, R.color.black));
                backdrop.setImageResource(R.drawable.day_backdrop);
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
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
