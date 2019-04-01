package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

import com.tehosiewdai.gojbboh.entity.MoneyChanger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MoneyChangerAsyncTask extends AsyncTask<Void, Void, MoneyChanger[]> {

    private static final String TAG = MoneyChangerAsyncTask.class.getSimpleName();

    private final String MONEY_CHANGER_LOCATION_URL = "https://geo.data.gov.sg/moneychanger/2019/01/02/geojson/moneychanger.geojson";

    private MoneyChangerTaskCallback callback;

    public MoneyChangerAsyncTask(MoneyChangerTaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null) {
            callback.onPreExecuteMoneyChangerTask();
        }
    }

    @Override
    protected MoneyChanger[] doInBackground(Void... voids) {
        try {
            URL url = new URL(MONEY_CHANGER_LOCATION_URL);
            String result = NetworkUtils.getResponseFromHttpUrl(url);
            return parseJSON(result);
        } catch (MalformedURLException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
        }
        return null;
    }

    @Override
    protected void onPostExecute(MoneyChanger[] moneyChangers) {
        if (callback != null && moneyChangers != null) {
            callback.onPostExecuteMoneyChangerTask(moneyChangers);
        }
    }

    private MoneyChanger[] parseJSON(String results){

        JSONArray features = null;
        MoneyChanger[] moneyChangers = null;

        try {
            JSONObject moneyChangerObject = new JSONObject(results);
            features = moneyChangerObject.getJSONArray("features");
        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }

        if (features != null) {
            moneyChangers = new MoneyChanger[features.length()];
            int counter = 0;
            for (int i = 0; i < features.length(); i++) {
                JSONObject feature = null;
                try {
                    feature = features.getJSONObject(i);
                    double lat = feature.getJSONObject("geometry").getJSONArray("coordinates").getDouble(1);
                    double lng = feature.getJSONObject("geometry").getJSONArray("coordinates").getDouble(0);
                    String title = feature.getJSONObject("properties").getString("Name");
                    String description = Html.fromHtml(feature.getJSONObject("properties").getString("Description")).toString();

                    String name = description.substring(description.lastIndexOf("NAME")+1, description.indexOf("BUSINESS_POSTALCODE"));


                    moneyChangers[counter] = new MoneyChanger(lat, lng, name, title, "k");
                    counter++;

                } catch (JSONException e) {
                    Log.e(TAG,String.valueOf(e));
                }

            }
        }

        return moneyChangers;
    }



    public interface MoneyChangerTaskCallback {
        void onPreExecuteMoneyChangerTask();

        void onPostExecuteMoneyChangerTask(MoneyChanger[] moneyChangers);
    }
}
