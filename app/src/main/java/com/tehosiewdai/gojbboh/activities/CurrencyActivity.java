package com.tehosiewdai.gojbboh.activities;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.utilities.ConvertCurrency;
import com.tehosiewdai.gojbboh.utilities.FetchData;

public class CurrencyActivity extends AppCompatActivity implements FetchData.FetchDataCall {
    Button click;
    public static ToggleButton change;
    public static TextView data;
    public static EditText SGDinput;
    public static EditText MYRinput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        click = findViewById(R.id.convert);
        data = findViewById(R.id.exchangeRate);
        change = findViewById(R.id.switchConvert);
        SGDinput = findViewById(R.id.SGD);
        MYRinput = findViewById(R.id.MYR);
    new FetchData(this).execute();
        click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ConvertCurrency convert = new ConvertCurrency();
                convert.execute();
            }
        });
    };

    @Override
    public void onPreExecuteFetchData() {

    }

    @Override
    public void onPostExecuteFetchData(String result) {
        data.setText(result);
    }

}
