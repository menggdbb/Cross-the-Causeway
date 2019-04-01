package com.tehosiewdai.gojbboh.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.controller.CalendarListAdapter;
import com.tehosiewdai.gojbboh.entity.PublicHoliday;
import com.tehosiewdai.gojbboh.utilities.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = CalendarActivity.class.getSimpleName();

    private static final int PUBLIC_HOLIDAY_FILE_INDEX = R.raw.public_holidays;

    private ArrayList<PublicHoliday> publicHolidays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        TextView monthYearTextView = findViewById(R.id.month_year);
        TextView dateTextView = findViewById(R.id.date);
        TextView publicHolidayTextView = findViewById(R.id.p_hol_name);
        TextView dayTextView = findViewById(R.id.day);

        publicHolidays = new ArrayList<>();

        loadPublicHolidayArray();

        CalendarListAdapter adapter = new CalendarListAdapter(this, publicHolidays);

        ListView listView = findViewById(R.id.holiday_list_view);
        listView.setAdapter(adapter);

        Date now = new Date();

        String currentDate = new SimpleDateFormat("dd MMM").format(now);

        if (currentDate.equals(publicHolidays.get(0).getDate())) {
            publicHolidayTextView.setText(publicHolidays.get(0).getName());
            publicHolidays.remove(0);
            monthYearTextView.setTextColor(Color.RED);
            dateTextView.setTextColor(Color.RED);
            dayTextView.setTextColor(Color.RED);
            publicHolidayTextView.setTextColor(Color.RED);
        }

        String monthYear = new SimpleDateFormat("MMMM yyyy").format(now);
        String date = new SimpleDateFormat("dd").format(now);
        String day = new SimpleDateFormat("EEEE").format(now);


        monthYearTextView.setText(monthYear);
        dateTextView.setText(date);
        dayTextView.setText(day);


    }

    private void loadPublicHolidayArray() {
        try {
            String result = FileUtils.readFile(this, PUBLIC_HOLIDAY_FILE_INDEX);
            JSONArray publicHolidayList = new JSONArray(result);
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
    }
}
