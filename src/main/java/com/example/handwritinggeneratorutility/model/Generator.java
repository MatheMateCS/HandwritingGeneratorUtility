package com.example.handwritinggeneratorutility.model;

public class Generator {

    public enum Tool {
        BRUSH,
        ERASER
    }

    private final GeneratorConfiguration configuration;

    private Tool state;

    public Generator(GeneratorConfiguration configuration) {
        this.configuration = configuration;
        this.state = Tool.BRUSH;
    }

    public GeneratorConfiguration getConf() { return this.configuration; }

    public void setCanvasState(Tool state) { this.state = state; }

    public Tool getCanvasState() { return this.state; }

    public void savePicture() {}

}
