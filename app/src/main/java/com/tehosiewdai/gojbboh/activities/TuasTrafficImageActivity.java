package com.tehosiewdai.gojbboh.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.entity.TrafficObject;
import com.tehosiewdai.gojbboh.utilities.TrafficImageAsyncTask;

public class TuasTrafficImageActivity extends AppCompatActivity implements TrafficImageAsyncTask.TrafficImageTaskCallback {

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuas_traffic_image);

        imageView1 = findViewById(R.id.image_view1);
        imageView2 = findViewById(R.id.image_view2);
        imageView3 = findViewById(R.id.image_view3);

        new TrafficImageAsyncTask(this).execute();
    }

    @Override
    public void onPreExecuteTrafficTask() {}

    @Override
    public void onPostExecuteTrafficTask(TrafficObject[] result) {
        Picasso.with(this).load(result[0].getImageUrl()).into(imageView1);
        Picasso.with(this).load(result[1].getImageUrl()).into(imageView2);
        Picasso.with(this).load(result[2].getImageUrl()).into(imageView3);
    }
}
