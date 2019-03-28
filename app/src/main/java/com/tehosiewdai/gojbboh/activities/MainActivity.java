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

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.utilities.TrafficImageAsyncTask;

public class MainActivity extends AppCompatActivity implements TrafficImageAsyncTask.TrafficImageTaskCallback{

    private ImageView woodlandsHomeImage;
    private ImageView tuasHomeImage;
    private Button woodlandsButton;
    private Button tuasButton;
    private ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        woodlandsHomeImage = (ImageView) findViewById(R.id.woodlands_home_image);
        tuasHomeImage = (ImageView) findViewById(R.id.tuas_home_image);
        woodlandsButton = (Button) findViewById(R.id.woodlands_button);
        tuasButton = (Button) findViewById(R.id.tuas_button);
        loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        new TrafficImageAsyncTask(this).execute();

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
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onPreExecute() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPostExecute(String[] result) {
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
