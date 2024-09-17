module com.example.astaralgorithm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.astaralgorithm to javafx.fxml;
    exports com.example.astaralgorithm;

}
