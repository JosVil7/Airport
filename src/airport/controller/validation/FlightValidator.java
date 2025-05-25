/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.validation;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 *
 * @author Jose
 */
public class FlightValidator {

    public static Responses validateCreate(String id, String planeId, String departureLocationId, String arrivalLocationId, String scaleLocationId, String year, String month, String day, String hour, String minutes, String hoursDurationArrival, String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {

        if (id == null || !id.matches("^[A-Z]{3}\\d{3}$")) {
            return new Responses("Flight ID must have format XXXYYY (3 uppercase letters + 3 digits)", Status.BAD_REQUEST);
        }

        if (year == null || !year.matches("\\d{4}")
                || month == null || !month.matches("\\d{1,2}")
                || day == null || !day.matches("\\d{1,2}")
                || hour == null || !hour.matches("\\d{1,2}")
                || minutes == null || !minutes.matches("\\d{1,2}")) {
            return new Responses("Invalid date or time format", Status.BAD_REQUEST);
        }

        try {
            int y = Integer.parseInt(year);
            int m = Integer.parseInt(month);
            int d = Integer.parseInt(day);
            int h = Integer.parseInt(hour);
            int min = Integer.parseInt(minutes);
            LocalDateTime.of(y, m, d, h, min);
        } catch (DateTimeException | NumberFormatException e) {
            return new Responses("Invalid departure date", Status.BAD_REQUEST);
        }

        if (hoursDurationArrival == null || !hoursDurationArrival.matches("\\d+")
                || minutesDurationArrival == null || !minutesDurationArrival.matches("\\d+")) {
            return new Responses("Arrival duration must be digits only", Status.BAD_REQUEST);
        }

        int hoursArrival = Integer.parseInt(hoursDurationArrival);
        int minutesArrival = Integer.parseInt(minutesDurationArrival);
        if (hoursArrival == 0 && minutesArrival == 0) {
            return new Responses("Flight duration must be greater than 00:00", Status.BAD_REQUEST);
        }
        
        

        if (scaleLocationId != null && !scaleLocationId.equals("None")) {
            if (hoursDurationScale == null || !hoursDurationScale.matches("\\d+")
                    || minutesDurationScale == null || !minutesDurationScale.matches("\\d+")) {
                return new Responses("Scale duration must be digits only", Status.BAD_REQUEST);
            }
        }

        return null;
    }
}
