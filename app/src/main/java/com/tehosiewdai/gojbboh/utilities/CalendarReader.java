package com.tehosiewdai.gojbboh.utilities;

import android.util.Log;

import com.tehosiewdai.gojbboh.entity.PublicHoliday;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Reader to extract public holidays from JSON string.
 */
public class CalendarReader {

    /**
     * Simple name of the class.
     */
    private static final String TAG = CalendarReader.class.getSimpleName();

    /**
     * Gets the JSON string and returns a list of public holidays.
     *
     * @param results JSON string to be parsed.
     * @return a list of public holidays.
     */
    public static ArrayList<PublicHoliday> getPublicHolidays(String results) {
        ArrayList<PublicHoliday> publicHolidays = new ArrayList<>();

        try {
            JSONArray publicHolidayList = new JSONArray(results);
            for (int i = 0; i < publicHolidayList.length(); i++) {
                JSONObject publicHoliday = publicHolidayList.getJSONObject(i);
                publicHolidays.add(new PublicHoliday(
                        publicHoliday.getString("date"),
                        publicHoliday.getString("name"),
                        publicHoliday.getString("country")));
            }
        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }

        return publicHolidays;
    }
}
