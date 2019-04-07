package com.tehosiewdai.gojbboh.utilities;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class ExchangeRateReader {

    private static final String TAG = ExchangeRateReader.class.getSimpleName();

    public static double getExchangeRate(String results) {
        try {
            JSONObject rateObject = new JSONObject(results);
            JSONObject rates = (JSONObject) rateObject.get("rates");
            return rates.getDouble("MYR");

        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }
        return 0.0;
    }
}
