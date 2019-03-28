package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;

import com.tehosiewdai.gojbboh.activities.CurrencyActivity;

public class ConvertCurrency extends AsyncTask<Void,Void,Void> {
    private String MYRstring = null;
    private String SGDString = null;
    private double convertedSGD = 0;
    private double convertedMYR = 0;
    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(CurrencyActivity.change.isChecked()){
            MYRstring = CurrencyActivity.MYRinput.getText().toString();
            convertedSGD = Double.parseDouble(MYRstring)/FetchData.SGDtoMYR;
            CurrencyActivity.SGDinput.setText(Double.toString(convertedSGD));
        }
        else{
            SGDString = CurrencyActivity.SGDinput.getText().toString();
            convertedMYR = Double.parseDouble(SGDString)*FetchData.SGDtoMYR;
            CurrencyActivity.MYRinput.setText(Double.toString(convertedMYR));
        }

    }
    }

