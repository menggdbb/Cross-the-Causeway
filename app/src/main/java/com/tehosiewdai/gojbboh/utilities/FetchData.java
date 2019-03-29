package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;

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

public class FetchData extends AsyncTask<Void, Void, String> {
    private String data = "";
    private String dataParsed = "";
    private FetchDataCall callback;
    public static double SGDtoMYR;
    public DecimalFormat df = new DecimalFormat(".###");
    public FetchData(FetchDataCall callback){
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null){
            callback.onPreExecuteFetchData();
        }
    }
    @Override
    protected String doInBackground(Void... voids) {
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
        return dataParsed;
    }

    @Override
    protected void onPostExecute(String result) {
        if (callback != null){
            callback.onPostExecuteFetchData(result);
        }
    }

    public interface FetchDataCall{
        void onPreExecuteFetchData();
        void onPostExecuteFetchData(String result);
    }
}
