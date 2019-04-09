package com.tehosiewdai.crossthecauseway.controller;

import android.annotation.SuppressLint;
import android.graphics.Color;

import com.tehosiewdai.crossthecauseway.R;
import com.tehosiewdai.crossthecauseway.activities.CalendarActivity;
import com.tehosiewdai.crossthecauseway.adapters.CalendarListAdapter;
import com.tehosiewdai.crossthecauseway.entity.PublicHoliday;
import com.tehosiewdai.crossthecauseway.utilities.CalendarReader;
import com.tehosiewdai.crossthecauseway.utilities.FileReader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Controller class for logic about Calendars.
 */
public class CalendarController {

    /**
     * Index of local data for public holidays.
     */
    private static final int PUBLIC_HOLIDAY_FILE_INDEX = R.raw.public_holidays;

    /**
     * Array list of public holidays.
     */
    private ArrayList<PublicHoliday> publicHolidays;

    /**
     * CalendarActivity that uses this controller.
     */
    private CalendarActivity activity;

    /**
     * Instantiates the controller.
     *
     * @param activity the activity that instantiated it.
     */
    public CalendarController(CalendarActivity activity) {
        this.activity = activity;
        publicHolidays = new ArrayList<>();
    }

    /**
     * Displays the list of public holidays and the current date.
     */
    public void displayCalendar() {
        String result = FileReader.readFile(activity, PUBLIC_HOLIDAY_FILE_INDEX);
        publicHolidays = CalendarReader.getPublicHolidays(result);

        CalendarListAdapter adapter = new CalendarListAdapter(activity, publicHolidays);
        activity.getListView().setAdapter(adapter);

        Date now = new Date();

        @SuppressLint("SimpleDateFormat") String currentDate = new SimpleDateFormat("dd MMM").format(now);

        if (currentDate.equals(publicHolidays.get(0).getDate())) {
            setPublicHolidayOnCalendar();
        }

        @SuppressLint("SimpleDateFormat") String monthYear = new SimpleDateFormat("MMMM yyyy").format(now);
        @SuppressLint("SimpleDateFormat") String date = new SimpleDateFormat("dd").format(now);
        @SuppressLint("SimpleDateFormat") String day = new SimpleDateFormat("EEEE").format(now);

        activity.getMonthYearTextView().setText(monthYear);
        activity.getDateTextView().setText(date);
        activity.getDayTextView().setText(day);

    }

    /**
     * To highlight the current date is public holiday if current date coincides with a public holiday.
     */
    private void setPublicHolidayOnCalendar() {
        activity.getPublicHolidayTextView().setText(publicHolidays.get(0).getName());
        publicHolidays.remove(0);
        activity.getMonthYearTextView().setTextColor(Color.RED);
        activity.getDateTextView().setTextColor(Color.RED);
        activity.getDayTextView().setTextColor(Color.RED);
        activity.getPublicHolidayTextView().setTextColor(Color.RED);
    }
}
