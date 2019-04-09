package com.tehosiewdai.crossthecauseway.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.tehosiewdai.crossthecauseway.R;
import com.tehosiewdai.crossthecauseway.controller.CalendarController;

/**
 * Activity that opens upon selection at the menu.
 * This activity acts as the Calendar Page for this application.
 */
public class CalendarActivity extends AppCompatActivity {

    /**
     * TextView variable to hold the current month and year on the Calendar page.
     */
    private TextView monthYearTextView;

    /**
     * TextView variable to hold the current date on the Calendar Page.
     */
    private TextView dateTextView;

    /**
     * TextView variable to hold the name of the public holiday if current date is a public holiday.
     */
    private TextView publicHolidayTextView;

    /**
     * TextView variable to hold the current day on the Calendar page.
     */
    private TextView dayTextView;

    /**
     * ListView variable to hold the contents of public holiday name, date and location.
     */
    private ListView listView;


    /**
     * Called when activity is starting.
     *
     * @param savedInstanceState This Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           This value may be null;
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        monthYearTextView = findViewById(R.id.month_year);
        dateTextView = findViewById(R.id.date);
        publicHolidayTextView = findViewById(R.id.p_hol_name);
        dayTextView = findViewById(R.id.day);

        listView = findViewById(R.id.holiday_list_view);

        //creates a new instance of CalendarController and calls to retrieve the API in the background.
        CalendarController calendarController = new CalendarController(this);
        calendarController.displayCalendar();
    }

    /**
     * Gets the ListView variable to hold the contents of public holiday name, date and location.
     *
     * @return ListView variable to hold the contents of public holiday name, date and location.
     */
    public ListView getListView() {
        return listView;
    }

    /**
     * Gets the TextView variable to hold the current date on the Calendar Page.
     *
     * @return TextView variable to hold the current date on the Calendar Page.
     */
    public TextView getDateTextView() {
        return dateTextView;
    }

    /**
     * Gets the TextView variable to hold the current day on the Calendar page.
     *
     * @return TextView variable to hold the current day on the Calendar page.
     */
    public TextView getDayTextView() {
        return dayTextView;
    }

    /**
     * Gets the TextView variable to hold the current month and year on the Calendar page.
     *
     * @return TextView variable to hold the current month and year on the Calendar page.
     */
    public TextView getMonthYearTextView() {
        return monthYearTextView;
    }

    /**
     * Gets the TextView variable to hold the name of the public holiday if current date is a public holiday.
     *
     * @return TextView variable to hold the name of the public holiday if current date is a public holiday.
     */
    public TextView getPublicHolidayTextView() {
        return publicHolidayTextView;
    }
}
