package com.example.backintyne.data;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Xml;

import com.example.backintyne.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DataManager {

    // Static variables for public access/utility
    private static DataManager dataManager = null;
    private static AssetManager assetManager = null;

    // List of entries
    // Primary data structures
    private List<SiteEntry> siteData = null;
    private List<EventEntry> eventData = null;

    // Don't use namespace
    private static final String namespace = null;

    // -------------------- Public Interface -------------------- //

    // Creates a DataManager instance based on given input stream
    public static void createDataManager(Resources resources) {
        if (dataManager != null) {
            return;
        }
        dataManager = new DataManager(resources);
        DataManager.assetManager = resources.getAssets();
    }

    // Returns the static reference to the DataManager instance
    public static DataManager getDataManager() {
        return dataManager;
    }

    // Gets the first entry that matches the name string
    public SiteEntry findEntryByName(String name) {
        for (SiteEntry entry : siteData) {
            if (entry.getName().equals(name)) {
                return entry;
            }
        }
        return null;
    }

    // Returns a drawable bitmap for an image in the assets folder
    public static Bitmap getImageBitMap(String assetFileName) throws IOException {
        InputStream is = assetManager.open("images/" + assetFileName);
        return BitmapFactory.decodeStream(is);
    }

    // Returns a copy of the SiteEntry list
    public List<SiteEntry> getSiteData() {
        return siteData;
    }

    // Returns a copy of the EventEntry list
    public List<EventEntry> getEventData() {
        return eventData;
    }

    // -------------------- Private Functions -------------------- //

    // Immediately begins parsing the input stream into the entry list
    private DataManager(Resources resources) {
        try {
            siteData = Collections.unmodifiableList(parseSiteData(resources.openRawResource(R.raw.site_data)));
            eventData = Collections.unmodifiableList(parseEventData(resources.openRawResource(R.raw.event_data))); // TODO
        } catch (XmlPullParserException | IOException ex) {
            ex.printStackTrace();
        }
    }

    // Parses the XML formatted input stream into a list of SiteEntry
    private List<SiteEntry> parseSiteData(InputStream in) throws XmlPullParserException, IOException {
        // Create output
        List<SiteEntry> entryList = new ArrayList<>();

        // Begin parsing
        try {
            // Set up parser object
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);

            // Enter entries root element
            parser.next();
            parser.require(XmlPullParser.START_TAG, namespace, "entries");

            // Parse until end of document
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }

                // Parse all entry elements
                if (parser.getName().equals("entry")) {
                    entryList.add(parseSiteEntry(parser));
                } else {
                    skip(parser);
                }
            }
        } finally {
            in.close();
        }

        return entryList;
    }

    private SiteEntry parseSiteEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, namespace, "entry");

        // Create temporary variables to hold entry siteData
        String name = "";
        String address = "";
        String era = "";
        String type = "";
        String introduction = "";
        String description = "";
        String details = "";
        String cost = "";
        String facilities = "";
        String publicTransport = "";
        List<ImageData> gallery = new ArrayList<>();
        double longitude = 0;
        double latitude = 0;

        // Parse through entry element
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            // Find appropriate parse method, and assign to appropriate output
            switch (parser.getName()) {
                case "name":
                    name = readText(parser);
                    break;
                case "address":
                    address = readText(parser);
                    break;
                case "type":
                    type = readText(parser);
                    break;
                case "era":
                    era = readText(parser);
                    break;
                case "introduction":
                    introduction = readText(parser);
                    break;
                case "description":
                    description = readText(parser);
                    break;
                case "details":
                    details = readText(parser);
                    break;
                case "cost":
                    cost = readText(parser);
                    break;
                case "facilities":
                    facilities = readText(parser);
                    break;
                case "public_transport":
                    publicTransport = readText(parser);
                    break;
                case "gallery":
                    gallery = readGallery(parser);
                    break;
                case "longitude":
                    longitude = readDouble(parser);
                    break;
                case "latitude":
                    latitude = readDouble(parser);
                    break;
                default:
                    skip(parser);
            }
        }

        return new SiteEntry(name, address, era, type, introduction, description, details, cost, facilities, publicTransport, gallery, longitude, latitude);
    }

    private List<EventEntry> parseEventData(InputStream in) throws XmlPullParserException, IOException {
        // Create output
        List<EventEntry> entryList = new ArrayList<>();

        // Begin parsing
        try {
            // Set up parser object
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);

            // Enter entries root element
            parser.next();
            parser.require(XmlPullParser.START_TAG, namespace, "entries");

            // Parse until end of document
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }

                // Parse all entry elements
                if (parser.getName().equals("entry")) {
                    entryList.add(parseEventEntry(parser));
                } else {
                    skip(parser);
                }
            }
        } finally {
            in.close();
        }

        return entryList;
    }

    private EventEntry parseEventEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, namespace, "entry");

        // Create temporary variables to hold entry siteData
        String name = "";
        String location = "";
        String details = "";
        String link = "";
        String date = "";
        List<ImageData> gallery = new ArrayList<>();

        // Parse through entry element
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            // Find appropriate parse method, and assign to appropriate output
            switch (parser.getName()) {
                case "name":
                    name = readText(parser);
                    break;
                case "location":
                    location = readText(parser);
                    break;
                case "details":
                    details = readText(parser);
                    break;
                case "link":
                    link = readText(parser);
                    break;
                case "date":
                    date = readText(parser);
                    break;
                case "gallery":
                    gallery = readGallery(parser);
                    break;
                default:
                    skip(parser);
            }
        }

        return new EventEntry(name, location, details, link, date, gallery);
    }

    // General method to return text from inside an element as text
    private String readText(XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = null;
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
        }
        parser.next();
        return result;
    }

    // General method to return text from inside an element as double
    private double readDouble(XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = null;
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
        }
        parser.next();
        assert result != null;
        return Double.parseDouble(result);
    }

    // Method to read a gallery element in objects
    private List<ImageData> readGallery(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, namespace, "gallery");

        List<ImageData> gallery = new ArrayList<>();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            if (parser.getName().equals("image")) {
                ImageData imageData = readImage(parser);
                if (imageData.getFileName() != null) {
                    gallery.add(imageData);
                }
            } else {
                skip(parser);
            }
        }

        return gallery;
    }

    // Method to read an image element into an object
    private ImageData readImage(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, namespace, "image");

        String fileName = "";
        String attribution = "";

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            switch (parser.getName()) {
                case "file_name":
                    fileName = readText(parser);
                    break;
                case "attribution":
                    attribution = readText(parser);
                    break;
            }
        }

        return new ImageData(fileName, attribution);
    }

    // Method to skip from a starting tag to its associated closing tag
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

}
