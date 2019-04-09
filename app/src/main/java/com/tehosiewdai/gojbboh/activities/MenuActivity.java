package com.tehosiewdai.gojbboh.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.controller.MenuController;

/**
 * Activity that opens upon called by user to open the menu at the homepage.
 * This activity acts as the menu for this application.
 */
public class MenuActivity extends AppCompatActivity {

    /**
     * ImageView variable to hold the image of the Calendar icon.
     */
    private ImageView calendarImage;

    /**
     * ImageView variable to hold the image of the Exchange Rate icon.
     */
    private ImageView exchangeImage;

    /**
     * ImageView variable to hold the image of the Money Changer locations icon.
     */
    private ImageView locationImage;

    /**
     * Called when activity is starting.
     *
     * @param savedInstanceState This Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           This value may be null;
     */
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

        //Creates an instance of MenuController and sets intents upon selection of menu icons.
        MenuController menuController = new MenuController(this);
        menuController.setIntents();
    }

    /**
     * Event Listener for menu options upon selected.
     *
     * @param item that is selected in the menu.
     * @return true once item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * gets the ImageView variable to hold the image of the Calendar icon.
     *
     * @return ImageView variable to hold the image of the Calendar icon.
     */
    public ImageView getCalendarImage() {
        return calendarImage;
    }

    /**
     * Gets the ImageView variable to hold the image of the Exchange Rate icon.
     *
     * @return ImageView variable to hold the image of the Exchange Rate icon.
     */
    public ImageView getExchangeImage() {
        return exchangeImage;
    }

    /**
     * Gets the ImageView variable to hold the image of the Money Changer locations icon.
     *
     * @return ImageView variable to hold the image of the Money Changer locations icon.
     */
    public ImageView getLocationImage() {
        return locationImage;
    }
}
