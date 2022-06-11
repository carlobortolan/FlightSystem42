module com.example.eist22t02zweiundvierziger2022 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
//    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.eist22t02zweiundvierziger2022 to javafx.fxml;
    exports com.example.eist22t02zweiundvierziger2022;
    exports com.example.eist22t02zweiundvierziger2022.components;
    opens com.example.eist22t02zweiundvierziger2022.components to javafx.fxml;
    exports com.example.eist22t02zweiundvierziger2022.controllers;
    opens com.example.eist22t02zweiundvierziger2022.controllers to javafx.fxml;
}