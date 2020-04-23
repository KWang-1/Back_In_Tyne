package com.example.backintyne.ui.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends ViewModel {

    private DataManager data = DataManager.getDataManager();
    private MutableLiveData<List<SiteEntry>> searchResults = new MutableLiveData<>();

    private String searchQuery = "";
    private String filterSelected;

    public SearchViewModel() {
        searchResults.setValue(data.getSiteData());
    }

    void searchQuery(String query) {
        List<SiteEntry> results = new ArrayList<>();
        searchQuery = query.toLowerCase();

        for (SiteEntry entry : data.getSiteData()) {

            if ((filterSelected.equals("Filter") || entry.getType().equals(filterSelected)) &&
                    (searchQuery.equals("") || entry.getName().toLowerCase().contains(searchQuery))) {
                results.add(entry);
            }
        }

        searchResults.setValue(results);
    }

    SiteEntry findEntryByName(String name) {
        return data.findEntryByName(name);
    }

    void setFilterSelected(String selected) {
        filterSelected = selected;
        searchQuery(searchQuery);
    }

    MutableLiveData<List<SiteEntry>> getSearchResults() {
        return searchResults;
    }

}