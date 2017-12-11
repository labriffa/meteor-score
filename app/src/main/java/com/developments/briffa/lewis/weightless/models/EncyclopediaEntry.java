package com.developments.briffa.lewis.weightless.models;

public class EncyclopediaEntry {
    private String name;
    private int image;
    private int photo;
    private String description;

    public EncyclopediaEntry(String name, int image, String description, int photo) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
