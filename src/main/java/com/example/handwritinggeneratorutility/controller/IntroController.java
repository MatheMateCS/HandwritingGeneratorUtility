package com.example.handwritinggeneratorutility.controller;

import com.example.handwritinggeneratorutility.Main;
import com.example.handwritinggeneratorutility.model.GeneratorConfiguration;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class IntroController {
    private Stage stage;

    @FXML
    protected Pane pane;

    @FXML
    protected Menu menu_file;

    @FXML
    protected Menu menu_edit;

    @FXML
    protected Menu menu_help;

    @FXML
    protected MenuItem mi_load;

    @FXML
    protected MenuItem mi_exit;

    @FXML
    protected MenuItem mi_about;

    @FXML
    protected Label err_message;

    @FXML
    protected TextField tf_width;

    @FXML
    protected TextField tf_height;

    @FXML
    protected TextField tf_path;

    @FXML
    protected Button btn_select_path;

    @FXML
    protected Button btn_open_canvas;

    public IntroController() {}

    public void setDefault(Stage stage) {
        err_message.setVisible(false);
        this.stage = stage;
        tf_path.setFocusTraversable(false);
        pane.requestFocus();
    }

    private boolean isWidthValid() {
        String data = tf_width.getCharacters().toString();
        try {
            int width = Integer.parseInt(data);
            return width > 0;
        } catch (NumberFormatException _) {
            return false;
        }
    }

    private boolean isHeightValid() {
        String data = tf_height.getCharacters().toString();
        try {
            int height = Integer.parseInt(data);
            return height > 0;
        } catch (NumberFormatException _) {
            return false;
        }
    }

    private boolean isPathValid() {
        String path = tf_path.getCharacters().toString();
        File dir = new File(path);
        return dir.exists();
    }

    @FXML
    protected void pathSelect() {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Выбор папки");
        File enteredPath = new File(tf_path.getCharacters().toString());
        if(enteredPath.exists()) dirChooser.setInitialDirectory(enteredPath);
        File selectedDir = dirChooser.showDialog(this.stage);
        if(selectedDir != null) tf_path.setText(selectedDir.getPath());
    }

    @FXML
    protected void openCanvas() {
        err_message.setVisible(false);

        if(tf_width.getCharacters().isEmpty()
                || tf_height.getCharacters().isEmpty()
                || tf_path.getCharacters().isEmpty()) {
            err_message.setText("Все поля должны быть заполнены!");
            err_message.setVisible(true);
            return;
        }

        if(!this.isWidthValid()) {
            err_message.setText("Ширина холста должна быть целым положительным числом!");
            err_message.setVisible(true);
            return;
        }
        if(!this.isHeightValid()) {
            err_message.setText("Высота холста должна быть целым положительным числом!");
            err_message.setVisible(true);
            return;
        }
        if(!this.isPathValid()) {
            err_message.setText("Указанный путь должен соответствовать реальной папке!");
            err_message.setVisible(true);
            return;
        }

        try {
            Main.launchGeneratorWindow(new GeneratorConfiguration(Integer.parseInt(tf_width.getCharacters().toString()),
                    Integer.parseInt(tf_height.getCharacters().toString()), tf_path.getCharacters().toString()));
        } catch (IOException e) {
            System.err.println("Problems with source file loading!");
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void loadConf() {}

    @FXML
    protected void closePage() {
        this.stage.close();
    }

    @FXML
    protected void showAbout() {}

    @FXML
    protected void keyPressedListen(KeyEvent e) {
        if(e.isControlDown() && e.getCode().getChar().equals("Q")) {
            this.closePage();
        }
        if(e.isControlDown() && e.isAltDown() && e.getCode().getChar().equals("P")) {
            this.stage.setAlwaysOnTop(!this.stage.alwaysOnTopProperty().getValue());
        }
        if(e.getCode().getName().equals("Enter")) {
            this.openCanvas();
        }
    }

    @FXML
    protected void keyReleasedListen(KeyEvent e) {}

    @FXML
    protected void mouseClickedListen(MouseEvent e) {
        pane.requestFocus();
    }
}