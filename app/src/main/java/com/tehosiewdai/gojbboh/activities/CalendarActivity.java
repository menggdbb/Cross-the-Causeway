package com.tehosiewdai.gojbboh.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.controller.CalendarController;

public class CalendarActivity extends AppCompatActivity {

    private TextView monthYearTextView;
    private TextView dateTextView;
    private TextView publicHolidayTextView;
    private TextView dayTextView;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        monthYearTextView = findViewById(R.id.month_year);
        dateTextView = findViewById(R.id.date);
        publicHolidayTextView = findViewById(R.id.p_hol_name);
        dayTextView = findViewById(R.id.day);

        listView = findViewById(R.id.holiday_list_view);

        CalendarController calendarController = new CalendarController(this);
        calendarController.displayCalendar();

    }

    public ListView getListView() {
        return listView;
    }

    public TextView getDateTextView() {
        return dateTextView;
    }

    public TextView getDayTextView() {
        return dayTextView;
    }

    public TextView getMonthYearTextView() {
        return monthYearTextView;
    }

    public TextView getPublicHolidayTextView() {
        return publicHolidayTextView;
    }
}
