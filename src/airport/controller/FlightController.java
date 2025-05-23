/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.utils.Status;
import airport.controller.utils.Responses;
import airport.models.Flight;
import airport.models.Location;
import airport.models.Plane;
import airport.models.database.Storage_Flight;
import airport.models.database.Storage_Location;
import airport.models.database.Storage_Plane;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Jose
 */


public class FlightController {
    public static Responses createFlight(String id, String planeId, String departureLocationId, String arrivalLocationId, String scaleLocationId, String year, String month, String day, String hour, String minutes, String hoursDurationArrival, String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale){
    try {
        // Validación del ID de vuelo: 3 letras mayúsculas + 3 dígitos
        if (id == null || !id.matches("^[A-Z]{3}\\d{3}$")) {
            return new Responses("Flight ID must have format XXXYYY (3 uppercase letters + 3 digits)", Status.BAD_REQUEST);
        }

        Storage_Flight flightStorage = Storage_Flight.getInstance();
        if (flightStorage.getFlight(id) != null) {
            return new Responses("Flight with this ID already exists", Status.BAD_REQUEST);
        }

        // Validación del avión
        Storage_Plane planeStorage = Storage_Plane.getInstance();
        Plane plane = planeStorage.getPlane(planeId);
        if (plane == null) {
            return new Responses("Plane ID not found", Status.BAD_REQUEST);
        }

        // Validación de localizaciones
        Storage_Location locationStorage = Storage_Location.getInstance();
        Location departure = locationStorage.getLocation(departureLocationId);
        Location arrival = locationStorage.getLocation(arrivalLocationId);
        Location scale = scaleLocationId.equals("None") ? null : locationStorage.getLocation(scaleLocationId);

        if (departure == null) return new Responses("Departure location not found", Status.BAD_REQUEST);
        if (arrival == null) return new Responses("Arrival location not found", Status.BAD_REQUEST);
        if (scaleLocationId != null && !scaleLocationId.equals("None") && scale == null) {
            return new Responses("Scale location not found", Status.BAD_REQUEST);
        }

        // Validación de fecha
        if (!year.matches("\\d{4}") || !month.matches("\\d{1,2}") || !day.matches("\\d{1,2}") ||
            !hour.matches("\\d{1,2}") || !minutes.matches("\\d{1,2}")) {
            return new Responses("Invalid date or time format", Status.BAD_REQUEST);
        }

        int y = Integer.parseInt(year);
        int m = Integer.parseInt(month);
        int d = Integer.parseInt(day);
        int h = Integer.parseInt(hour);
        int min = Integer.parseInt(minutes);

        LocalDateTime departureDate;
        try {
            departureDate = LocalDateTime.of(y, m, d, h, min);
        } catch (DateTimeException e) {
            return new Responses("Invalid departure date", Status.BAD_REQUEST);
        }

        // Validación de duración de vuelo
        if (!hoursDurationArrival.matches("\\d+") || !minutesDurationArrival.matches("\\d+")) {
            return new Responses("Arrival duration must be digits only", Status.BAD_REQUEST);
        }

        int hoursArrival = Integer.parseInt(hoursDurationArrival);
        int minutesArrival = Integer.parseInt(minutesDurationArrival);
        if (hoursArrival == 0 && minutesArrival == 0) {
            return new Responses("Flight duration must be greater than 00:00", Status.BAD_REQUEST);
        }

        // Validación de duración de escala
        int hoursScale = 0;
        int minutesScale = 0;
        if (scale != null) {
            if (!hoursDurationScale.matches("\\d+") || !minutesDurationScale.matches("\\d+")) {
                return new Responses("Scale duration must be digits only", Status.BAD_REQUEST);
            }
            hoursScale = Integer.parseInt(hoursDurationScale);
            minutesScale = Integer.parseInt(minutesDurationScale);
        }

        // Crear y añadir vuelo
        Flight flight;
        if (scale == null) {
            flight = new Flight(id, plane, departure, arrival, departureDate, hoursArrival, minutesArrival);
        } else {
            flight = new Flight(id, plane, departure, scale, arrival, departureDate, hoursArrival, minutesArrival, hoursScale, minutesScale);
        }

        boolean added = flightStorage.addFlight(flight);
        if (!added) {
            return new Responses("Flight could not be added", Status.INTERNAL_SERVER_ERROR);
        }

        return new Responses("Flight has been created successfully", Status.CREATED);

    } catch (Exception ex) {
        return new Responses("Unexpected error occurred", Status.INTERNAL_SERVER_ERROR);
    }
}

}

//try {
//        // Validación del ID de vuelo: 3 letras mayúsculas + 3 dígitos
//        if (id == null || !id.matches("^[A-Z]{3}\\d{3}$")) {
//            return new Responses("Flight ID must have format XXXYYY (3 uppercase letters + 3 digits)", Status.BAD_REQUEST);
//        }
//
//        Storage_Flight flightStorage = Storage_Flight.getInstance();
//        if (flightStorage.getFlight(id) != null) {
//            return new Responses("Flight with this ID already exists", Status.BAD_REQUEST);
//        }
//
//        // Validación del avión
//        Storage_Plane planeStorage = Storage_Plane.getInstance();
//        Plane plane = planeStorage.getPlane(planeId);
//        if (plane == null) {
//            return new Responses("Plane ID not found", Status.BAD_REQUEST);
//        }
//
//        // Validación de localizaciones
//        Storage_Location locationStorage = Storage_Location.getInstance();
//        Location departure = locationStorage.getLocation(departureLocationId);
//        Location arrival = locationStorage.getLocation(arrivalLocationId);
//        Location scale = scaleLocationId.equals("None") ? null : locationStorage.getLocation(scaleLocationId);
//
//        if (departure == null) return new Responses("Departure location not found", Status.BAD_REQUEST);
//        if (arrival == null) return new Responses("Arrival location not found", Status.BAD_REQUEST);
//        if (scaleLocationId != null && !scaleLocationId.equals("None") && scale == null) {
//            return new Responses("Scale location not found", Status.BAD_REQUEST);
//        }
//
//        // Validación de fecha
//        if (!year.matches("\\d{4}") || !month.matches("\\d{1,2}") || !day.matches("\\d{1,2}") ||
//            !hour.matches("\\d{1,2}") || !minutes.matches("\\d{1,2}")) {
//            return new Responses("Invalid date or time format", Status.BAD_REQUEST);
//        }
//
//        int y = Integer.parseInt(year);
//        int m = Integer.parseInt(month);
//        int d = Integer.parseInt(day);
//        int h = Integer.parseInt(hour);
//        int min = Integer.parseInt(minutes);
//
//        LocalDateTime departureDate;
//        try {
//            departureDate = LocalDateTime.of(y, m, d, h, min);
//        } catch (DateTimeException e) {
//            return new Responses("Invalid departure date", Status.BAD_REQUEST);
//        }
//
//        // Validación de duración de vuelo
//        if (!hoursDurationArrival.matches("\\d+") || !minutesDurationArrival.matches("\\d+")) {
//            return new Responses("Arrival duration must be digits only", Status.BAD_REQUEST);
//        }
//
//        int hoursArrival = Integer.parseInt(hoursDurationArrival);
//        int minutesArrival = Integer.parseInt(minutesDurationArrival);
//        if (hoursArrival == 0 && minutesArrival == 0) {
//            return new Responses("Flight duration must be greater than 00:00", Status.BAD_REQUEST);
//        }
//
//        // Validación de duración de escala
//        int hoursScale = 0;
//        int minutesScale = 0;
//        if (scale != null) {
//            if (!hoursDurationScale.matches("\\d+") || !minutesDurationScale.matches("\\d+")) {
//                return new Responses("Scale duration must be digits only", Status.BAD_REQUEST);
//            }
//            hoursScale = Integer.parseInt(hoursDurationScale);
//            minutesScale = Integer.parseInt(minutesDurationScale);
//        }
//
//        // Crear y añadir vuelo
//        Flight flight;
//        if (scale == null) {
//            flight = new Flight(id, plane, departure, arrival, departureDate, hoursArrival, minutesArrival);
//        } else {
//            flight = new Flight(id, plane, departure, scale, arrival, departureDate, hoursArrival, minutesArrival, hoursScale, minutesScale);
//        }
//
//        boolean added = flightStorage.addFlight(flight);
//        if (!added) {
//            return new Responses("Flight could not be added", Status.INTERNAL_SERVER_ERROR);
//        }
//
//        return new Responses("Flight has been created successfully", Status.CREATED);
//
//    } catch (Exception ex) {
//        return new Responses("Unexpected error occurred", Status.INTERNAL_SERVER_ERROR);
//    }
