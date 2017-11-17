package com.developments.briffa.lewis.weightless;

public class PlanetEntry {
    private String name;
    private int image;
    private String description;

    public PlanetEntry(String name, int image, String description) {
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
