module com.example.eist22t02zweiundvierziger2022 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires javafx.media;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.web;
    requires jdk.jsobject;

    opens com.example.eist22t02zweiundvierziger2022 to javafx.fxml;
    exports com.example.eist22t02zweiundvierziger2022;
    exports com.example.eist22t02zweiundvierziger2022.components;
    opens com.example.eist22t02zweiundvierziger2022.components to javafx.fxml;
    exports com.example.eist22t02zweiundvierziger2022.controllers;
    opens com.example.eist22t02zweiundvierziger2022.controllers to javafx.fxml;

    requires org.apache.tomcat.embed.core;
    requires spring.webflux;
    opens hello.world.demo to spring.core;
    exports hello.world.demo to spring.beans, spring.context,javafx.graphics, spring.web, com.example.eist22t02zweiundvierziger2022;
}