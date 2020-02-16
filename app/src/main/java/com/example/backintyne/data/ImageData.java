package com.example.backintyne.data;

public class ImageData {

    private final String fileName;
    private final String attribution;

    ImageData(String fileName, String attribution) {
        if (fileName.equals("") || attribution.equals("")) {
            throw new IllegalArgumentException("Variables fileName and attribution cannot be empty");
        }
        this.fileName = fileName;
        this.attribution = attribution;
    }

    public String getFileName() {
        return fileName;
    }

    public String getAttribution() {
        return attribution;
    }

}
