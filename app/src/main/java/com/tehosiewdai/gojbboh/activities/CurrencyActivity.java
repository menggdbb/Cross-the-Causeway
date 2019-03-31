package com.tehosiewdai.gojbboh.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.utilities.ExchangeRateAsyncTask;

public class CurrencyActivity extends AppCompatActivity implements ExchangeRateAsyncTask.CurrencyTaskCallback {

    private TextView exchangeRate;
    private EditText sgdInput;
    private EditText myrInput;

    private double rate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        exchangeRate = findViewById(R.id.exchangeRate);

        sgdInput = findViewById(R.id.SGD_edit_text);
        myrInput = findViewById(R.id.MYR_edit_text);


        new ExchangeRateAsyncTask(this).execute();

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
                if (editable != null) {
                    if (sgdInput.getText().hashCode() == editable.hashCode()) {
                        myrInput.removeTextChangedListener(this);
                        if (editable.toString().equalsIgnoreCase("")) {
                            myrInput.setText("");
                        } else {
                            double value = Double.valueOf(editable.toString()) * rate;
                            myrInput.setText(String.format("%.2f", value));
                        }
                        myrInput.addTextChangedListener(this);
                    } else if (myrInput.getText().hashCode() == editable.hashCode()) {
                        sgdInput.removeTextChangedListener(this);
                        if (editable.toString().equalsIgnoreCase("")) {
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

        sgdInput.addTextChangedListener(textWatcher);
        myrInput.addTextChangedListener(textWatcher);
    }


    @Override
    public void onPreExecuteCurrencyTask() {
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onPostExecuteCurrencyTask(double result) {
        rate = result;
        exchangeRate.setText("1SGD = " + String.format("%.2f", result) + "MYR");
    }

}
