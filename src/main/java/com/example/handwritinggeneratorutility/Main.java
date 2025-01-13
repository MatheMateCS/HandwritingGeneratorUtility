package com.example.handwritinggeneratorutility;

import com.example.handwritinggeneratorutility.controller.GeneratorController;
import com.example.handwritinggeneratorutility.controller.IntroController;
import com.example.handwritinggeneratorutility.model.Generator;
import com.example.handwritinggeneratorutility.model.GeneratorConfiguration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL introPath = Main.class.getResource("introduction.fxml");
        fxmlLoader.setLocation(introPath);
        Parent root = fxmlLoader.load();
        ((IntroController) fxmlLoader.getController()).setDefault(stage);

        Scene scene = new Scene(root, 400, 400);

        stage.setTitle("Генератор рукописных изображений");
        stage.getIcons().add(new Image(String.valueOf(Main.class.getResource("images/icon.jpg"))));
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void launchGeneratorWindow(GeneratorConfiguration conf) throws IOException {
        Stage stage = new Stage();
        Generator generator = new Generator(conf);

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL introPath = Main.class.getResource("generator.fxml");
        fxmlLoader.setLocation(introPath);
        Parent root = fxmlLoader.load();
        ((GeneratorController) fxmlLoader.getController()).setDefault(stage, generator);

        Scene scene = new Scene(root, conf.getWidth() + 300, Math.max(conf.getHeight() + 100, 400));

        stage.setTitle("Холст для генерации изображений");
        stage.getIcons().add(new Image(String.valueOf(Main.class.getResource("images/icon.jpg"))));
        stage.setResizable(true);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}