package com.tehosiewdai.gojbboh.utilities;

import android.util.Log;

import com.tehosiewdai.gojbboh.entity.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherReader {

    private static final String TAG = WeatherReader.class.getSimpleName();

    public static ArrayList<Weather> getWeather(String results){

        ArrayList<Weather> weatherDescriptions = new ArrayList<>();
        try {
            JSONObject weatherObject = new JSONObject(results);
            JSONArray forecasts = weatherObject.getJSONArray("items").getJSONObject(0).getJSONArray("forecasts");

            for (int i = 0; i < forecasts.length(); i++) {
                JSONObject forecast = forecasts.getJSONObject(i);
                weatherDescriptions.add(new Weather(forecast.getString("area"), forecast.getString("forecast")));
            }
        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }

        return weatherDescriptions;
    }

}
