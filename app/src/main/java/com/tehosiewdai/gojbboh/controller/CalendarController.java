package com.tehosiewdai.gojbboh.controller;

import android.annotation.SuppressLint;
import android.graphics.Color;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.activities.CalendarActivity;
import com.tehosiewdai.gojbboh.adapters.CalendarListAdapter;
import com.tehosiewdai.gojbboh.entity.PublicHoliday;
import com.tehosiewdai.gojbboh.utilities.CalendarReader;
import com.tehosiewdai.gojbboh.utilities.FileReader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarController {

    private static final int PUBLIC_HOLIDAY_FILE_INDEX = R.raw.public_holidays;

    private ArrayList<PublicHoliday> publicHolidays;

    private CalendarActivity activity;

    public CalendarController(CalendarActivity activity){
        this.activity = activity;
        publicHolidays = new ArrayList<>();
    }

    public void displayCalendar(){
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

    private void setPublicHolidayOnCalendar() {
        activity.getPublicHolidayTextView().setText(publicHolidays.get(0).getName());
        publicHolidays.remove(0);
        activity.getMonthYearTextView().setTextColor(Color.RED);
        activity.getDateTextView().setTextColor(Color.RED);
        activity.getDayTextView().setTextColor(Color.RED);
        activity.getPublicHolidayTextView().setTextColor(Color.RED);
    }
}
