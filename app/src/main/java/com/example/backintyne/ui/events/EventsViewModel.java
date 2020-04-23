package com.example.backintyne.ui.events;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.backintyne.data.DataManager;
import com.example.backintyne.data.EventEntry;

import java.util.List;

/**
 * ViewModel for Events page.
 */
public class EventsViewModel extends ViewModel {

    private DataManager data = DataManager.getDataManager();

    public EventsViewModel() {}

    List<EventEntry> getEventData() {
        return data.getEventData();
    }


}