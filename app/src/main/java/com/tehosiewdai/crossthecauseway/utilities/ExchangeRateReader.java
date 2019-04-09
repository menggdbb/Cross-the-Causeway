package com.tehosiewdai.crossthecauseway.utilities;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Reader to exchange rate from JSON string.
 */
public class ExchangeRateReader {

    /**
     * Simple name of the class.
     */
    private static final String TAG = ExchangeRateReader.class.getSimpleName();

    /**
     * Gets the JSON string and returns the exchange rate.
     *
     * @param results JSON string to be parsed.
     * @return the exchange rate.
     */
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
