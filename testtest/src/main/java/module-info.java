module com.example.testtest {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testtest to javafx.fxml;
    exports com.example.testtest;
}