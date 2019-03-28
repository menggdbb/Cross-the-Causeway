package com.tehosiewdai.gojbboh.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.utilities.TrafficImageUtils;

public class WoodlandsTrafficImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woodlands_traffic_image);


        ImageView imageView6 = findViewById(R.id.image_view6);
        String url6 = TrafficImageUtils.getImageUrl(5);
        Picasso.with(this).load(url6).into(imageView6);

        ImageView imageView7 = findViewById(R.id.image_view7);
        String url7 = TrafficImageUtils.getImageUrl(6);
        Picasso.with(this).load(url7).into(imageView7);

        ImageView imageView8 = findViewById(R.id.image_view8);
        String url8 = TrafficImageUtils.getImageUrl(7);
        Picasso.with(this).load(url8).into(imageView8);

        ImageView imageView9 = findViewById(R.id.image_view9);
        String url9 = TrafficImageUtils.getImageUrl(8);
        Picasso.with(this).load(url9).into(imageView9);

        ImageView imageView10 = findViewById(R.id.image_view10);
        String url10 = TrafficImageUtils.getImageUrl(9);
        Picasso.with(this).load(url10).into(imageView10);
    }
}
