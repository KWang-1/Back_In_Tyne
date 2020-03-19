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
    private final String details;
    private final String cost;
    private final String facilities;
    private final List<ImageData> gallery;

    public SiteEntry(String name, String address, String era, String type, String introduction, String details, String cost, String facilities, List<ImageData> gallery) {
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

    protected SiteEntry(Parcel in) {
        gallery = new ArrayList<ImageData>();

        name = in.readString();
        address = in.readString();
        era = in.readString();
        type = in.readString();
        introduction = in.readString();
        details = in.readString();
        cost = in.readString();
        facilities = in.readString();
        in.readList(gallery, ClassLoader.getSystemClassLoader());
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
        dest.writeString(details);
        dest.writeString(cost);
        dest.writeString(facilities);
        dest.writeList(gallery);
    }
}
