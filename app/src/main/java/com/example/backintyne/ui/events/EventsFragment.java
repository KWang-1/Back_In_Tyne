package com.example.backintyne.ui.events;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.backintyne.R;
import com.example.backintyne.data.EventEntry;

/**
 * Events page fragment.
 * Displays information for each event organiser.
 */
public class EventsFragment extends Fragment {

    private EventsViewModel eventsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventsViewModel = ViewModelProviders.of(this).get(EventsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        LinearLayout eventsLayout = root.findViewById(R.id.events);

        // For each event
        for (EventEntry entry : eventsViewModel.getEventData()) {

            // Create new view for event
            View event = LayoutInflater.from(getContext()).inflate(R.layout.event_layout, null);

            // Set the data for the event
            TextView eventName = event.findViewById(R.id.event_name);
            eventName.setText(entry.getName());

            TextView eventLocation = event.findViewById(R.id.event_location);
            eventLocation.setText(entry.getLocation());

            TextView eventDetails = event.findViewById(R.id.event_details);
            eventDetails.setText(entry.getDetails());

            TextView eventDate = event.findViewById(R.id.event_date);
            eventDate.setText(entry.getDate());

            TextView eventLink = event.findViewById(R.id.event_link);
            eventLink.setText(entry.getLink());

            // Set parameters for the events layout
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int pixelsConversion = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    (float) 8, getResources().getDisplayMetrics());
            layoutParams.setMargins(0, 0, 0, pixelsConversion);

            // Add the event to the page
            eventsLayout.addView(event, layoutParams);
        }

        return root;
    }



}