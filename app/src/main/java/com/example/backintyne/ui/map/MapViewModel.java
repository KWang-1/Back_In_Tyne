package com.example.backintyne.ui.map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * View model for the map page.
 * Handles backend including google maps launch requests.
 */
public class MapViewModel extends ViewModel {

    private DataManager data = DataManager.getDataManager();

    public MapViewModel() {}

    void launchGoogleMapsWithDirections(Activity activity, LatLng source, LatLng destination) {
        @SuppressLint("DefaultLocale") String saddr = String.format("%f, %f", source.latitude, source.longitude);
        @SuppressLint("DefaultLocale") String daddr = String.format("%f, %f", destination.latitude, destination.longitude);

        String request = String.format("http://maps.google.com/maps?saddr=%s&daddr=%s", saddr, daddr);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(request));
        activity.startActivity(intent);
    }

    // Indirection may seem redundant but adheres to design pattern
    SiteEntry findEntryByName(String name) {
        return data.findEntryByName(name);
    }

    List<SiteEntry> getEntries() {
        return data.getSiteData();
    }

}