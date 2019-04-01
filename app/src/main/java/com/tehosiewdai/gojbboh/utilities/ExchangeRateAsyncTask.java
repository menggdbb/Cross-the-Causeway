package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ExchangeRateAsyncTask extends AsyncTask<Void, Void, Double> {

    private static final String TAG = ExchangeRateAsyncTask.class.getSimpleName();

    private final String EXCHANGE_RATE_URL = "https://api.exchangeratesapi.io/latest?base=SGD";

    private CurrencyTaskCallback callback;

    public ExchangeRateAsyncTask(CurrencyTaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null) {
            callback.onPreExecuteCurrencyTask();
        }
    }

    @Override
    protected Double doInBackground(Void... voids) {
        try {
            URL url = new URL(EXCHANGE_RATE_URL);
            String results = NetworkUtils.getResponseFromHttpUrl(url);

            JSONObject rateObject = new JSONObject(results);
            JSONObject rates = (JSONObject) rateObject.get("rates");
            return rates.getDouble("MYR");

        } catch (MalformedURLException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }

        return 0.0;
    }

    @Override
    protected void onPostExecute(Double result) {
        if (callback != null) {
            callback.onPostExecuteCurrencyTask(result);
        }
    }

    public interface CurrencyTaskCallback {
        void onPreExecuteCurrencyTask();

        void onPostExecuteCurrencyTask(double result);
    }
}
