package com.tehosiewdai.gojbboh.controller;

import android.content.Intent;
import android.view.View;

import com.tehosiewdai.gojbboh.activities.CalendarActivity;
import com.tehosiewdai.gojbboh.activities.CurrencyActivity;
import com.tehosiewdai.gojbboh.activities.MenuActivity;
import com.tehosiewdai.gojbboh.activities.MoneyChangerActivity;

public class MenuController {

    private MenuActivity activity;

    public MenuController(MenuActivity activity){

        this.activity = activity;

    }

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
