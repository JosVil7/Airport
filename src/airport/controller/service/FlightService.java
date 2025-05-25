/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.service;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.controller.validation.FlightValidator;
import airport.models.Flight;
import airport.models.Location;
import airport.models.Plane;
import airport.models.database.Storage_Flight;
import airport.models.database.Storage_Location;
import airport.models.database.Storage_Plane;
import java.time.LocalDateTime;

/**
 *
 * @author Jose
 */
public class FlightService {
    public static Responses createFlight(String id, String planeId, String departureLocationId, String arrivalLocationId, String scaleLocationId, String year, String month, String day, String hour, String minutes,String hoursDurationArrival, String minutesDurationArrival,String hoursDurationScale, String minutesDurationScale) {

        try {
            Responses validationResponse = FlightValidator.validateCreate(id, planeId, departureLocationId, arrivalLocationId, scaleLocationId, year, month, day, hour, minutes, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);

            if (validationResponse != null) return validationResponse;

            Storage_Flight flightStorage = Storage_Flight.getInstance();
            if (flightStorage.getFlight(id) != null) {
                return new Responses("Flight with this ID already exists", Status.BAD_REQUEST);
            }

            Storage_Plane planeStorage = Storage_Plane.getInstance();
            Plane plane = planeStorage.getPlane(planeId);
            if (plane == null) {
                return new Responses("Plane ID not found", Status.BAD_REQUEST);
            }

            Storage_Location locationStorage = Storage_Location.getInstance();
            Location departure = locationStorage.getLocation(departureLocationId);
            Location arrival = locationStorage.getLocation(arrivalLocationId);
            Location scale = (scaleLocationId == null || scaleLocationId.equals("None")) ? null : locationStorage.getLocation(scaleLocationId);

            if (departure == null) return new Responses("Departure location not found", Status.BAD_REQUEST);
            if (arrival == null) return new Responses("Arrival location not found", Status.BAD_REQUEST);
            if (scaleLocationId != null && !scaleLocationId.equals("None") && scale == null) {
                return new Responses("Scale location not found", Status.BAD_REQUEST);
            }

            int yearu = Integer.parseInt(year);
            int monthu = Integer.parseInt(month);
            int dayu = Integer.parseInt(day);
            int houru = Integer.parseInt(hour);
            int minuteu = Integer.parseInt(minutes);
            LocalDateTime departureDate = LocalDateTime.of(yearu, monthu, dayu, houru, minuteu);

            int hoursArrival = Integer.parseInt(hoursDurationArrival);
            int minutesArrival = Integer.parseInt(minutesDurationArrival);

            int hoursScale = 0;
            int minutesScale = 0;
            if (scale != null) {
                hoursScale = Integer.parseInt(hoursDurationScale);
                minutesScale = Integer.parseInt(minutesDurationScale);
            }

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
