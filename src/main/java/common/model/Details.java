package common.model;

import java.time.Duration;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Details {
    private LocalDateTime timeOfDeparture;
    private LocalDateTime eta;
    private int stops;
    private String airline;
    private String aircraftCode;
    private String terminalFrom;
    private String terminalTo;

    public Details(LocalDateTime timeOfDeparture, LocalDateTime eta, int stops, String airline, String aircraftCode, String terminalFrom, String terminalTo) {
        this.timeOfDeparture = timeOfDeparture;
//        this.eta = Details.calculateETA(timeOfDeparture, duration);
        this.eta = eta;
        this.stops = stops;
        this.airline = airline;
        this.aircraftCode = aircraftCode;
        this.terminalFrom = terminalFrom;
        this.terminalTo = terminalTo;
    }

//    unnecessary code
//    public static LocalDateTime calculateETA(LocalDateTime timeOfDeparture, String d) {
//
//        int arrYear = timeOfDeparture.getYear();
//        int arrMonth = timeOfDeparture.getMonthValue();
//        int arrDay = timeOfDeparture.getDayOfMonth();
//        int arrHours = timeOfDeparture.getHour();
//        int arrMinutes = timeOfDeparture.getMinute();
//
//
//        long durationMinutes = 0;
//        long durationHours = 0;
//
//        if (d.contains("M") && d.contains("H")) {
//            durationHours = Integer.parseInt(d.substring(0, d.indexOf("H")));
//            durationMinutes = Integer.parseInt(d.substring(d.indexOf("H") + 1, d.indexOf("M")));
//        } else if (d.contains("H")) {
//            durationHours = Integer.parseInt(d.substring(0, d.indexOf("H")));
//        } else if (d.contains("M")) {
//            durationMinutes = Long.parseLong(d.substring(0, d.indexOf("M") - 1));
//        }
//
//
//        arrMinutes += durationMinutes;
//
//        if (arrMinutes >= 60) {
//            arrHours++;
//            arrMinutes %= 60;
//            if (arrHours >= 24) {
//                arrDay++;
//                arrHours %= 24;
//                if ((arrMonth == 1 || arrMonth == 3 || arrMonth == 5 || arrMonth == 7 || arrMonth == 8 || arrMonth == 10 || arrMonth == 12) && arrDay >= 32) {
//                    arrMonth++;
//                    arrDay %= 32;
//                } else {
//                    if ((arrMonth == 2 || arrMonth == 4 || arrMonth == 6 || arrMonth == 9 || arrMonth == 11) && arrDay >= 31) {
//                        arrMonth++;
//                        arrDay %= 31;
//                    }
//                }
//                if (arrMonth >= 12) {
//                    arrYear++;
//                    arrMonth %= 12;
//                }
//            }
//        }
//
//        arrHours += durationHours;
//
//        if (arrHours >= 24) {
//            arrDay++;
//            arrHours %= 24;
//            if ((arrMonth == 1 || arrMonth == 3 || arrMonth == 5 || arrMonth == 7 || arrMonth == 8 || arrMonth == 10 || arrMonth == 12) && arrDay >= 32) {
//                arrMonth++;
//                arrDay %= 32;
//            } else {
//                if ((arrMonth == 2 || arrMonth == 4 || arrMonth == 6 || arrMonth == 9 || arrMonth == 11) && arrDay >= 31) {
//                    arrMonth++;
//                    arrDay %= 31;
//                }
//            }
//            if (arrMonth >= 12) {
//                arrYear++;
//                arrMonth %= 12;
//            }
//        }
//
//
//        return LocalDateTime.of(arrYear, arrMonth, arrDay, arrHours, arrMinutes);
//    }
}
