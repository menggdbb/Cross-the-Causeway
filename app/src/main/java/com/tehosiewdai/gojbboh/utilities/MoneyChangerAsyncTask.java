package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MoneyChangerAsyncTask extends AsyncTask<Void, Void, JSONObject> {

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
    protected JSONObject doInBackground(Void... voids) {
        try {
            URL url = new URL(MONEY_CHANGER_LOCATION_URL);
            String results = NetworkUtils.getResponseFromHttpUrl(url);
            return new JSONObject(results);
        } catch (MalformedURLException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if (callback != null && jsonObject != null) {
            callback.onPostExecuteMoneyChangerTask(jsonObject);
        }
    }

    public interface MoneyChangerTaskCallback {
        void onPreExecuteMoneyChangerTask();

        void onPostExecuteMoneyChangerTask(JSONObject jsonObject);
    }
}
