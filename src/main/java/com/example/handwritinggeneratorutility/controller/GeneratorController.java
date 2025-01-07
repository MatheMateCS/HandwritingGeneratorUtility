package com.example.handwritinggeneratorutility.controller;

import com.example.handwritinggeneratorutility.model.Generator;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class GeneratorController {

    private Generator generator;

    @FXML
    Stage stage;

    public GeneratorController() {}

    public void setDefault(Stage stage, Generator generator) {
        this.stage = stage;
        this.generator = generator;
    }
}
