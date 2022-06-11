package com.example.eist22t02zweiundvierziger2022.components;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class FlightScrollPane extends ScrollPane {
        private Pane content;

        public FlightScrollPane() {
            super();

            content = new AnchorPane();


        }
}
