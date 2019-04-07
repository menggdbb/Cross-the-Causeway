package com.tehosiewdai.gojbboh.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.controller.TrafficImageController;

public class WoodlandsTrafficImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woodlands_traffic_image);

        ImageView image1 = findViewById(R.id.woodlands_image_1);
        ImageView image2 = findViewById(R.id.woodlands_image_2);
        ImageView image3 = findViewById(R.id.woodlands_image_3);

        TextView datetime1 = findViewById(R.id.woodlands_image_1_datetime);
        TextView datetime2 = findViewById(R.id.woodlands_image_2_datetime);
        TextView datetime3 = findViewById(R.id.woodlands_image_3_datetime);

        Picasso
                .with(this)
                .load(getIntent().getStringExtra(TrafficImageController.WOODLANDS_CAUSEWAY_ID))
                .placeholder(R.drawable.fff)
                .into(image1);

        datetime1.setText(getIntent().getStringExtra(TrafficImageController.WOODLANDS_CHECKPOINT_ID_DATETIME));

        Picasso
                .with(this)
                .load(getIntent().getStringExtra(TrafficImageController.WOODLANDS_CHECKPOINT_ID))
                .placeholder(R.drawable.fff)
                .into(image2);

        datetime2.setText(getIntent().getStringExtra(TrafficImageController.WOODLANDS_CAUSEWAY_ID_DATETIME));

        Picasso
                .with(this)
                .load(getIntent().getStringExtra(TrafficImageController.WOODLANDS_FLYOVER_ID))
                .placeholder(R.drawable.fff)
                .into(image3);

        datetime3.setText(getIntent().getStringExtra(TrafficImageController.WOODLANDS_FLYOVER_ID_DATETIME));
    }
}
