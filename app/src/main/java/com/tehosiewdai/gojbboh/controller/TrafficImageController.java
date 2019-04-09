package com.tehosiewdai.gojbboh.controller;

import android.content.Intent;
import android.util.Log;
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

/**
 * Controller class for logic about traffic images.
 */
public class TrafficImageController implements ControllerCallback {

    /**
     * Camera ID for Woodlands Causeway Location image.
     */
    public static final String WOODLANDS_CAUSEWAY_ID = "2701";

    /**
     * Unique identifier for Woodlands Causeway Location image datetime.
     */
    public static final String WOODLANDS_CAUSEWAY_ID_DATETIME = "2701_D";

    /**
     * Camera ID for Woodlands Checkpoint Location image.
     */
    public static final String WOODLANDS_CHECKPOINT_ID = "2702";

    /**
     * Unique identifier for Woodlands Checkpoint Location image datetime.
     */
    public static final String WOODLANDS_CHECKPOINT_ID_DATETIME = "2702_D";

    /**
     * Camera ID for Woodlands Flyover Location image.
     */
    public static final String WOODLANDS_FLYOVER_ID = "2704";

    /**
     * Unique identifier for Woodlands Flyover Location image datetime.
     */
    public static final String WOODLANDS_FLYOVER_ID_DATETIME = "2704_D";

    /**
     * Camera ID for Tuas Second Link Location image.
     */
    public static final String TUAS_SECOND_LINK_ID = "4703";

    /**
     * Unique identifier for Tuas Second Link Location image datetime.
     */
    public static final String TUAS_SECOND_LINK_ID_DATETIME = "4703_D";

    /**
     * Camera ID for Tuas Checkpoint Location image.
     */
    public static final String TUAS_CHECKPOINT_ID = "4712";

    /**
     * Unique identifier for Tuas Checkpoint Location image datetime.
     */
    public static final String TUAS_CHECKPOINT_ID_DATETIME = "4712_D";

    /**
     * Camera ID for After Tuas West Road Location image.
     */
    public static final String AFTER_TUAS_WEST_ROAD_ID = "4713";

    /**
     * Unique identifier for After Tuas West Road Location image datetime.
     */
    public static final String AFTER_TUAS_WEST_ROAD_ID_DATETIME = "4713_D";

    /**
     * URL string for API access to traffic images.
     */
    private final String TRAFFIC_URL = "https://api.data.gov.sg/v1/transport/traffic-images";

    /**
     * TrafficImage object for Woodlands Causeway image Location.
     */
    private TrafficImage woodlandsCausewayImage;

    /**
     * TrafficImage object for Woodlands Checkpoint image Location.
     */
    private TrafficImage woodlandsCheckpointImage;

    /**
     * TrafficImage object for Woodlands Flyover image Location.
     */
    private TrafficImage woodlandsFlyoverImage;

    /**
     * TrafficImage object for Tuas Second Link image Location.
     */
    private TrafficImage tuasSecondLinkImage;

    /**
     * TrafficImage object for Tuas Checkpoint image Location.
     */
    private TrafficImage tuasCheckpointImage;

    /**
     * TrafficImage object for After Tuas West Road image Location.
     */
    private TrafficImage afterTuasWestRoadImage;

    /**
     * Array list of traffic images.
     */
    private ArrayList<TrafficImage> trafficImages;

    /**
     * MainActivity that uses it.
     */
    private MainActivity activity;

    /**
     * Instantiates the controller.
     *
     * @param activity the activity that instantiated it.
     */
    public TrafficImageController(final MainActivity activity) {
        this.activity = activity;
        trafficImages = new ArrayList<>();
    }

    /**
     * Implements ControllerCallback method.
     * Sets a loading indicator to show that image is being loaded.
     */
    @Override
    public void onPreExecute() {
        activity.getLoadingIndicator().setVisibility(View.VISIBLE);
    }

    /**
     * Implements ControllerCallback method.
     * Actions done after querying the JSON result in the background.
     *
     * @param results JSON string queried.
     */
    @Override
    public void onPostExecute(String results) {

        trafficImages = TrafficImageReader.getTrafficImages(results);

        activity.getLoadingIndicator().setVisibility(View.INVISIBLE);

        //Sets the TrafficImage object variables.
        for (TrafficImage image : trafficImages) {
            Log.e("traffic", String.valueOf(image.getCameraId()));
            switch (image.getCameraId()) {
                case WOODLANDS_CAUSEWAY_ID:
                    woodlandsCausewayImage = image;
                    Picasso
                            .with(activity)
                            .load(image.getImageUrl())
                            .placeholder(R.drawable.maintainance)
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
                            .placeholder(R.drawable.maintainance)
                            .into(activity.getTuasHomeImage());
                    break;
                case TUAS_CHECKPOINT_ID:
                    tuasCheckpointImage = image;
                    break;
                case AFTER_TUAS_WEST_ROAD_ID:
                    afterTuasWestRoadImage = image;
                    break;
            }

        }

        //Sets event listener for Woodlands traffic image upon click, passes information and unique identifiers through intents.
        activity.getWoodlandsHomeImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, WoodlandsTrafficImageActivity.class);
                if (woodlandsCausewayImage != null) {
                    intent.putExtra(WOODLANDS_CAUSEWAY_ID, woodlandsCausewayImage.getImageUrl());
                    intent.putExtra(WOODLANDS_CAUSEWAY_ID_DATETIME, woodlandsCausewayImage.getDatetime());
                }
                if (woodlandsCheckpointImage != null) {
                    intent.putExtra(WOODLANDS_CHECKPOINT_ID, woodlandsCheckpointImage.getImageUrl());
                    intent.putExtra(WOODLANDS_CHECKPOINT_ID_DATETIME, woodlandsCheckpointImage.getDatetime());
                }
                if (woodlandsFlyoverImage != null) {
                    intent.putExtra(WOODLANDS_FLYOVER_ID, woodlandsFlyoverImage.getImageUrl());
                    intent.putExtra(WOODLANDS_FLYOVER_ID_DATETIME, woodlandsFlyoverImage.getDatetime());
                }

                activity.startActivity(intent);
            }
        });

        //Sets event listener for Tuas traffic image upon click, passes information and unique identifiers through intents
        activity.getTuasHomeImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TuasTrafficImageActivity.class);

                if (tuasSecondLinkImage != null) {
                    intent.putExtra(TUAS_SECOND_LINK_ID, tuasSecondLinkImage.getImageUrl());
                    intent.putExtra(TUAS_SECOND_LINK_ID_DATETIME, tuasSecondLinkImage.getDatetime());
                }
                if (tuasCheckpointImage != null) {
                    intent.putExtra(TUAS_CHECKPOINT_ID, tuasCheckpointImage.getImageUrl());
                    intent.putExtra(TUAS_CHECKPOINT_ID_DATETIME, tuasCheckpointImage.getDatetime());
                }
                if (afterTuasWestRoadImage != null) {
                    intent.putExtra(AFTER_TUAS_WEST_ROAD_ID, afterTuasWestRoadImage.getImageUrl());
                    intent.putExtra(AFTER_TUAS_WEST_ROAD_ID_DATETIME, afterTuasWestRoadImage.getDatetime());
                }

                activity.startActivity(intent);
            }
        });
    }

    /**
     * Gets the URL string for API access to traffic images.
     *
     * @return URL string for API access to traffic images.
     */
    @Override
    public String getUrlString() {
        return TRAFFIC_URL;
    }

    /**
     * Executes a background process to retrieve JSON string from the API.
     */
    public void runApiQuery() {
        trafficImages.clear();
        new ApiAsyncTask(this).execute();
    }
}
