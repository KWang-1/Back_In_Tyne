package com.example.backintyne.ui.map;

import androidx.lifecycle.ViewModel;

import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.util.List;

public class MapViewModel extends ViewModel {

    private DataManager data = DataManager.getDataManager();

    public MapViewModel() {}

    SiteEntry findEntryByName(String name) {
        for (SiteEntry entry : data.getSiteData()) {
            if (entry.getName().equals(name)) {
                return entry;
            }
        }
        return null;
    }

    List<SiteEntry> getEntries() {
        return data.getSiteData();
    }

}