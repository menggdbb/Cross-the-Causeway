package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;

import com.tehosiewdai.gojbboh.activities.CurrencyActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class FetchData extends AsyncTask<Void, Void, Void> {
    String data = "";
    String dataParsed = "";
    public static double SGDtoMYR;
    public DecimalFormat df = new DecimalFormat(".###");
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest?base=SGD");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream)));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject JO = new JSONObject (data);
            JSONObject rates = (JSONObject) JO.get("rates");
            SGDtoMYR = rates.getDouble("MYR");
            dataParsed = "1 SGD = " + String.valueOf(df.format(SGDtoMYR)) + " MYR";

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        CurrencyActivity.data.setText((this.dataParsed));
    }
}