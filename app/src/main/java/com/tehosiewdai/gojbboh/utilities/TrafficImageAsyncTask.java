package com.tehosiewdai.gojbboh.utilities;

import android.os.AsyncTask;
import android.util.Log;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.entity.TrafficObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class TrafficImageAsyncTask extends AsyncTask<Void, Void, TrafficObject[]> {

    private final String TAG = this.getClass().getSimpleName();

    private final String TRAFFIC_URL = "https://api.data.gov.sg/v1/transport/traffic-images";

    private String[] cameraIds = {"2701", "2702", "2704", "4712", "4713", "4703"};

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
    protected TrafficObject[] doInBackground(Void... voids) {
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
    protected void onPostExecute(TrafficObject[] result) {
        if (callback != null && result != null){
            callback.onPostExecuteTrafficTask(result);
        }
    }

    private TrafficObject[] parseJSON(String results) throws JSONException {

        TrafficObject[] trafficObjects = new TrafficObject[cameraIds.length];
        int counter = 0;
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
                if (counter == cameraIds.length){
                    break;
                }

                if (Arrays.asList(cameraIds).contains((String) cameras.getJSONObject(i).get("camera_id"))){
                    trafficObjects[counter] = new TrafficObject(
                            (String) cameras.getJSONObject(i).get("camera_id"),
                            (String) cameras.getJSONObject(i).get("image"),
                            //TODO display nicer date function
                            formatDatetime((String) cameras.getJSONObject(i).get("timestamp")));
                    counter++;
                }

            }
        }

        return trafficObjects;
    }

    private String formatDatetime(String datetime){
        String date = datetime.substring(0,10);
        String time;
        if (Integer.valueOf(datetime.substring(11,13)) < 13 ){
            time = datetime.substring(11,16) + "am";
        } else {
            int hour = Integer.valueOf(datetime.substring(11,13));
            time = (hour - 12) + datetime.substring(13,16) + "pm";
        }
        return date + " " + time;
    }


    public interface TrafficImageTaskCallback {
        void onPreExecuteTrafficTask();
        void onPostExecuteTrafficTask(TrafficObject[] result);
    }
}
