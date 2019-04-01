package com.tehosiewdai.gojbboh.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.controller.CalendarListAdapter;
import com.tehosiewdai.gojbboh.entity.PublicHoliday;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ArrayList<PublicHoliday> publicHolidays = new ArrayList<>();
        publicHolidays.add(new PublicHoliday("19 Apr", "Good Friday", "SG"));
        publicHolidays.add(new PublicHoliday("01 May", "Labour Day", "-"));
        publicHolidays.add(new PublicHoliday("06 May", "Awal Ramadan", "MY"));
        publicHolidays.add(new PublicHoliday("19 May", "Vesak Day", "-"));
        publicHolidays.add(new PublicHoliday("06 May", "Hari Raya Puasa", "-"));
        publicHolidays.add(new PublicHoliday("09 Aug", "National Day", "SG"));
        publicHolidays.add(new PublicHoliday("11 Aug", "Hari Raya Haji", "-"));
        publicHolidays.add(new PublicHoliday("31 Aug", "Merdeka Day", "MY"));
        publicHolidays.add(new PublicHoliday("09 Sep", "Agong's Birthday", "MY"));
        publicHolidays.add(new PublicHoliday("16 Sep", "Malaysia Day", "MY"));
        publicHolidays.add(new PublicHoliday("05 Oct", "Hari Hol Almarhum Sultan Iskandar", "MY"));
        publicHolidays.add(new PublicHoliday("27 Oct", "Deepavali", "-"));
        publicHolidays.add(new PublicHoliday("09 Nov", "Prophet Muhammad's Birthday", "MY"));
        publicHolidays.add(new PublicHoliday("25 Dec", "Christmas Day", "-"));

        CalendarListAdapter adapter = new CalendarListAdapter(this, publicHolidays);

        ListView listView = findViewById(R.id.holiday_list_view);
        listView.setAdapter(adapter);


        String currentDate = new SimpleDateFormat("dd MMM").format(new Date());


        TextView date1 = findViewById(R.id.date_1);
//        TextView pubHol = findViewById(R.id.p_hol_name);
        date1.setText(currentDate);


    }
}
