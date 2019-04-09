package com.tehosiewdai.crossthecauseway.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.tehosiewdai.crossthecauseway.R;

/**
 * Adapter for info window display on google maps.
 */
public class MapInfoWindowAdapter implements InfoWindowAdapter {

    /**
     * View for contents.
     */
    private View myContentsView;

    /**
     * Instantiates the info window adapter.
     *
     * @param context activity that uses it.
     */
    public MapInfoWindowAdapter(Activity context) {
        myContentsView = context.getLayoutInflater().inflate(R.layout.custom_info_windows, null);
    }

    /**
     * Returns a view with the info window contents.
     *
     * @param marker Google Map marker.
     * @return the View with the contents.
     */
    @Override
    public View getInfoContents(Marker marker) {

        TextView tvTitle = (myContentsView.findViewById(R.id.moneyChanger));
        tvTitle.setText(marker.getTitle());
        TextView tvSnippet = (myContentsView.findViewById(R.id.snippet));
        tvSnippet.setText(marker.getSnippet());

        return myContentsView;
    }

    /**
     * Does nothing.
     *
     * @param marker Google Map marker.
     * @return null.
     */
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }


}
