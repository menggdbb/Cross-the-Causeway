package com.tehosiewdai.gojbboh.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.utilities.TrafficImageAsyncTask;
import com.tehosiewdai.gojbboh.utilities.WeatherAsyncTask;

public class MainActivity extends AppCompatActivity implements
        TrafficImageAsyncTask.TrafficImageTaskCallback, WeatherAsyncTask.WeatherTaskCallback {

    private ImageView woodlandsHomeImage;
    private ImageView tuasHomeImage;
    private ProgressBar loadingIndicator;
    private TextView woodlandsWeatherDescription;
    private TextView tuasWeatherDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        woodlandsHomeImage = (ImageView) findViewById(R.id.woodlands_home_image);
        tuasHomeImage = (ImageView) findViewById(R.id.tuas_home_image);

        Button woodlandsButton = (Button) findViewById(R.id.woodlands_button);
        Button tuasButton = (Button) findViewById(R.id.tuas_button);

        loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator_traffic);

        woodlandsWeatherDescription = (TextView) findViewById(R.id.woodlands_description);
        tuasWeatherDescription = (TextView) findViewById(R.id.tuas_description);

        new TrafficImageAsyncTask(this).execute();
        new WeatherAsyncTask(this).execute();

        tuasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuasHomeImage.setVisibility(View.VISIBLE);
            }
        });

        woodlandsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuasHomeImage.setVisibility(View.INVISIBLE);
            }
        });

        woodlandsHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destinationActivity = WoodlandsTrafficImageActivity.class;
                Intent startTrafficActivityIntent = new Intent(context, destinationActivity);
                startActivity(startTrafficActivityIntent);
            }
        });

        tuasHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destinationActivity = TuasTrafficImageActivity.class;
                Intent startTrafficActivityIntent = new Intent(context, destinationActivity);
                startActivity(startTrafficActivityIntent);
            }
        });

    }

    @Override
    public void onPreExecuteTrafficTask() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPostExecuteTrafficTask(String[] result) {
        loadingIndicator.setVisibility(View.INVISIBLE);
        Picasso
                .with(this)
                .load(result[1])
                .placeholder(R.drawable.fff)
                .into(woodlandsHomeImage);
        Picasso
                .with(this)
                .load(result[3])
                .placeholder(R.drawable.fff)
                .into(tuasHomeImage);
    }

    @Override
    public void onPreExecuteWeatherTask() {

    }

    @Override
    public void onPostExecuteWeatherTask(String[] result) {
        woodlandsWeatherDescription.setText(result[0]);
        tuasWeatherDescription.setText(result[1]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == R.id.menu){

            Context context = MainActivity.this;
            Class destinationActivity = MenuActivity.class;
            Intent startMenuActivity = new Intent(context, destinationActivity);

            startActivity(startMenuActivity);
        }
        return true;
    }

}
