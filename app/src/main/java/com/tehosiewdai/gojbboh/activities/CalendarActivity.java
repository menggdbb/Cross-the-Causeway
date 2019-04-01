package com.tehosiewdai.gojbboh.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tehosiewdai.gojbboh.R;

import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Dictionary pHol_1 = new Hashtable();
        pHol_1.put("19/04/2019", "Good Friday (SG)");
        pHol_1.put("01/05/2019", "Labour Day");
        pHol_1.put("06/05/2019", "Awal Ramadan (MY)");
        pHol_1.put("19/05/2019", "Vesak Day");
        pHol_1.put("05/06/2019", "Hari Raya Puasa");
        pHol_1.put("09/08/2019", "National Day (SG)");
        pHol_1.put("11/08/2019", "Hari Raya Haji");
        pHol_1.put("31/08/2019", "Merdeka Day (MY)");
        pHol_1.put("09/09/2019", "Agong's Birthday (MY)");
        pHol_1.put("16/09/2019", "Malaysia Day");
        pHol_1.put("05/10/2019", "Hari Hol Almarhum Sultan Iskandar (MY)");
        pHol_1.put("27/10/2019", "Deepavali");
        pHol_1.put("09/11/2019", "Prophet Muhammad's Birthday (MY)");
        pHol_1.put("25/12/2019", "Christmas Day");

        //Main Calendar Date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String shortDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());

        TextView date1 = findViewById(R.id.date_1);
        TextView nextHol = findViewById(R.id.nextHoliday);
        TextView nextNextHol = findViewById(R.id.nextNextHoliday);
        TextView pubHol = findViewById(R.id.p_hol_name);
        date1.setText(currentDate);

        //Calendar colour change on public holidays
        if (((Hashtable) pHol_1).containsKey(shortDate)) {
            date1.setTextColor(0xffff0000);
            pubHol.setText(pHol_1.get(shortDate).toString());
            pubHol.setTextColor(0xffff0000);
            nextHol.setText(pHol_1.elements().nextElement().toString());
            nextNextHol.setText(pHol_1.elements().nextElement().toString());
        } else {
            date1.setTextColor(0xff000000);
            pubHol.setText("Not a Public Holiday");
            nextHol.setText(pHol_1.elements().nextElement().toString());
            nextNextHol.setText(pHol_1.elements().nextElement().toString());
        }
    }
}
