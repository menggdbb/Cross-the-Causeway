package com.tehosiewdai.crossthecauseway.controller;

import android.content.Intent;
import android.view.View;

import com.tehosiewdai.crossthecauseway.activities.CalendarActivity;
import com.tehosiewdai.crossthecauseway.activities.CurrencyActivity;
import com.tehosiewdai.crossthecauseway.activities.MenuActivity;
import com.tehosiewdai.crossthecauseway.activities.MoneyChangerActivity;

/**
 * Controller class for the menu.
 */
public class MenuController {

    /**
     * MenuACtivity that uses the controller.
     */
    private MenuActivity activity;

    /**
     * Instantiates the controller.
     *
     * @param activity activity that instantiated the controller.
     */
    public MenuController(MenuActivity activity){

        this.activity = activity;

    }

    /**
     * Sets click event listeners on the image icons sets intents to another activity.
     */
    public void setIntents(){
        activity.getCalendarImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(activity, CalendarActivity.class);
                activity.startActivity(startIntent);
            }
        });

        activity.getExchangeImage().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(activity, CurrencyActivity.class);
                activity.startActivity(startIntent);
            }
        });

        activity.getLocationImage().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(activity, MoneyChangerActivity.class);
                activity.startActivity(startIntent);
            }
        });
    }

}
