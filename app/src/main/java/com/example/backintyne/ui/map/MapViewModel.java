package com.example.backintyne.ui.map;

import androidx.lifecycle.ViewModel;

import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.util.List;

/**
 * View model for the map page.
 */
public class MapViewModel extends ViewModel {

    private DataManager data = DataManager.getDataManager();

    public MapViewModel() {}

    // Indirection may seem redundant but adheres to design pattern
    SiteEntry findEntryByName(String name) {
        return data.findEntryByName(name);
    }

    List<SiteEntry> getEntries() {
        return data.getSiteData();
    }

}