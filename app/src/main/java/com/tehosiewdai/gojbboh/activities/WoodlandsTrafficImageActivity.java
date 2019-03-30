package com.tehosiewdai.gojbboh.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.entity.TrafficObject;
import com.tehosiewdai.gojbboh.utilities.TrafficImageAsyncTask;

public class WoodlandsTrafficImageActivity extends AppCompatActivity implements TrafficImageAsyncTask.TrafficImageTaskCallback {

    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woodlands_traffic_image);

        imageView6 = findViewById(R.id.image_view6);
        imageView7 = findViewById(R.id.image_view7);
        imageView8 = findViewById(R.id.image_view8);

        new TrafficImageAsyncTask(this).execute();
    }

    @Override
    public void onPreExecuteTrafficTask() {}

    @Override
    public void onPostExecuteTrafficTask(TrafficObject[] result) {
        Picasso.with(this).load(result[3].getImageUrl()).into(imageView6);
        Picasso.with(this).load(result[4].getImageUrl()).into(imageView7);
        Picasso.with(this).load(result[5].getImageUrl()).into(imageView8);
    }
}
