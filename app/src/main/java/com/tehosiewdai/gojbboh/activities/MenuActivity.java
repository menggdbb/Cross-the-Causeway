package com.tehosiewdai.gojbboh.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.tehosiewdai.gojbboh.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageView calendarImage = findViewById(R.id.calendar_image);
        ImageView exchangeImage = findViewById(R.id.exchange_image);
        ImageView locationImage = findViewById(R.id.location_image);

        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MenuActivity.this;
                Class destinationActivity = CalendarActivity.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });

        exchangeImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Context context = MenuActivity.this;
                Class destinationActivity = CurrencyActivity.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });

        locationImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Context context = MenuActivity.this;
                Class destinationActivity = MoneyChangerActivity.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
