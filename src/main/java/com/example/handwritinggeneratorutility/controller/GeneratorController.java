package com.example.handwritinggeneratorutility.controller;

import com.example.handwritinggeneratorutility.model.Generator;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class GeneratorController {

    private Generator generator;

    @FXML
    protected Stage stage;

    @FXML
    protected AnchorPane pane;

    @FXML
    protected MenuBar menubar;

    @FXML
    protected Menu menu_file;

    @FXML
    protected Menu menu_edit;

    @FXML
    protected Menu menu_help;

    @FXML
    protected Canvas canvas;

    @FXML
    protected ToolBar toolbar;

    @FXML
    protected Slider slider;

    @FXML
    protected RadioButton radio_brush;

    @FXML
    protected RadioButton radio_eraser;

    @FXML
    protected TextField tf_path;

    @FXML
    protected Button btn_select_path;

    @FXML
    protected Label lb_label;

    @FXML
    protected TextField tf_label;

    @FXML
    protected Button btn_save;

    @FXML
    protected Button btn_clear;

    public GeneratorController() {}

    public void setDefault(Stage stage, Generator generator) {
        this.stage = stage;
        this.generator = generator;
        this.canvas.setWidth(generator.getConf().getWidth());
        this.canvas.setHeight(generator.getConf().getHeight());
        this.tf_path.setText(generator.getConf().getPath());
        this.tf_path.setDisable(true);
        this.chooseBrush();
    }

    private boolean isBrush() {
        return this.generator.getCanvasState() == Generator.Tool.BRUSH;
    }

    @FXML
    protected void openFile() {}

    @FXML
    protected void save() {}

    @FXML
    protected void saveAs() {}

    @FXML
    protected void closePage() {
        this.stage.close();
    }

    @FXML
    protected void clearCanvas() {}

    @FXML
    protected void chooseBrush() {
        this.radio_brush.setSelected(true);
        this.radio_eraser.setSelected(false);
        this.generator.setCanvasState(Generator.Tool.BRUSH);
    }

    @FXML
    protected void chooseEraser() {
        this.radio_brush.setSelected(false);
        this.radio_eraser.setSelected(true);
        this.generator.setCanvasState(Generator.Tool.ERASER);
    }

    @FXML
    protected void showAbout() {}

    @FXML
    protected void pathSelect() {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Выбор папки");
        File enteredPath = new File(tf_path.getCharacters().toString());
        if(enteredPath.exists()) dirChooser.setInitialDirectory(enteredPath);
        File selectedDir = dirChooser.showDialog(this.stage);
        if(selectedDir != null) tf_path.setText(selectedDir.getPath());
    }
}
