package com.tehosiewdai.crossthecauseway.controller;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.tehosiewdai.crossthecauseway.activities.CurrencyActivity;
import com.tehosiewdai.crossthecauseway.utilities.ApiAsyncTask;
import com.tehosiewdai.crossthecauseway.utilities.ExchangeRateReader;

/**
 * Controller class for logic about exchange rate.
 */
public class CurrencyController implements ControllerCallback {

    /**
     * URL string for API access to exchange rates.
     */
    private final String EXCHANGE_RATE_URL = "https://api.exchangeratesapi.io/latest?base=SGD";

    /**
     * exchange rate between SGD and MYR.
     */
    private double rate;

    /**
     * CurrencyActivity that uses this activity.
     */
    private CurrencyActivity activity;

    /**
     * Instantiates the controller.
     *
     * @param activity the activity that instantiated it.
     */
    public CurrencyController(CurrencyActivity activity) {
        this.activity = activity;
        rate = 0.0;
    }

    /**
     * Implements ControllerCallback method.
     * Does nothing.
     */
    @Override
    public void onPreExecute() {
    }

    /**
     * Implements ControllerCallback method.
     * Actions done after querying the JSON result in the background.
     *
     * @param results JSON string queried.
     */
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onPostExecute(String results) {

        //gets the exchange rate after using the ExchangeRateReader to read the JSON string result.
        rate = ExchangeRateReader.getExchangeRate(results);
        activity.getExchangeRate().setText("1SGD = " + String.format("%.2f", rate) + "MYR");

        //TextWatcher to observe if text has changed in the EditText inputs for SGD and MYR.
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @SuppressLint("DefaultLocale")
            @Override
            public void afterTextChanged(Editable editable) {
                EditText sgdInput = activity.getSgdInput();
                EditText myrInput = activity.getMyrInput();
                if (editable != null) {
                    if (sgdInput.getText().hashCode() == editable.hashCode()) {
                        myrInput.removeTextChangedListener(this);
                        if (editable.toString().equalsIgnoreCase("") || editable.toString().equalsIgnoreCase(".")) {
                            myrInput.setText("");
                        } else {
                            double value = Double.valueOf(editable.toString()) * rate;
                            myrInput.setText(String.format("%.2f", value));
                        }
                        myrInput.addTextChangedListener(this);
                    } else if (myrInput.getText().hashCode() == editable.hashCode()) {
                        sgdInput.removeTextChangedListener(this);
                        if (editable.toString().equalsIgnoreCase("") || editable.toString().equalsIgnoreCase(".")) {
                            sgdInput.setText("");
                        } else {
                            double value = Double.valueOf(editable.toString()) / rate;
                            sgdInput.setText(String.format("%.2f", value));
                        }
                        sgdInput.addTextChangedListener(this);
                    }
                }
            }
        };

        activity.getSgdInput().addTextChangedListener(textWatcher);
        activity.getMyrInput().addTextChangedListener(textWatcher);
    }

    /**
     * Gets the URL string for API access to exchange rates.
     *
     * @return URL string for API access to exchange rates.
     */
    @Override
    public String getUrlString() {
        return EXCHANGE_RATE_URL;
    }

    /**
     * Executes a background process to retrieve JSON string from the API.
     */
    public void runApiQuery() {
        new ApiAsyncTask(this).execute();
    }
}
