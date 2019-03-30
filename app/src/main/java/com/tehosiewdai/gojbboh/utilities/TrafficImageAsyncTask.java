package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TrafficImageAsyncTask extends AsyncTask<Void, Void, String[]> {

    private final String TAG = this.getClass().getSimpleName();

    private final String TRAFFIC_URL = "https://api.data.gov.sg/v1/transport/traffic-images";

    private TrafficImageTaskCallback callback;

    public TrafficImageAsyncTask(TrafficImageTaskCallback callback){
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null){
            callback.onPreExecuteTrafficTask();
        }
    }

    @Override
    protected String[] doInBackground(Void... voids) {
        try {
            URL url = new URL(TRAFFIC_URL);
            String results = NetworkUtils.getResponseFromHttpUrl(url);
            return parseJSON(results);
        } catch (MalformedURLException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }
        return null;
    }

    @Override
    protected void onPostExecute(String[] result) {
        if (callback != null && result != null){
            callback.onPostExecuteTrafficTask(result);
        }
    }

    private String[] parseJSON(String results) throws JSONException {

        String[] urlArray = new String[10];

        JSONArray cameras = null;

        try {
            JSONObject trafficObject = new JSONObject(results);
            JSONArray trafficImages = trafficObject.getJSONArray("items");
            cameras = trafficImages.getJSONObject(0).getJSONArray("cameras");

        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }
        if (cameras != null){
            for(int i=0; i < cameras.length(); i++) {
                if (cameras.getJSONObject(i).get("camera_id").equals("1701")) {
                    urlArray[0] = (String) cameras.getJSONObject(i).get("image");
                } else if (cameras.getJSONObject(i).get("camera_id").equals("1702")) {
                    urlArray[1] = (String) cameras.getJSONObject(i).get("image");
                } else if (cameras.getJSONObject(i).get("camera_id").equals("1704")) {
                    urlArray[2] = (String) cameras.getJSONObject(i).get("image");
                } else if (cameras.getJSONObject(i).get("camera_id").equals("1705")) {
                    urlArray[3] = (String) cameras.getJSONObject(i).get("image");
                } else if (cameras.getJSONObject(i).get("camera_id").equals("1706")) {
                    urlArray[4] = (String) cameras.getJSONObject(i).get("image");
                } else if (cameras.getJSONObject(i).get("camera_id").equals("1707")) {
                    urlArray[5] = (String) cameras.getJSONObject(i).get("image");
                } else if (cameras.getJSONObject(i).get("camera_id").equals("1709")) {
                    urlArray[6] = (String) cameras.getJSONObject(i).get("image");
                } else if (cameras.getJSONObject(i).get("camera_id").equals("1711")) {
                    urlArray[7] = (String) cameras.getJSONObject(i).get("image");
                } else if (cameras.getJSONObject(i).get("camera_id").equals("2701")) {
                    urlArray[8] = (String) cameras.getJSONObject(i).get("image");
                } else if (cameras.getJSONObject(i).get("camera_id").equals("2702")) {
                    urlArray[9] = (String) cameras.getJSONObject(i).get("image");
                }
            }
        }

        return urlArray;
    }

    public interface TrafficImageTaskCallback {
        void onPreExecuteTrafficTask();
        void onPostExecuteTrafficTask(String[] result);
    }
}
