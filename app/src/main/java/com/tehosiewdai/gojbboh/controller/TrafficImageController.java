package com.tehosiewdai.gojbboh.controller;

import android.content.Intent;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.tehosiewdai.gojbboh.R;
import com.tehosiewdai.gojbboh.activities.MainActivity;
import com.tehosiewdai.gojbboh.activities.TuasTrafficImageActivity;
import com.tehosiewdai.gojbboh.activities.WoodlandsTrafficImageActivity;
import com.tehosiewdai.gojbboh.entity.TrafficImage;
import com.tehosiewdai.gojbboh.utilities.ApiAsyncTask;
import com.tehosiewdai.gojbboh.utilities.TrafficImageReader;

import java.util.ArrayList;

public class TrafficImageController implements ControllerCallback {

    private final String TRAFFIC_URL = "https://api.data.gov.sg/v1/transport/traffic-images";

    public static final String WOODLANDS_CAUSEWAY_ID = "2701";
    public static final String WOODLANDS_CAUSEWAY_ID_DATETIME = "2701_D";
    public static final String WOODLANDS_CHECKPOINT_ID = "2702";
    public static final String WOODLANDS_CHECKPOINT_ID_DATETIME = "2702_D";
    public static final String WOODLANDS_FLYOVER_ID = "2704";
    public static final String WOODLANDS_FLYOVER_ID_DATETIME = "2704_D";
    public static final String TUAS_SECOND_LINK_ID = "4703";
    public static final String TUAS_SECOND_LINK_ID_DATETIME = "4703_D";
    public static final String TUAS_CHECKPOINT_ID = "4712";
    public static final String TUAS_CHECKPOINT_ID_DATETIME = "4712_D";
    public static final String AFTER_TUAS_WEST_ROAD_ID = "4713";
    public static final String AFTER_TUAS_WEST_ROAD_ID_DATETIME = "4713_D";

    private TrafficImage woodlandsCausewayImage;
    private TrafficImage woodlandsCheckpointImage;
    private TrafficImage woodlandsFlyoverImage;
    private TrafficImage tuasSecondLinkImage;
    private TrafficImage tuasCheckpointImage;
    private TrafficImage afterTuasWestRoadImage;

    private ArrayList<TrafficImage> trafficImages;

    private MainActivity activity;

    public TrafficImageController(final MainActivity activity) {
        this.activity = activity;
        trafficImages = new ArrayList<>();
    }

    @Override
    public void onPreExecute() {
        activity.getLoadingIndicator().setVisibility(View.VISIBLE);
    }

    @Override
    public void onPostExecute(String results) {

        trafficImages = TrafficImageReader.getTrafficImages(results);

        activity.getLoadingIndicator().setVisibility(View.INVISIBLE);

        for (TrafficImage image : trafficImages) {
            switch (image.getCameraId()) {
                case WOODLANDS_CAUSEWAY_ID:
                    woodlandsCausewayImage = image;
                    Picasso
                            .with(activity)
                            .load(image.getImageUrl())
                            .placeholder(R.drawable.fff)
                            .into(activity.getWoodlandsHomeImage());
                    break;
                case WOODLANDS_CHECKPOINT_ID:
                    woodlandsCheckpointImage = image;
                    break;
                case WOODLANDS_FLYOVER_ID:
                    woodlandsFlyoverImage = image;
                    break;
                case TUAS_SECOND_LINK_ID:
                    tuasSecondLinkImage = image;
                    Picasso
                            .with(activity)
                            .load(image.getImageUrl())
                            .placeholder(R.drawable.fff)
                            .into(activity.getTuasHomeImage());
                    break;
                case TUAS_CHECKPOINT_ID:
                    tuasCheckpointImage = image;
                    break;
                case AFTER_TUAS_WEST_ROAD_ID:
                    afterTuasWestRoadImage = image;
            }

        }

        activity.getWoodlandsHomeImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, WoodlandsTrafficImageActivity.class);

                intent.putExtra(WOODLANDS_CAUSEWAY_ID, woodlandsCausewayImage.getImageUrl());
                intent.putExtra(WOODLANDS_CAUSEWAY_ID_DATETIME, woodlandsCausewayImage.getDatetime());
                intent.putExtra(WOODLANDS_CHECKPOINT_ID, woodlandsCheckpointImage.getImageUrl());
                intent.putExtra(WOODLANDS_CHECKPOINT_ID_DATETIME, woodlandsCheckpointImage.getDatetime());
                intent.putExtra(WOODLANDS_FLYOVER_ID, woodlandsFlyoverImage.getImageUrl());
                intent.putExtra(WOODLANDS_FLYOVER_ID_DATETIME, woodlandsFlyoverImage.getDatetime());

                activity.startActivity(intent);
            }
        });

        activity.getTuasHomeImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TuasTrafficImageActivity.class);

                intent.putExtra(TUAS_SECOND_LINK_ID, tuasSecondLinkImage.getImageUrl());
                intent.putExtra(TUAS_SECOND_LINK_ID_DATETIME, tuasSecondLinkImage.getDatetime());
                intent.putExtra(TUAS_CHECKPOINT_ID, tuasCheckpointImage.getImageUrl());
                intent.putExtra(TUAS_CHECKPOINT_ID_DATETIME, tuasCheckpointImage.getDatetime());
                intent.putExtra(AFTER_TUAS_WEST_ROAD_ID, afterTuasWestRoadImage.getImageUrl());
                intent.putExtra(AFTER_TUAS_WEST_ROAD_ID_DATETIME, afterTuasWestRoadImage.getDatetime());

                activity.startActivity(intent);
            }
        });
    }

    @Override
    public String getUrlString() {
        return TRAFFIC_URL;
    }

    public void runApiQuery() {
        trafficImages.clear();
        new ApiAsyncTask(this).execute();
    }


}
