package com.tehosiewdai.gojbboh.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.entity.TrafficImage;
import com.tehosiewdai.gojbboh.utilities.TrafficImageAsyncTask;

public class TuasTrafficImageActivity extends AppCompatActivity implements TrafficImageAsyncTask.TrafficImageTaskCallback {

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;

    private TextView datetime1;
    private TextView datetime2;
    private TextView datetime3;

    private RelativeLayout info1;
    private RelativeLayout info2;
    private RelativeLayout info3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuas_traffic_image);

        image1 = findViewById(R.id.tuas_image_1);
        image2 = findViewById(R.id.tuas_image_2);
        image3 = findViewById(R.id.tuas_image_3);

        datetime1 = findViewById(R.id.tuas_image_1_datetime);
        datetime2 = findViewById(R.id.tuas_image_2_datetime);
        datetime3 = findViewById(R.id.tuas_image_3_datetime);

        info1 = findViewById(R.id.tuas_image_1_info);
        info2 = findViewById(R.id.tuas_image_2_info);
        info3 = findViewById(R.id.tuas_image_3_info);

        new TrafficImageAsyncTask(this).execute();
    }

    @Override
    public void onPreExecuteTrafficTask() {
    }

    @Override
    public void onPostExecuteTrafficTask(TrafficImage[] result) {

        info1.setVisibility(View.VISIBLE);
        info2.setVisibility(View.VISIBLE);
        info3.setVisibility(View.VISIBLE);

        for (TrafficImage trafficImage : result) {
            if (trafficImage.getCameraId().equals("4703")) {
                Picasso.with(this).load(trafficImage.getImageUrl()).placeholder(R.drawable.fff).into(image1);
                datetime1.setText(trafficImage.getDatetime());
            } else if (trafficImage.getCameraId().equals("4713")) {
                Picasso.with(this).load(trafficImage.getImageUrl()).placeholder(R.drawable.fff).into(image2);
                datetime2.setText(trafficImage.getDatetime());
            } else if (trafficImage.getCameraId().equals("4712")) {
                Picasso.with(this).load(trafficImage.getImageUrl()).placeholder(R.drawable.fff).into(image3);
                datetime3.setText(trafficImage.getDatetime());
            }
        }
    }
}
