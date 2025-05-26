///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package airport.controller.validation;
//
//import airport.controller.utils.Responses;
//import airport.controller.utils.Status;
//import java.time.DateTimeException;
//import java.time.LocalDateTime;
//
///**
// *
// * @author Jose
// */
//public class FlightValidator {
//
//   
//    public static Responses validateCreate(String id, String planeId, String departureLocationId, String arrivalLocationId, String scaleLocationId, String year, String month, String day, String hour, String minutes, String hoursDurationArrival, String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {
//
//        
//        if (id == null || id.trim().isEmpty()) {
//            return new Responses("Flight ID must not be empty.", Status.BAD_REQUEST);
//        }
//        if (!id.matches("^[A-Z]{3}\\d{3}$")) {
//            return new Responses("Flight ID must have format XXXYYY (3 uppercase letters + 3 digits).", Status.BAD_REQUEST);
//        }
//
//        
//        if (planeId == null || planeId.trim().isEmpty() || planeId.equals("Plane")) {
//             return new Responses("Plane must be selected.", Status.BAD_REQUEST);
//        }
//
//       
//        if (departureLocationId == null || departureLocationId.trim().isEmpty() || departureLocationId.equals("Location")) {
//            return new Responses("Departure location must be selected.", Status.BAD_REQUEST);
//        }
//
//        if (arrivalLocationId == null || arrivalLocationId.trim().isEmpty() || arrivalLocationId.equals("Location")) {
//            return new Responses("Arrival location must be selected.", Status.BAD_REQUEST);
//        }
//
//        if (departureLocationId.equals(arrivalLocationId)) {
//            return new Responses("Departure and Arrival locations cannot be the same.", Status.BAD_REQUEST);
//        }
//
//        if (year == null || year.trim().isEmpty() || !year.matches("\\d{4}")
//                || month == null || month.trim().isEmpty() || !month.matches("\\d{1,2}")
//                || day == null || day.trim().isEmpty() || !day.matches("\\d{1,2}")
//                || hour == null || hour.trim().isEmpty() || !hour.matches("\\d{1,2}")
//                || minutes == null || minutes.trim().isEmpty() || !minutes.matches("\\d{1,2}")) {
//            return new Responses("Invalid date or time format. Please provide valid numbers.", Status.BAD_REQUEST);
//        }
//
//        try {
//            int y = Integer.parseInt(year);
//            int m = Integer.parseInt(month);
//            int d = Integer.parseInt(day);
//            int h = Integer.parseInt(hour);
//            int min = Integer.parseInt(minutes);
//            LocalDateTime departureDateTime = LocalDateTime.of(y, m, d, h, min);
//
//            if (departureDateTime.isBefore(LocalDateTime.now().minusYears(1))) { 
//                return new Responses("Departure date cannot be more than 1 year in the past.", Status.BAD_REQUEST);
//            }
//            if (departureDateTime.isAfter(LocalDateTime.now().plusYears(5))) { // Example: not more than 5 years in future
//                return new Responses("Departure date cannot be more than 5 years in the future.", Status.BAD_REQUEST);
//            }
//
//        } catch (DateTimeException | NumberFormatException e) {
//            return new Responses("Invalid departure date or time. Please ensure date components are valid and within range.", Status.BAD_REQUEST);
//        }
//
//        // Arrival Duration Validation
//        if (hoursDurationArrival == null || hoursDurationArrival.trim().isEmpty() || !hoursDurationArrival.matches("\\d+")
//                || minutesDurationArrival == null || minutesDurationArrival.trim().isEmpty() || !minutesDurationArrival.matches("\\d+")) {
//            return new Responses("Arrival duration hours and minutes must be non-empty digits.", Status.BAD_REQUEST);
//        }
//        try {
//            int hoursArrival = Integer.parseInt(hoursDurationArrival);
//            int minutesArrival = Integer.parseInt(minutesDurationArrival);
//            if (hoursArrival < 0 || minutesArrival < 0 || minutesArrival >= 60) {
//                return new Responses("Invalid arrival duration. Hours cannot be negative, minutes must be 0-59.", Status.BAD_REQUEST);
//            }
//            if (hoursArrival == 0 && minutesArrival == 0) {
//                return new Responses("Flight duration must be greater than 00:00.", Status.BAD_REQUEST);
//            }
//        } catch (NumberFormatException e) {
//            return new Responses("Invalid Arrival Duration format. Must be valid numbers.", Status.BAD_REQUEST);
//        }
//
//       
//        if (scaleLocationId != null && !scaleLocationId.trim().isEmpty() && !scaleLocationId.equals("Location")) {
//            if (scaleLocationId.equals(departureLocationId) || scaleLocationId.equals(arrivalLocationId)) {
//                return new Responses("Scale location cannot be the same as departure or arrival location.", Status.BAD_REQUEST);
//            }
//            if (hoursDurationScale == null || hoursDurationScale.trim().isEmpty() || !hoursDurationScale.matches("\\d+")
//                    || minutesDurationScale == null || minutesDurationScale.trim().isEmpty() || !minutesDurationScale.matches("\\d+")) {
//                return new Responses("Scale duration hours and minutes must be non-empty digits if scale location is provided.", Status.BAD_REQUEST);
//            }
//            try {
//                int hoursScale = Integer.parseInt(hoursDurationScale);
//                int minutesScale = Integer.parseInt(minutesDurationScale);
//                if (hoursScale < 0 || minutesScale < 0 || minutesScale >= 60) {
//                    return new Responses("Invalid scale duration. Hours cannot be negative, minutes must be 0-59.", Status.BAD_REQUEST);
//                }
//                if (hoursScale == 0 && minutesScale == 0) {
//                    return new Responses("Scale duration must be greater than 00:00 if a scale location is selected.", Status.BAD_REQUEST);
//                }
//            } catch (NumberFormatException e) {
//                return new Responses("Invalid Scale Duration format. Must be valid numbers.", Status.BAD_REQUEST);
//            }
//        } else {
//            try {
//                int hoursScale = 0;
//                if (hoursDurationScale != null && !hoursDurationScale.trim().isEmpty() && !hoursDurationScale.equals("Hour")) {
//                    hoursScale = Integer.parseInt(hoursDurationScale);
//                }
//
//                int minutesScale = 0;
//                if (minutesDurationScale != null && !minutesDurationScale.trim().isEmpty() && !minutesDurationScale.equals("Minute")) {
//                    minutesScale = Integer.parseInt(minutesDurationScale);
//                }
//
//                if (hoursScale != 0 || minutesScale != 0) {
//                     return new Responses("Scale duration must be 00:00 if no scale location is selected.", Status.BAD_REQUEST);
//                }
//            } catch (NumberFormatException e) {
//                 return new Responses("Invalid Scale Duration format. Must be valid numbers if provided, or 00:00 if no scale location.", Status.BAD_REQUEST);
//            }
//        }
//
//        return null; 
//    }
//
//    
//    public static Responses validateDelay(String flightId, String hours, String minutes) {
//        if (flightId == null || flightId.trim().isEmpty() || flightId.equals("ID")) { // Check against "ID" default combobox value
//            return new Responses("Flight ID must be selected for delay.", Status.BAD_REQUEST);
//        }
//        if (!flightId.matches("^[A-Z]{3}\\d{3}$")) {
//            return new Responses("Flight ID format is invalid (expected XXXYYY).", Status.BAD_REQUEST);
//        }
//
//        if (hours == null || hours.trim().isEmpty() || minutes == null || minutes.trim().isEmpty()) {
//            return new Responses("Delay hours and minutes must not be empty.", Status.BAD_REQUEST);
//        }
//        // Check if selected items are default placeholders
//        if (hours.equals("Hour") || minutes.equals("Minute")) {
//             return new Responses("Please select valid hours and minutes for delay.", Status.BAD_REQUEST);
//        }
//
//
//        if (!hours.matches("\\d+") || !minutes.matches("\\d+")) {
//            return new Responses("Delay hours and minutes must contain only digits.", Status.BAD_REQUEST);
//        }
//
//        try {
//            int hoursInt = Integer.parseInt(hours);
//            int minutesInt = Integer.parseInt(minutes);
//
//            if (hoursInt < 0 || minutesInt < 0 || minutesInt >= 60) {
//                return new Responses("Invalid delay duration. Hours cannot be negative, minutes must be 0-59.", Status.BAD_REQUEST);
//            }
//            if (hoursInt == 0 && minutesInt == 0) {
//                return new Responses("Delay duration must be greater than 00:00.", Status.BAD_REQUEST);
//            }
//        } catch (NumberFormatException e) {
//            return new Responses("Invalid numeric format for delay hours/minutes.", Status.BAD_REQUEST);
//        }
//
//        return null;
//    }
//
//    public static Responses validateAddPassenger(String flightId, String passengerId) {
//        if (flightId == null || flightId.trim().isEmpty() || flightId.equals("Flight")) { // Check against "Flight" default combobox value
//            return new Responses("Flight must be selected to add a passenger.", Status.BAD_REQUEST);
//        }
//        if (!flightId.matches("^[A-Z]{3}\\d{3}$")) {
//            return new Responses("Flight ID format is invalid (expected XXXYYY).", Status.BAD_REQUEST);
//        }
//
//        if (passengerId == null || passengerId.trim().isEmpty()) {
//            return new Responses("Passenger ID must not be empty.", Status.BAD_REQUEST);
//        }
//        if (!passengerId.matches("\\d+")) {
//            return new Responses("Passenger ID must contain only numbers.", Status.BAD_REQUEST);
//        }
//        try {
//            long pId = Long.parseLong(passengerId);
//            if (pId < 0) {
//                return new Responses("Passenger ID must be greater than or equal to 0.", Status.BAD_REQUEST);
//            }
//        } catch (NumberFormatException e) {
//            return new Responses("Invalid Passenger ID format. Must be a valid number.", Status.BAD_REQUEST);
//        }
//
//        return null;
//    }
//}
