package com.example.handwritinggeneratorutility.controller;

import com.example.handwritinggeneratorutility.model.Generator;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class GeneratorController {

    private Generator generator;

    private GraphicsContext g;

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
    protected Button btn_clear_label;

    @FXML
    protected Button btn_save;

    @FXML
    protected Button btn_clear;


    public GeneratorController() {}

    //TEMP
    private void AUXILIARY_TEST() {
        this.g.setFill(Color.WHITE);
        this.g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        this.g.setFill(Color.BLACK);
        this.g.fillRect(20, 10, 100, 100);
    }

    public void setDefault(Stage stage, Generator generator) {
        this.stage = stage;
        this.generator = generator;

        canvas.setWidth(generator.getConf().getWidth());
        canvas.setHeight(generator.getConf().getHeight());
        canvas.setVisible(true);
        this.g = canvas.getGraphicsContext2D();
        this.AUXILIARY_TEST();

        this.generator.setCanvas(this.canvas);

        tf_path.setText(generator.getConf().getPath());
        tf_path.setDisable(true);

        this.chooseBrush();
    }

    private boolean isBrush() {
        return this.generator.getCanvasState() == Generator.Tool.BRUSH;
    }

    private boolean isLabelValid() { return true; }
    @FXML
    protected void openFile() {}

    @FXML //@TEMP
    protected void save() {
        if(this.isLabelValid()) {
            this.generator.savePicture(tf_label.getCharacters().toString());
        } else {
            // TODO: show error
        }
    }

    @FXML
    protected void saveAs() {}

    @FXML
    protected void closePage() {
        this.stage.close();
    }

    @FXML
    protected void clearCanvas() {
        this.g.setFill(Color.WHITE);
        this.g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        this.g.setFill(Color.BLACK);
    }

    @FXML
    protected void clearLabel() {
        tf_label.setText("");
    }

    @FXML
    protected void clearAll() {
        this.clearCanvas();
        this.clearLabel();
    }

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

    @FXML
    protected void keyPressedListen(KeyEvent e) {
        if(e.isControlDown() && e.getCode().getChar().equals("Q")) {
            this.closePage();
        }
        if(e.isControlDown() && e.getCode().getChar().equals("D")) {
            this.clearCanvas();
        }
        if(e.isControlDown() && e.isAltDown() && e.getCode().getChar().equals("P")) {
            this.stage.setAlwaysOnTop(!this.stage.alwaysOnTopProperty().getValue());
        }
        if(e.getCode().getName().equals("Esc")) {
            pane.requestFocus();
        }

        if(e.getCode().getName().equals("Enter")) {}
    }

    @FXML
    protected void keyReleasedListen(KeyEvent e) {}

    @FXML
    protected void mouseClickedListen(MouseEvent e) {
        pane.requestFocus();
    }

    @FXML
    protected void canvasMousePressedListen(MouseEvent e) {}

    @FXML
    protected void canvasMouseDraggedListen(MouseEvent e) {
        if(this.generator.getCanvasState() == Generator.Tool.BRUSH) {
            this.g.fillOval(e.getX(), e.getY(), 10, 10);
        }
    }

    @FXML
    protected void canvasMouseReleasedListen(MouseEvent e) {}
}
