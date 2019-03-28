package com.tehosiewdai.gojbboh.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.utilities.TrafficImageUtils;

public class MainActivity extends AppCompatActivity {

//    private TextView mTextView;

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextView = (TextView) findViewById(R.id.text1);

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destinationActivity = WoodlandsTrafficImageActivity.class;
                Intent startTrafficActivityIntent = new Intent(context, destinationActivity);
                startActivity(startTrafficActivityIntent);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destinationActivity = TuasTrafficImageActivity.class;
                Intent startTrafficActivityIntent = new Intent(context, destinationActivity);
                startActivity(startTrafficActivityIntent);
            }
        });

        makeTrafficQuery();
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeTrafficQuery();
    }

    private void makeTrafficQuery(){
        Picasso.with(this).load(TrafficImageUtils.getTrafficString()).into(image1);
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
