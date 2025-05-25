/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.service.FlightService;
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

    public static Responses createFlight(String id, String planeId, String departureLocationId, String arrivalLocationId, String scaleLocationId, String year, String month, String day, String hour, String minutes, String hoursDurationArrival, String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {
        return FlightService.createFlight(id, planeId, departureLocationId, arrivalLocationId, scaleLocationId, year, month, day, hour, minutes, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
    }
}
