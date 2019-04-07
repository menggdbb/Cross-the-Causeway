package com.tehosiewdai.gojbboh.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.controller.TrafficImageController;

public class TuasTrafficImageActivity extends AppCompatActivity {

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

        Picasso
                .with(this)
                .load(getIntent().getStringExtra(TrafficImageController.TUAS_SECOND_LINK_ID))
                .placeholder(R.drawable.fff)
                .into(image1);

        datetime1.setText(getIntent().getStringExtra(TrafficImageController.TUAS_SECOND_LINK_ID_DATETIME));

        Picasso
                .with(this)
                .load(getIntent().getStringExtra(TrafficImageController.TUAS_CHECKPOINT_ID))
                .placeholder(R.drawable.fff)
                .into(image2);

        datetime2.setText(getIntent().getStringExtra(TrafficImageController.TUAS_CHECKPOINT_ID_DATETIME));

        Picasso
                .with(this)
                .load(getIntent().getStringExtra(TrafficImageController.AFTER_TUAS_WEST_ROAD_ID))
                .placeholder(R.drawable.fff)
                .into(image3);

        datetime3.setText(getIntent().getStringExtra(TrafficImageController.AFTER_TUAS_WEST_ROAD_ID_DATETIME));

    }
}
