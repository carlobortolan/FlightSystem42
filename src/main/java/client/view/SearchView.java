//package client.view;
//
//import com.example.eist22t02zweiundvierziger2022.SearchController;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.control.TextInputControl;
//import lufthansa.FlightParser;
//import model.Flight;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class SearchView extends Canvas {
//
//    private TextField fromBox;
//    private TextField toBox;
//    private TextField dateYearBox;
//    private TextField dateMonthBox;
//    private TextField dateDayOfMonthBox;
//    private Button searchButton;
//    private SearchController searchController;
//
//    public SearchView(SearchController searchController) {
////
////        this.searchBox = new TextField();
////        this.searchBox.setOnMouseEntered(event -> {
//
////        });
//
//        this.searchButton = new Button("Search");
//
//        this.searchButton.setOnAction(event -> {
//            try {
//                ArrayList<Flight> flights = FlightParser.fetchFlights(new FlightParser().searchFlight(this.fromBox.getCharacters().toString(), this.fromBox.getCharacters().toString(), this.fromBox.getCharacters().toString(), this.fromBox.getCharacters().toString(), this.fromBox.getCharacters().toString()), 0))
//                ;
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        });
//
//
//    }
//
//}