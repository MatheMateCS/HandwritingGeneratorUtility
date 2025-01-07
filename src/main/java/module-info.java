module com.example.handwritinggeneratorutility {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.compiler;
    requires java.xml;

    opens com.example.handwritinggeneratorutility to javafx.fxml;
    exports com.example.handwritinggeneratorutility;
    exports com.example.handwritinggeneratorutility.controller;
    exports com.example.handwritinggeneratorutility.model;
    opens com.example.handwritinggeneratorutility.controller to javafx.fxml;
}