package com.tehosiewdai.gojbboh.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tehosiewdai.gojbboh.R;

import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    
    String[] pHol = {"Good Friday", "Labour Day", "Hari Raya Puasa", "National Day", "Hari Raya Haji", "Deepavali", "Christmas Day"};
    String[] pHol_date = {"Friday, 19 April 2019", "Wednesday, 1 May 2019", "Sunday, 19 May 2019", "Wednesday, 5 June 2019",
            "Friday, 9 August 2019", "Sunday, 11 August 2019", "Sunday, 27 October 2019", "Wednesday, 25 December 2019"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //Main Calendar Date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView date1 = findViewById(R.id.date_1);
        TextView nextHol = findViewById(R.id.nextHoliday);
        TextView nextNextHol = findViewById(R.id.nextNextHoliday);
        TextView pubHol = findViewById(R.id.p_hol_name);
        date1.setText(currentDate);

//        //Calendar colour change on public holidays
//        int i = 0;
//        while (i < pHol_date.length){
//            if (pHol_date[i] == currentDate) {
//                date1.setTextColor(0xffff0000);
//                pubHol.setText(pHol[i]);
//                nextHol.setText(pHol[i+1]);
//                nextNextHol.setText(pHol[i+2]);
//                i++;
//            } else
//                date1.setTextColor(0xff000000);
//
//        }

    }
}
