package com.tehosiewdai.gojbboh.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tehosiewdai.gojbboh.R;

import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    
    TextView data;
    String public_hols = "thursday, 30 March 2019";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        data = (TextView) findViewById(R.id.nextHoliday);

        TextView date1 = findViewById(R.id.date_1);
        if(public_hols == currentDate){
            date1.setText(currentDate);
            date1.setTextColor(0xffff0000);
        }
        else
            date1.setText(currentDate);
            date1.setTextColor(0xff000000);
    }
}
