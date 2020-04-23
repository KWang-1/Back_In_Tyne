package com.example.backintyne.data;

import java.util.List;

/**
 * Represents an event organiser.
 */
public class EventEntry {

    private final String name;
    private final String location;
    private final String details;
    private final String link;
    private final String date;
    private final List<ImageData> gallery;

    EventEntry(String name, String location, String details, String link, String date, List<ImageData> gallery) {
        this.name = name;
        this.location = location;
        this.details = details;
        this.link = link;
        this.date = date;
        this.gallery = gallery;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDetails() {
        return details;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public List<ImageData> getGallery() {
        return gallery;
    }

}
