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

/**
 * Adapter to hold the list of public holidays.
 */
public class CalendarListAdapter extends ArrayAdapter<PublicHoliday> {

    /**
     * Instantiates the adapter.
     *
     * @param context        activity that is using this adapter.
     * @param publicHolidays list of public holidays.
     */
    public CalendarListAdapter(Activity context, ArrayList<PublicHoliday> publicHolidays) {
        super(context, 0, publicHolidays);
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView This value may be null.
     * @param parent      This value must never be null.
     * @return This value will never be null.
     */
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
