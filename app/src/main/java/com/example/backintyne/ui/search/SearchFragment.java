package com.example.backintyne.ui.search;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.backintyne.R;
import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.SiteEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;

    private LinearLayout results;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize views

        results = root.findViewById(R.id.results);

        // Initialize search box
        EditText searchBox = root.findViewById(R.id.search_box);
        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                searchViewModel.searchQuery(v.getText().toString());
                return true;
            }
        });

        // Initialize spinner items
        List<String> spinnerItems = getSpinnerItems();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Initialize spinner with spinner items
        Spinner filter = root.findViewById(R.id.filter);
        filter.setAdapter(adapter);
        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchViewModel.setFilterSelected(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Observe displayed entries variable
        searchViewModel.getSearchResults().observe(getViewLifecycleOwner(), new Observer<List<SiteEntry>>() {
            @Override
            public void onChanged(List<SiteEntry> siteEntries) {
                setSearchResults(siteEntries);
            }
        });

        return root;
    }

    private List<String> getSpinnerItems() {
        List<String> types = new ArrayList<>();
        types.add("Filter"); // prompt

        for (SiteEntry entry : DataManager.getDataManager().getSiteData()) {
            String newType = entry.getType();

            // Check if type already exists
            // O(n) exists check since n is very small
            boolean exists = false;
            for (String type : types) {
                if (newType.equals(type)) {
                    exists = true;
                    break;
                }
            }

            // If type not already stored, store
            if (!exists) {
                types.add(newType);
            }
        }

        return types;
    }

    private void setSearchResults(List<SiteEntry> entries) {
        // Remove all previous results
        results.removeAllViews();

        // Add result view to results view for each entry to be displayed
        for (SiteEntry entry : entries) {
            // Get view object from XML
            View result = LayoutInflater.from(getContext()).inflate(R.layout.result_item2, null);

            // Set image
            ImageView resultsImage = result.findViewById(R.id.result_image);
            try {
                resultsImage.setImageBitmap(DataManager.getImageBitMap(entry.getGallery().get(0).getFileName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Set name
            TextView resultsName = result.findViewById(R.id.result_name);
            resultsName.setText(entry.getName());

            // Set description
            TextView resultsDescription = result.findViewById(R.id.result_description);
            resultsDescription.setText(entry.getIntroduction());

            // Set view group layout parameters
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int pixelsConversion = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    (float) 8, getResources().getDisplayMetrics());
            layoutParams.setMargins(0, 0, 0, pixelsConversion);

            // TODO Complete once InfoFragment implemented
            // Add click listener to navigate to info page
            /*
            result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = v.findViewById(R.id.result_name).toString();
                    SiteEntry entry = searchViewModel.findEntryByName(name);
                    if (entry != null) {
                        // Navigate to InfoFragment with SiteEntry in passed parameter
                    }
                }
            });
            */

            // Append result view to end of results view group
            results.addView(result, layoutParams);
        }
    }

}