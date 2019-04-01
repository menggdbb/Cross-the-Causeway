package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherAsyncTask extends AsyncTask<Void, Void, String[]> {

    private static final String TAG = WeatherAsyncTask.class.getSimpleName();

    private final String WEATHER_URL = "https://api.data.gov.sg/v1/environment/2-hour-weather-forecast";

    private WeatherTaskCallback callback;

    public WeatherAsyncTask(WeatherTaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null) {
            callback.onPreExecuteWeatherTask();
        }
    }

    @Override
    protected String[] doInBackground(Void... voids) {
        String[] descriptionArray = new String[2];
        try {
            URL url = new URL(WEATHER_URL);
            String results = NetworkUtils.getResponseFromHttpUrl(url);
            JSONObject weatherObject = new JSONObject(results);
            JSONArray forecasts = weatherObject.getJSONArray("items").getJSONObject(0).getJSONArray("forecasts");

            for (int i = 0; i < forecasts.length(); i++) {
                JSONObject forecast = forecasts.getJSONObject(i);
                if (forecast.get("area").equals("Woodlands")) {
                    descriptionArray[0] = (String) forecast.get("forecast");
                } else if (forecast.get("area").equals("Tuas")) {
                    descriptionArray[1] = (String) forecast.get("forecast");
                }
            }

        } catch (MalformedURLException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }

        return descriptionArray;
    }

    @Override
    protected void onPostExecute(String[] result) {
        if (callback != null && result[0] != null) {
            callback.onPostExecuteWeatherTask(result);
        }
    }

    public interface WeatherTaskCallback {
        void onPreExecuteWeatherTask();

        void onPostExecuteWeatherTask(String[] result);
    }
}
