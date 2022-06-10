module com.example.eist22t02zweiundvierziger2022 {
    requires spring.boot.autoconfigure;
    requires javafx.graphics;
    requires spring.boot;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
//    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires spring.web;

    opens com.example.eist22t02zweiundvierziger2022 to javafx.fxml;
    exports com.example.eist22t02zweiundvierziger2022;
}