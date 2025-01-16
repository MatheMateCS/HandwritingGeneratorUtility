package com.example.handwritinggeneratorutility.controller;

import com.example.handwritinggeneratorutility.model.Generator;
import javafx.css.PseudoClass;
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
    protected Label lb_error;

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

    public void setDefault(Stage stage, Generator generator) {
        this.stage = stage;
        this.generator = generator;

        canvas.setWidth(generator.getConf().getWidth());
        canvas.setHeight(generator.getConf().getHeight());
        this.g = canvas.getGraphicsContext2D();
        this.clearCanvas();
        canvas.setVisible(true);

        this.generator.setCanvas(this.canvas);

        tf_path.setText(generator.getConf().getPath());
        tf_path.setDisable(true);

        this.chooseBrush();
    }

    private boolean isBrush() {
        return this.generator.getCanvasState() == Generator.Tool.BRUSH;
    }

    private boolean isLabelValid() {
        // There may be any additional conditions
        return ! tf_label.getCharacters().isEmpty();
    }
    @FXML
    protected void openFile() {}

    @FXML //@TEMP
    protected void save() {
        lb_error.setVisible(false);
        if(this.isLabelValid()) {
            this.generator.savePicture(tf_label.getCharacters().toString());
        } else {
            lb_error.setText("Метка изображения должна быть указана!");
            lb_error.setVisible(true);
            tf_label.requestFocus();
        }
    }

    @FXML
    protected void saveAs() {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Выбор папки");
        File enteredPath = new File(this.generator.getConf().getPath());
        if(enteredPath.exists()) dirChooser.setInitialDirectory(enteredPath);
        File selectedDir = dirChooser.showDialog(this.stage);
        if(selectedDir != null) {
            tf_path.setText(selectedDir.getPath());
            this.generator.getConf().setPath(selectedDir.getPath());
        }
        this.save();
    }

    @FXML
    protected void closePage() {
        this.stage.close();
    }

    @FXML
    protected void clearCanvas() {
        this.g.setFill(Color.WHITE);
        this.g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        this.g.setFill(this.generator.getCanvasState() == Generator.Tool.BRUSH ? Color.BLACK : Color.WHITE);
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
        this.g.setFill(Color.BLACK);
    }

    @FXML
    protected void chooseEraser() {
        this.radio_brush.setSelected(false);
        this.radio_eraser.setSelected(true);
        this.generator.setCanvasState(Generator.Tool.ERASER);
        this.g.setFill(Color.WHITE);
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
        if(selectedDir != null) {
            tf_path.setText(selectedDir.getPath());
            this.generator.getConf().setPath(selectedDir.getPath());
        }
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

        if(e.getCode().getName().equals("Enter")) {
            btn_save.pseudoClassStateChanged(PseudoClass.getPseudoClass("hover"), true);
            btn_save.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
            this.save();
        }
    }


    @FXML
    protected void keyReleasedListen(KeyEvent e) {
        if(e.getCode().getName().equals("Enter")) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            btn_save.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
            btn_save.pseudoClassStateChanged(PseudoClass.getPseudoClass("hover"), false);
            this.clearCanvas();
        }
    }

    @FXML
    protected void mouseClickedListen(MouseEvent e) {
        pane.requestFocus();
    }

    @FXML
    protected void canvasMousePressedListen(MouseEvent e) {}

    @FXML
    protected void canvasMouseDraggedListen(MouseEvent e) {
        int radius = (int) slider.getValue();
        switch (this.generator.getCanvasState()) {
            case Generator.Tool.BRUSH : {
                this.g.fillOval(e.getX()- (double) radius /2, e.getY() - (double) radius /2, radius, radius);
                break;
            }
            case Generator.Tool.ERASER: {
                this.g.fillOval(e.getX()- (double) radius /2, e.getY() - (double) radius /2, radius, radius);
                break;
            }
            default: { break; }
        }
    }

    @FXML
    protected void canvasMouseReleasedListen(MouseEvent e) {}
}
