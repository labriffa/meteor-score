package com.developments.briffa.lewis.weightless.models;

public class PlanetEntry {
    private String name;
    private int image;
    private int photo;
    private String description;

    public PlanetEntry(String name, int image, String description, int photo) {
        this.name = name;
        this.image = image;
        this.photo = photo;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getPhoto() { return photo; }

    public String getDescription() {
        return description;
    }
}
