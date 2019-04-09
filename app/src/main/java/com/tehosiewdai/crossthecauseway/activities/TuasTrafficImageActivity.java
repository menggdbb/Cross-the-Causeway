package com.tehosiewdai.crossthecauseway.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.crossthecauseway.R;
import com.tehosiewdai.crossthecauseway.controller.TrafficImageController;

/**
 * Activity that opens upon selection at the Woodlands traffic image in the homepage.
 * This activity acts as the display for all Woodlands traffic images for this application.
 */
public class TuasTrafficImageActivity extends AppCompatActivity {

    /**
     * Called when activity is starting.
     *
     * @param savedInstanceState This Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           This value may be null;
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuas_traffic_image);

        ImageView image1 = findViewById(R.id.tuas_image_1);
        ImageView image2 = findViewById(R.id.tuas_image_2);
        ImageView image3 = findViewById(R.id.tuas_image_3);

        TextView datetime1 = findViewById(R.id.tuas_image_1_datetime);
        TextView datetime2 = findViewById(R.id.tuas_image_2_datetime);
        TextView datetime3 = findViewById(R.id.tuas_image_3_datetime);

        //Sets the images and date time passed from the intents.
        Picasso
                .with(this)
                .load(getIntent().getStringExtra(TrafficImageController.TUAS_SECOND_LINK_ID))
                .placeholder(R.drawable.maintainance)
                .into(image1);

        datetime1.setText(getIntent().getStringExtra(TrafficImageController.TUAS_SECOND_LINK_ID_DATETIME));

        Picasso
                .with(this)
                .load(getIntent().getStringExtra(TrafficImageController.TUAS_CHECKPOINT_ID))
                .placeholder(R.drawable.maintainance)
                .into(image2);

        datetime2.setText(getIntent().getStringExtra(TrafficImageController.TUAS_CHECKPOINT_ID_DATETIME));

        Picasso
                .with(this)
                .load(getIntent().getStringExtra(TrafficImageController.AFTER_TUAS_WEST_ROAD_ID))
                .placeholder(R.drawable.maintainance)
                .into(image3);

        datetime3.setText(getIntent().getStringExtra(TrafficImageController.AFTER_TUAS_WEST_ROAD_ID_DATETIME));

    }
}
