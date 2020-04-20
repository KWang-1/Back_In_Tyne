package com.example.backintyne.ui.events;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.backintyne.R;
import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.EventEntry;


import java.io.IOException;

public class EventsFragment extends Fragment {


    private LinearLayout events;
    private EventsViewModel eventsViewModel;
    private LinearLayout eventsLayout;
    // UI

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventsViewModel =
                ViewModelProviders.of(this).get(EventsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        eventsLayout = root.findViewById(R.id.Events);

        for (EventEntry entry : DataManager.getDataManager().getEventData()) {
            //for each event

            View event = LayoutInflater.from(getContext()).inflate(R.layout.event_layout, null);
            //set new view for event

            //ImageView eventlogo = event.findViewById(R.id.event_logo);
            //try {
            //    eventlogo.setImageBitmap(DataManager.getImageBitMap(entry.getGallery().get(0).getFileName()));
            //} catch (IOException ex) {
            //     ex.printStackTrace();
            //}

            //set the data
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

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int pixelsConversion = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    (float) 8, getResources().getDisplayMetrics());
            layoutParams.setMargins(0, 0, 0, pixelsConversion);

            eventsLayout.addView(event, layoutParams);
            //add the event to the page
        }
        return root;
    }



}