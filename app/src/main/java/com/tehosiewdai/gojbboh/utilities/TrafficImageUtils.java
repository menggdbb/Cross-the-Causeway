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
    private static String trafficString = null;
    private static String[] urlArray = new String[10];

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
                try {
                    parseJSON(results);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                trafficString = "null";
            }
        }
    }

    public static String getImageUrl (int i) {
        return urlArray[i];
    }

    private static void parseJSON(String results) throws JSONException {

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

        JSONArray cameras = null;

        try {
            cameras = trafficImages.getJSONObject(0).getJSONArray("cameras");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0; i < 87; i++) {
            if (cameras.getJSONObject(i).get("camera_id").equals("1701")) {
                urlArray[0] = (String) cameras.getJSONObject(i).get("image");
            } else if (cameras.getJSONObject(i).get("camera_id").equals("1702")){
                urlArray[1] = (String) cameras.getJSONObject(i).get("image");
            } else if (cameras.getJSONObject(i).get("camera_id").equals("1704")){
                urlArray[2] = (String) cameras.getJSONObject(i).get("image");
            } else if (cameras.getJSONObject(i).get("camera_id").equals("1705")){
                urlArray[3] = (String) cameras.getJSONObject(i).get("image");
            } else if (cameras.getJSONObject(i).get("camera_id").equals("1706")){
                urlArray[4] = (String) cameras.getJSONObject(i).get("image");
            } else if (cameras.getJSONObject(i).get("camera_id").equals("1707")){
                urlArray[5] = (String) cameras.getJSONObject(i).get("image");
            } else if (cameras.getJSONObject(i).get("camera_id").equals("1709")){
                urlArray[6] = (String) cameras.getJSONObject(i).get("image");
            } else if (cameras.getJSONObject(i).get("camera_id").equals("1711")){
                urlArray[7] = (String) cameras.getJSONObject(i).get("image");
            } else if (cameras.getJSONObject(i).get("camera_id").equals("2701")){
                urlArray[8] = (String) cameras.getJSONObject(i).get("image");
            } else if (cameras.getJSONObject(i).get("camera_id").equals("2702")){
                urlArray[9] = (String) cameras.getJSONObject(i).get("image");
            }
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

    private static String getImage(String results, int i, int cameraID) {

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

            trafficImage0 = trafficImages.getJSONObject(0).getJSONArray("cameras").getJSONObject(i);
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

        try {
            if (trafficImage0.get("camera_id").equals(cameraID)) {
                return imageUrl;
            } else {
                return imageUrl;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return imageUrl;

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
