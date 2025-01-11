package com.example.handwritinggeneratorutility.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelReader;
import javafx.scene.shape.Path;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        return "image" + this.canvas.getWidth();
    }

    //TODO
    private String getExtensionType() { return "png"; }

    public GeneratorConfiguration getConf() { return this.configuration; }

    public void setCanvas(Canvas canvas) { this.canvas = canvas; }

    public void setCanvasState(Tool state) { this.state = state; }

    public Tool getCanvasState() { return this.state; }

    public void savePicture(String label) {
        try {
            checkOrCreateStorage();
            BufferedImage image = this.serializeCanvas();
            String filename = this.generateFilename();
            String fullPath = String.format("%s\\HGU_dataset\\%s.%s", this.configuration.getPath(), filename, this.getExtensionType());
            ImageIO.write(image, this.getExtensionType(), new File(fullPath));
            this.saveLabel(filename, label);
            System.out.println(fullPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkOrCreateStorage() throws IOException {
        File dir = new File(this.configuration.getPath() + "\\HGU_dataset");
        File labels = new File(this.configuration.getPath() + "\\HGU_dataset\\labels.json");
        if(! dir.exists()) {
            dir.mkdir();
        }
        if(! labels.exists()) {
            Files.write(labels.toPath(), List.of("{\n}"), StandardCharsets.UTF_8);
        }
    }

    private void saveLabel(String imgName, String label) throws IOException {
        String filepath = this.configuration.getPath() + "\\HGU_dataset\\labels.json";
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(filepath));
        in.readLine();
        String line;
        while (!(line = in.readLine()).strip().endsWith("}")) {
            stringBuilder.append("\n").append(line);
        }
        if (!stringBuilder.isEmpty()) {
            stringBuilder.append(",");
        }
        stringBuilder.append("\n").append(String.format("\t\"%s\" : \"%s\"", imgName, label));
        Files.write((new File(filepath)).toPath(), List.of("{" + stringBuilder + "\n}"), StandardCharsets.UTF_8);
    }
}
