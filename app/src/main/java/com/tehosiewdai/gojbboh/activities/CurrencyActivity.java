package com.tehosiewdai.gojbboh.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.controller.CurrencyController;

/**
 * Activity that opens upon selection at the menu.
 * This activity acts as the Exchange Rate Page for this application.
 */
public class CurrencyActivity extends AppCompatActivity {

    /**
     * TextView variable to hold the exchange rate value
     */
    private TextView exchangeRate;

    /**
     * EditText variable to hold the input for SGD value.
     */
    private EditText sgdInput;

    /**
     * EditText variable to hold the input for MYR value.
     */
    private EditText myrInput;

    /**
     * Called when activity is starting.
     *
     * @param savedInstanceState This Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           This value may be null;
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        exchangeRate = findViewById(R.id.exchangeRate);

        sgdInput = findViewById(R.id.SGD_edit_text);
        myrInput = findViewById(R.id.MYR_edit_text);

        //creates an instance of CurrencyController and calls to retrieve the data from the API in the background.
        CurrencyController currencyController = new CurrencyController(this);
        currencyController.runApiQuery();

    }

    /**
     * Gets the EditText variable to hold the input for MYR value.
     *
     * @return EditText variable to hold the input for MYR value.
     */
    public EditText getMyrInput() {
        return myrInput;
    }

    /**
     * Gets the EditText variable to hold the input for SGD value.
     *
     * @return EditText variable to hold the input for SGD value.
     */
    public EditText getSgdInput() {
        return sgdInput;
    }

    /**
     * Gets the TextView variable to hold the exchange rate value.
     *
     * @return TextView variable to hold the exchange rate value.
     */
    public TextView getExchangeRate() {
        return exchangeRate;
    }
}
