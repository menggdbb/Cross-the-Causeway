package com.tehosiewdai.gojbboh.activities;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.utilities.TrafficImageAsyncTask;
import com.tehosiewdai.gojbboh.utilities.WeatherAsyncTask;

public class MainActivity extends AppCompatActivity implements
        TrafficImageAsyncTask.TrafficImageTaskCallback, WeatherAsyncTask.WeatherTaskCallback {

    private ImageView woodlandsHomeImage;
    private ImageView tuasHomeImage;
    private ProgressBar loadingIndicator;
    private TextView woodlandsWeatherDescription;
    private TextView tuasWeatherDescription;
    private ImageView woodlandsWeatherIcon;
    private ImageView tuasWeatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        woodlandsHomeImage = (ImageView) findViewById(R.id.woodlands_home_image);
        tuasHomeImage = (ImageView) findViewById(R.id.tuas_home_image);

        Button woodlandsButton = (Button) findViewById(R.id.woodlands_button);
        Button tuasButton = (Button) findViewById(R.id.tuas_button);

        loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator_traffic);

        woodlandsWeatherDescription = (TextView) findViewById(R.id.woodlands_description);
        tuasWeatherDescription = (TextView) findViewById(R.id.tuas_description);

        woodlandsWeatherIcon = (ImageView) findViewById(R.id.woodlands_weather_image);
        tuasWeatherIcon = (ImageView) findViewById(R.id.tuas_weather_image);

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

    }

    @Override
    public void onPreExecuteTrafficTask() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPostExecuteTrafficTask(String[] result) {
        loadingIndicator.setVisibility(View.INVISIBLE);
        Picasso
                .with(this)
                .load(result[1])
                .placeholder(R.drawable.fff)
                .into(woodlandsHomeImage);
        Picasso
                .with(this)
                .load(result[3])
                .placeholder(R.drawable.fff)
                .into(tuasHomeImage);
    }

    @Override
    public void onPreExecuteWeatherTask() {

    }

    @Override
    public void onPostExecuteWeatherTask(String[] result) {
//        woodlandsWeatherDescription.setText(result[0]);
//        tuasWeatherDescription.setText(result[1]);

        displayWeather(result[0], woodlandsWeatherDescription, woodlandsWeatherIcon);
        displayWeather(result[1], tuasWeatherDescription, tuasWeatherIcon);

    }

    private void displayWeather(String description, TextView textView, ImageView imageView){

        switch (description){
            case "Fair (Night)":
                textView.setText(R.string.fair);
                imageView.setImageResource(R.drawable.fair_night);
                break;
            case "Fair (Day)":
            case "Fair":
                textView.setText(R.string.fair);
                imageView.setImageResource(R.drawable.fair_day);
                break;
            case "Fair & Warm":
                textView.setText(R.string.fair_and_warm);
                imageView.setImageResource(R.drawable.fair_and_warm);
                break;
            case "Partly Cloudy (Night)":
                textView.setText(R.string.partly_cloudy);
                imageView.setImageResource(R.drawable.partly_cloudy_night);
                break;
            case "Partly Cloudy (Day)":
            case "Partly Cloudy":
                textView.setText(R.string.partly_cloudy);
                imageView.setImageResource(R.drawable.partly_cloudy_day);
                break;
            case "Cloudy":
                textView.setText(R.string.cloudy);
                imageView.setImageResource(R.drawable.cloudy);
                break;
            case "Hazy":
                textView.setText(R.string.hazy);
                imageView.setImageResource(R.drawable.haze);
                break;
            case "Slightly Hazy":
                textView.setText(R.string.slightly_hazy);
                imageView.setImageResource(R.drawable.haze);
                break;
            case "Windy":
                textView.setText(R.string.windy);
                imageView.setImageResource(R.drawable.windy);
                break;
            case "Mist":
                textView.setText(R.string.mist);
                imageView.setImageResource(R.drawable.mist);
                break;
            case "Light Rain":
                textView.setText(R.string.light_rain);
                imageView.setImageResource(R.drawable.rain);
                break;
            case "Rain":
                textView.setText(R.string.light_rain);
                imageView.setImageResource(R.drawable.rain);
                break;
            case "Moderate Rain":
                textView.setText(R.string.moderate_rain);
                imageView.setImageResource(R.drawable.rain);
                break;
            case "Heavy Rain":
                textView.setText(R.string.heavy_rain);
                imageView.setImageResource(R.drawable.rain);
                break;
            case "Passing Showers":
                textView.setText(R.string.passing_showers);
                imageView.setImageResource(R.drawable.showers);
                break;
            case "Light Showers":
                textView.setText(R.string.light_showers);
                imageView.setImageResource(R.drawable.showers);
                break;
            case "Showers":
                textView.setText(R.string.showers);
                imageView.setImageResource(R.drawable.showers);
                break;
            case "Heavy Showers":
                textView.setText(R.string.heavy_showers);
                imageView.setImageResource(R.drawable.showers);
                break;
            case "Thundery Showers":
                textView.setText((R.string.thundery_showers));
                imageView.setImageResource(R.drawable.thundery_showers);
                break;
            case "Heavy Thundery Showers":
                textView.setText((R.string.thundery_showers));
                imageView.setImageResource(R.drawable.thundery_showers);
                break;
            case "Heavy Thundery Showers with Gusty Winds":
                textView.setText((R.string.thundery_showers));
                imageView.setImageResource(R.drawable.thundery_showers);
                break;
            default:
                textView.setText(R.string.weather_error);
                imageView.setImageResource(R.drawable.fair_day);
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
        if (menuItemThatWasSelected == R.id.menu){

            Context context = MainActivity.this;
            Class destinationActivity = MenuActivity.class;
            Intent startMenuActivity = new Intent(context, destinationActivity);

            startActivity(startMenuActivity);
        }
        return true;
    }

}
