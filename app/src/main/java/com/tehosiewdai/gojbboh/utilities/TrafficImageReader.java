package com.tehosiewdai.gojbboh.utilities;

import android.util.Log;

import com.tehosiewdai.gojbboh.entity.TrafficImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrafficImageReader {

    private static final String TAG = TrafficImageReader.class.getSimpleName();

    public static ArrayList<TrafficImage> getTrafficImages(String results) {

        ArrayList<TrafficImage> trafficImages = new ArrayList<>();

        JSONArray cameras = null;

        try {
            JSONObject trafficObject = new JSONObject(results);
            JSONArray trafficImagesItems = trafficObject.getJSONArray("items");
            cameras = trafficImagesItems.getJSONObject(0).getJSONArray("cameras");

        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }

        if (cameras != null) {
            for (int i = 0; i < cameras.length(); i++) {
                try {
                    trafficImages.add(new TrafficImage(
                            cameras.getJSONObject(i).getString("camera_id"),
                            cameras.getJSONObject(i).getString("image"),
                            formatDatetime(cameras.getJSONObject(i).getString("timestamp"))));
                } catch (JSONException e) {
                    Log.e(TAG, String.valueOf(e));
                }
            }

        }

        return trafficImages;
    }

    private static String formatDatetime(String datetime) {
        String date = datetime.substring(0, 10);
        String time;
        if (Integer.valueOf(datetime.substring(11, 13)) < 13) {
            time = datetime.substring(11, 16) + "am";
        } else {
            int hour = Integer.valueOf(datetime.substring(11, 13));
            time = (hour - 12) + datetime.substring(13, 16) + "pm";
        }
        return date + " " + time;
    }

}
