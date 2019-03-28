package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherAsyncTask extends AsyncTask<Void, Void, String[]> {

    private String weatherUrlString = "https://api.data.gov.sg/v1/environment/2-hour-weather-forecast";

    private WeatherTaskCallback callback;

    public WeatherAsyncTask(WeatherTaskCallback callback){
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null){
            callback.onPreExecuteWeatherTask();
        }
    }

    @Override
    protected String[] doInBackground(Void... voids) {
        URL url = null;
        String results = null;
        String[] descriptionArray = new String[2];

        try {
            url = new URL(weatherUrlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            results = NetworkUtils.getResponseFromHttpUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject weatherObject = null;

        try {
            weatherObject = new JSONObject(results);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray forecasts = null;

        try {
            forecasts = weatherObject.getJSONArray("items").getJSONObject(0).getJSONArray("forecasts");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < forecasts.length(); i++) {
            JSONObject forecast = null;
            try {
                forecast = forecasts.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                if (forecast.get("area").equals("Woodlands")) {
                    descriptionArray[0] = (String) forecast.get("forecast");
                } else if (forecast.get("area").equals("Tuas")) {
                    descriptionArray[1] = (String) forecast.get("forecast");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return descriptionArray;
    }

    @Override
    protected void onPostExecute(String[] result) {
        if (callback != null){
            callback.onPostExecuteWeatherTask(result);
        }
    }

    public interface WeatherTaskCallback {
        void onPreExecuteWeatherTask();
        void onPostExecuteWeatherTask(String[] result);
    }
}
