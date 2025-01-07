package com.example.handwritinggeneratorutility.model;

public class GeneratorConfiguration {
    private int width;
    private int height;
    private String path;

    public GeneratorConfiguration(int width, int height, String path) {
        this.width = width;
        this.height = height;
        this.path = path;
    }

    public int getWidth() { return this.width; }

    public int getHeight() { return this.height; }

    public String getPath() { return this.path; }

    public void setPath(String path) { this.path = path; }
}
