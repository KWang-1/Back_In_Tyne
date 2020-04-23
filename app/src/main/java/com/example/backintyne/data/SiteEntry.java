package com.example.backintyne.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.ClassLoader;
import 	java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class SiteEntry implements Parcelable {

    private final String name;
    private final String address;
    private final String era;
    private final String type;
    private final String introduction;
    private final String description;
    private final String details;
    private final String cost;
    private final String facilities;
    private final String publicTransport;
    private final List<ImageData> gallery;
    private final double longitude;
    private final double latitude;

    SiteEntry(String name, String address, String era, String type, String introduction, String description, String details, String cost, String facilities, String public_transport, List<ImageData> gallery, double longitude, double latitude) {
        this.name = name;
        this.address = address;
        this.era = era;
        this.type = type;
        this.introduction = introduction;
        this.description = description;
        this.details = details;
        this.cost = cost;
        this.facilities = facilities;
        this.publicTransport = public_transport;
        this.gallery = Collections.unmodifiableList(gallery);
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private SiteEntry(Parcel in) {
        gallery = new ArrayList<>();

        name = in.readString();
        address = in.readString();
        era = in.readString();
        type = in.readString();
        introduction = in.readString();
        description = in.readString();
        details = in.readString();
        cost = in.readString();
        facilities = in.readString();
        publicTransport = in.readString();
        in.readList(gallery, ClassLoader.getSystemClassLoader());
        longitude = in.readDouble();
        latitude = in.readDouble();
    }

    public static final Creator<SiteEntry> CREATOR = new Creator<SiteEntry>() {
        @Override
        public SiteEntry createFromParcel(Parcel in) {
            return new SiteEntry(in);
        }

        @Override
        public SiteEntry[] newArray(int size) {
            return new SiteEntry[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(era);
        dest.writeString(type);
        dest.writeString(introduction);
        dest.writeString(description);
        dest.writeString(details);
        dest.writeString(cost);
        dest.writeString(facilities);
        dest.writeString(publicTransport);
        dest.writeList(gallery);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
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

    public String getDescription() { return description; }

    public String getDetails() {
        return details;
    }

    public String getCost() {
        return cost;
    }

    public String getFacilities() {
        return facilities;
    }

    public String getPublicTransport() {
        return publicTransport;
    }

    public List<ImageData> getGallery() {
        return gallery;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

}
