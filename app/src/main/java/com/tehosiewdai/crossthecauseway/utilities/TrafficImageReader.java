package com.tehosiewdai.crossthecauseway.utilities;

import android.util.Log;

import com.tehosiewdai.crossthecauseway.entity.TrafficImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Reader to read money changer locations.
 */
public class TrafficImageReader {

    /**
     * Simple name of the class.
     */
    private static final String TAG = TrafficImageReader.class.getSimpleName();

    /**
     * Gets the JSON string and returns the list of traffic images.
     *
     * @param results JSON string to be parsed.
     * @return list of traffic images.
     */
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

    /**
     * Formats the date and time from the date and time given from the JSON string.
     * @param datetime original date and time format.
     * @return formatted date and time.
     */
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
