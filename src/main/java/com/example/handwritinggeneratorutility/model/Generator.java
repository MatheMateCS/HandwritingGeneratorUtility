package com.example.handwritinggeneratorutility.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Generator {

    public enum Tool {
        BRUSH,
        ERASER
    }

    private final GeneratorConfiguration configuration;

    private Canvas canvas;

    private Tool state;

    public Generator(GeneratorConfiguration configuration) {
        this.configuration = configuration;
        this.state = Tool.BRUSH;
    }

    private BufferedImage serializeCanvas() {
        int width = (int)this.canvas.getWidth();
        int height = (int)this.canvas.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        PixelReader pixelReader = this.canvas.snapshot(null, null).getPixelReader();
        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                bufferedImage.setRGB(i, j, pixelReader.getArgb(i, j));
            }
        }

        return bufferedImage;
    }

    //TODO
    private String generateFilename() {
        return "image";
    }

    //TODO
    private String getExtensionType() { return "png"; }


    public GeneratorConfiguration getConf() { return this.configuration; }

    public void setCanvas(Canvas canvas) { this.canvas = canvas; }

    public void setCanvasState(Tool state) { this.state = state; }

    public Tool getCanvasState() { return this.state; }

    public void savePicture() {
        try {
            BufferedImage image = this.serializeCanvas();
            String filename = this.generateFilename();
            String fullPath = String.format("%s\\%s.%s", this.configuration.getPath(), this.generateFilename(), this.getExtensionType());
            ImageIO.write(image, this.getExtensionType(), new File(fullPath));
            System.out.println(fullPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
