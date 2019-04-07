package com.tehosiewdai.gojbboh.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.controller.MenuController;

public class MenuActivity extends AppCompatActivity {

    private ImageView calendarImage;
    private ImageView exchangeImage;
    private ImageView locationImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        calendarImage = findViewById(R.id.calendar_image);
        exchangeImage = findViewById(R.id.exchange_image);
        locationImage = findViewById(R.id.location_image);

        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        MenuController menuController = new MenuController(this);
        menuController.setIntents();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public ImageView getCalendarImage() {
        return calendarImage;
    }

    public ImageView getExchangeImage() {
        return exchangeImage;
    }

    public ImageView getLocationImage() {
        return locationImage;
    }
}
