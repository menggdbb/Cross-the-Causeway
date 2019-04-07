package com.tehosiewdai.gojbboh.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.tehosiewdai.gojbboh.R;

public class MapInfoWindowAdapter implements InfoWindowAdapter {

    private View myContentsView;

    public MapInfoWindowAdapter(Activity context) {
        myContentsView = context.getLayoutInflater().inflate(R.layout.custom_info_windows,null);
    }

    @Override
    public View getInfoContents(Marker marker) {

        TextView tvTitle = (myContentsView.findViewById(R.id.moneyChanger));
        tvTitle.setText(marker.getTitle());
        TextView tvSnippet = (myContentsView.findViewById(R.id.snippet));
        tvSnippet.setText(marker.getSnippet());

        return myContentsView;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }


}
