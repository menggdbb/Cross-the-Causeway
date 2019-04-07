package com.tehosiewdai.gojbboh.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.entity.PublicHoliday;

import java.util.ArrayList;

public class CalendarListAdapter extends ArrayAdapter<PublicHoliday> {

    public CalendarListAdapter(Activity context, ArrayList<PublicHoliday> publicHolidays){
        super(context, 0, publicHolidays);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.calendar_list_item, parent, false);
        }

        PublicHoliday publicHoliday = getItem(position);

        TextView dateTextView = listItemView.findViewById(R.id.list_holiday_date);
        TextView nameTextView = listItemView.findViewById(R.id.list_holiday_name);
        TextView countryTextView = listItemView.findViewById(R.id.list_holiday_country);

        if (publicHoliday != null) {
            dateTextView.setText(publicHoliday.getDate());
            nameTextView.setText(publicHoliday.getName());
            countryTextView.setText(publicHoliday.getCountry());
        }

        return listItemView;
    }
}
