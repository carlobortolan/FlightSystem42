package client.view;

import com.example.eist22t02zweiundvierziger2022.FlightSystemController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.text.Text;

public class TopBarUI extends ToolBar {
    private Button switchToSearchView;
    private Button switchToFlightView;
    private Button switchToServiceView;

    private final Text text;

    public TopBarUI() {
        this.switchToSearchView = new Button("Search Flights");
        this.switchToFlightView = new Button("MyFlights");
        this.switchToServiceView = new Button("In Flight Services");
        this.text = new Text("FlightSystem42");

        getItems().addAll(this.switchToSearchView, new Separator(), this.switchToFlightView, new Separator(), this.switchToServiceView, new Separator(), this.text);
    }

    public void initializeActions(FlightSystemController flightSystemController) {
        this.switchToFlightView.setOnAction(event -> {
            this.switchToFlightView.setDisable(true);
            this.switchToSearchView.setDisable(false);
            this.switchToServiceView.setDisable(false);
        });

        this.switchToSearchView.setOnAction(event -> {
            this.switchToFlightView.setDisable(false);
            this.switchToSearchView.setDisable(true);
            this.switchToServiceView.setDisable(false);
        });

        this.switchToServiceView.setOnAction(event -> {
            this.switchToFlightView.setDisable(false);
            this.switchToSearchView.setDisable(false);
            this.switchToServiceView.setDisable(true);
        });

        this.switchToFlightView.setOnAction(event -> {

        });
    }

    }
