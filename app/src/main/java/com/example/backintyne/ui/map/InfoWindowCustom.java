package com.example.backintyne.ui.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.backintyne.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfoWindowCustom implements GoogleMap.InfoWindowAdapter
{

    // used to set custom info window
    private Context context;
    private LayoutInflater inflater;
    InfoWindowCustom(Context context) {
        this.context = context;
    }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        inflater = (LayoutInflater)
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.echo_info_window, null);
        // where text is put
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView snippet = (TextView) view.findViewById(R.id.snippet);
        // set window title and snippet value
        title.setText(marker.getTitle());
        snippet.setText(marker.getSnippet());
        return view;
    }
}
