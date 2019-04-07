package com.tehosiewdai.gojbboh.controller;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.tehosiewdai.gojbboh.activities.CurrencyActivity;
import com.tehosiewdai.gojbboh.utilities.ApiAsyncTask;
import com.tehosiewdai.gojbboh.utilities.ExchangeRateReader;

public class CurrencyController implements ControllerCallback{

    private final String EXCHANGE_RATE_URL = "https://api.exchangeratesapi.io/latest?base=SGD";

    private double rate;

    private CurrencyActivity activity;

    public CurrencyController(CurrencyActivity activity){
        this.activity = activity;
        rate = 0.0;
    }


    @Override
    public void onPreExecute() {}

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onPostExecute(String results) {

        rate = ExchangeRateReader.getExchangeRate(results);
        activity.getExchangeRate().setText("1SGD = " + String.format("%.2f", rate) + "MYR");

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

    @Override
    public String getUrlString() {
        return EXCHANGE_RATE_URL;
    }

    public void runApiQuery() {
        new ApiAsyncTask(this).execute();
    }
}
