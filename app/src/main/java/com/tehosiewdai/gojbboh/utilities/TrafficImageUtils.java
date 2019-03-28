package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TrafficImageUtils {

    private static URL trafficUrl = null;
    private static String urlString = "https://api.data.gov.sg/v1/transport/traffic-images";
    private static String trafficString;

    private static String[] urlArray = new String[5];

    private static class TrafficImageQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL mUrl = urls[0];
            String results = null;
            try {
                results = NetworkUtils.getResponseFromHttpUrl(mUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return results;
        }

        @Override
        protected void onPostExecute(String results) {
            if (results != null && !results.equals("")) {
                parseJSON(results);
            } else {
                trafficString = "null";
            }
        }
    }

    public static String getURL(int index){
        return urlArray[index];
    }

    private static void parseJSON(String results) {

        JSONObject trafficObject = null;

        Log.e("this","ok");

        try {
            trafficObject = new JSONObject(results);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray trafficImages = null;

        Log.e("this","ok2");

        try {
            trafficImages = trafficObject.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject trafficImage0 = null;
        Log.e("this","ok3");

        try {
            trafficImage0 = trafficImages.getJSONObject(0).getJSONArray("cameras").getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("this","ok4");


        String imageUrl = null;
        try {
            imageUrl = (String) trafficImage0.get("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("this","ok5");
        trafficString = imageUrl;

//        try {
//            trafficString = "lat: " + trafficImage0.getJSONObject("location").get("latitude") +
//                    "\nlong: " + trafficImage0.getJSONObject("location").get("longitude") +
//                    "\ncam id: " + trafficImage0.get("camera_id");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        Picasso.with(this).load(imageUrl).into(image1);
    }

    public static String getTrafficString(){

        try {
            trafficUrl = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new TrafficImageQueryTask().execute(trafficUrl);

        return trafficString;

    }
}
