package com.example.backintyne.ui.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * View model for search page.
 * Handles backend logic and query processing.
 */
public class SearchViewModel extends ViewModel {

    private DataManager data = DataManager.getDataManager();
    private MutableLiveData<List<SiteEntry>> searchResults = new MutableLiveData<>();

    // Store previous search parameters
    private String searchQuery = "";
    private String filterSelected;

    public SearchViewModel() {
        searchResults.setValue(data.getSiteData());
    }

    // Find all site entries matching search query
    void searchQuery(String query) {
        List<SiteEntry> results = new ArrayList<>();
        searchQuery = query.toLowerCase();

        // For each SiteEntry object
        // Check if variables match, or if default query
        for (SiteEntry entry : data.getSiteData()) {
            if ((filterSelected.equals("Filter") || entry.getType().equals(filterSelected)) &&
                    (searchQuery.equals("") || entry.getName().toLowerCase().contains(searchQuery))) {
                results.add(entry);
            }
        }

        // Explicit mutable data set as otherwise view not notified
        searchResults.setValue(results);
    }

    // For navigation object passing
    SiteEntry findEntryByName(String name) {
        return data.findEntryByName(name);
    }

    // Handles new filter item selected
    // Call to searchQuery() to update results
    void setFilterSelected(String selected) {
        filterSelected = selected;
        searchQuery(searchQuery);
    }

    MutableLiveData<List<SiteEntry>> getSearchResults() {
        return searchResults;
    }

}