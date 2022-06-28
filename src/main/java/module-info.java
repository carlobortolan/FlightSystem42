module com.example.eist22t02zweiundvierziger2022 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires javafx.media;
    requires jdk.jsobject;
    requires org.json;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.web;

    opens com.example.eist22t02zweiundvierziger2022 to javafx.fxml;
    exports com.example.eist22t02zweiundvierziger2022;
    exports com.example.eist22t02zweiundvierziger2022.components;
    opens com.example.eist22t02zweiundvierziger2022.components to javafx.fxml;
    exports com.example.eist22t02zweiundvierziger2022.controllers;
    opens com.example.eist22t02zweiundvierziger2022.controllers to javafx.fxml;

    opens hello.world.demo to spring.core;
    exports hello.world.demo to spring.beans, spring.context,javafx.graphics;
}