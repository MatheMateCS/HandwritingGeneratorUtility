module com.example.handwritinggeneratorutility {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.compiler;

    opens com.example.handwritinggeneratorutility to javafx.fxml;
    exports com.example.handwritinggeneratorutility;
    exports com.example.handwritinggeneratorutility.controller;
    opens com.example.handwritinggeneratorutility.controller to javafx.fxml;
}