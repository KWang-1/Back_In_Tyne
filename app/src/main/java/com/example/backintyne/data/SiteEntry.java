package com.example.backintyne.data;

import java.util.Collections;
import java.util.List;

public class SiteEntry {

    private final String name;
    private final String address;
    private final String era;
    private final String type;
    private final String introduction;
    private final String details;
    private final String cost;
    private final String facilities;
    private final List<ImageData> gallery;

    SiteEntry(String name, String address, String era, String type, String introduction, String details, String cost, String facilities, List<ImageData> gallery) {
        this.name = name;
        this.address = address;
        this.era = era;
        this.type = type;
        this.introduction = introduction;
        this.details = details;
        this.cost = cost;
        this.facilities = facilities;
        this.gallery = Collections.unmodifiableList(gallery);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEra() {
        return era;
    }

    public String getType() {
        return type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getDetails() {
        return details;
    }

    public String getCost() {
        return cost;
    }

    public String getFacilities() {
        return facilities;
    }

    public List<ImageData> getGallery() {
        return gallery;
    }

}
