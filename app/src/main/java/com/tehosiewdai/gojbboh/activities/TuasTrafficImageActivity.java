package com.tehosiewdai.gojbboh.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.utilities.TrafficImageUtils;

public class TuasTrafficImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuas_traffic_image);

        ImageView imageView1 = findViewById(R.id.image_view1);
        String url1 = TrafficImageUtils.getImageUrl(0);
        Picasso.with(this).load(url1).into(imageView1);

        ImageView imageView2 = findViewById(R.id.image_view2);
        String url2 = TrafficImageUtils.getImageUrl(1);
        Picasso.with(this).load(url2).into(imageView2);

        ImageView imageView3 = findViewById(R.id.image_view3);
        String url3 = TrafficImageUtils.getImageUrl(2);
        Picasso.with(this).load(url3).into(imageView3);

        ImageView imageView4 = findViewById(R.id.image_view4);
        String url4 = TrafficImageUtils.getImageUrl(3);
        Picasso.with(this).load(url4).into(imageView4);

        ImageView imageView5 = findViewById(R.id.image_view5);
        String url5 = TrafficImageUtils.getImageUrl(4);
        Picasso.with(this).load(url5).into(imageView5);
    }
}
