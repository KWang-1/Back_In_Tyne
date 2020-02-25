package com.example.backintyne.data;

public class ImageData {

    private final String fileName;
    private final String attribution;

    ImageData(String fileName, String attribution) {
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
