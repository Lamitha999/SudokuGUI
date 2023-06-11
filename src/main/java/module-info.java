module com.example.scenebuilder {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.scenebuilder to javafx.fxml;
    exports com.example.scenebuilder;
}