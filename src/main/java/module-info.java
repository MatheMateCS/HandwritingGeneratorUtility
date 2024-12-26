module com.example.handwritinggeneratorutility {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.handwritinggeneratorutility to javafx.fxml;
    exports com.example.handwritinggeneratorutility;
}