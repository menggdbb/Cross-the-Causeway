package com.tehosiewdai.crossthecauseway.controller;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tehosiewdai.crossthecauseway.R;
import com.tehosiewdai.crossthecauseway.activities.MainActivity;
import com.tehosiewdai.crossthecauseway.entity.Weather;
import com.tehosiewdai.crossthecauseway.utilities.ApiAsyncTask;
import com.tehosiewdai.crossthecauseway.utilities.WeatherReader;

import java.util.ArrayList;

/**
 * Controller class for logic about weather updates.
 */
public class WeatherController implements ControllerCallback {

    /**
     * URL string for API access to weather updates.
     */
    private final String WEATHER_URL = "https://api.data.gov.sg/v1/environment/2-hour-weather-forecast";

    /**
     * Array list for weather descriptions and its locations.
     */
    private ArrayList<Weather> weatherDescriptions;

    /**
     * MainActivity that uses this controller.
     */
    private MainActivity activity;

    /**
     * Instantiates the controller.
     *
     * @param activity the activity that instantiated it.
     */
    public WeatherController(MainActivity activity) {
        this.activity = activity;
        weatherDescriptions = new ArrayList<>();
    }

    /**
     * Implements ControllerCallback method.
     * Sets loading indicators to indicate that weather updates are being loaded.
     */
    @Override
    public void onPreExecute() {
        activity.getWoodlandsLoadingIndicator().setVisibility(View.VISIBLE);
        activity.getTuasLoadingIndicator().setVisibility(View.VISIBLE);
    }

    /**
     * Implements ControllerCallback method.
     * Actions done after querying the JSON result in the background.
     *
     * @param results JSON string queried.
     */
    @Override
    public void onPostExecute(String results) {
        weatherDescriptions = WeatherReader.getWeather(results);
        activity.getWoodlandsLoadingIndicator().setVisibility(View.INVISIBLE);
        activity.getTuasLoadingIndicator().setVisibility(View.INVISIBLE);

        //Displays weather updates if at Woodlands or Tuas.
        for (Weather description : weatherDescriptions) {
            if (description.getArea().equals("Woodlands")) {
                displayWeather(
                        description.getDescription(),
                        activity.getWoodlandsWeatherDescription(),
                        activity.getWoodlandsWeatherIcon(),
                        activity.getWoodlandsTitle(),
                        activity.getWoodlandsBackdrop()
                );
            } else if (description.getArea().equals("Tuas")) {
                displayWeather(
                        description.getDescription(),
                        activity.getTuasWeatherDescription(),
                        activity.getTuasWeatherIcon(),
                        activity.getTuasTitle(),
                        activity.getTuasBackdrop()
                );
            }
        }

    }

    /**
     * Gets the URL string for API access to weather updates.
     *
     * @return URL string for API access to weather updates..
     */
    @Override
    public String getUrlString() {
        return WEATHER_URL;
    }

    /**
     * Executes a background process to retrieve JSON string from the API.
     */
    public void runApiQuery() {
        weatherDescriptions.clear();
        new ApiAsyncTask(this).execute();
    }

    /**
     * Correlates the weather description from the weather updates and sets descriptions and assets on the Views.
     *
     * @param description Description of weather.
     * @param textView    TextView for description of weather.
     * @param imageView   ImageView for icon for the weather description.
     * @param title       TextView for the name of the location.
     * @param backdrop    ImageView for the image background associated to the current weather.
     */
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
                textView.setTextColor(ContextCompat.getColor(activity, R.color.black));
                title.setTextColor(ContextCompat.getColor(activity, R.color.black));
                backdrop.setImageResource(R.drawable.day_backdrop);
                break;
            case "Fair & Warm":
                textView.setText(R.string.fair_and_warm);
                imageView.setImageResource(R.drawable.fair_and_warm);
                imageView.setColorFilter(0);
                textView.setTextColor(ContextCompat.getColor(activity, R.color.black));
                title.setTextColor(ContextCompat.getColor(activity, R.color.black));
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
                textView.setTextColor(ContextCompat.getColor(activity, R.color.black));
                title.setTextColor(ContextCompat.getColor(activity, R.color.black));
                backdrop.setImageResource(R.drawable.day_backdrop);
                break;
            case "Cloudy":
                textView.setText(R.string.cloudy);
                imageView.setImageResource(R.drawable.cloudy);
                imageView.setColorFilter(0);
                textView.setTextColor(ContextCompat.getColor(activity, R.color.black));
                title.setTextColor(ContextCompat.getColor(activity, R.color.black));
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
                textView.setTextColor(ContextCompat.getColor(activity, R.color.black));
                title.setTextColor(ContextCompat.getColor(activity, R.color.black));
                backdrop.setImageResource(R.drawable.day_backdrop);
                break;
            case "Mist":
                textView.setText(R.string.mist);
                imageView.setImageResource(R.drawable.mist);
                imageView.setColorFilter(0);
                textView.setTextColor(ContextCompat.getColor(activity, R.color.black));
                title.setTextColor(ContextCompat.getColor(activity, R.color.black));
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
                textView.setTextColor(ContextCompat.getColor(activity, R.color.black));
                title.setTextColor(ContextCompat.getColor(activity, R.color.black));
                backdrop.setImageResource(R.drawable.day_backdrop);
        }

    }
}
