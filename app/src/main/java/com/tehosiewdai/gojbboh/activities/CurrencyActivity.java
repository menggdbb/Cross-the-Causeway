package com.tehosiewdai.gojbboh.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.controller.CurrencyController;

public class CurrencyActivity extends AppCompatActivity {

    private TextView exchangeRate;
    private EditText sgdInput;
    private EditText myrInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        exchangeRate = findViewById(R.id.exchangeRate);

        sgdInput = findViewById(R.id.SGD_edit_text);
        myrInput = findViewById(R.id.MYR_edit_text);

        CurrencyController currencyController = new CurrencyController(this);
        currencyController.runApiQuery();

    }

    public EditText getMyrInput() {
        return myrInput;
    }

    public EditText getSgdInput() {
        return sgdInput;
    }

    public TextView getExchangeRate() {
        return exchangeRate;
    }
}
