///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package airport.controller.service;
//
//import airport.controller.interfaces.IFlightService;
//import airport.controller.utils.Responses;
//import airport.controller.utils.Status;
//import airport.controller.validation.FlightValidator;
//import airport.models.Flight;
//import airport.models.Location;
//import airport.models.Passenger;
//import airport.models.Plane;
//import airport.models.database.Storage_Flight;
//import airport.models.database.Storage_Location;
//import airport.models.database.Storage_Passenger;
//import airport.models.database.Storage_Plane;
//import airport.models.database.interfaces.IFlightStorage;
//import airport.models.database.interfaces.ILocationStorage;
//import airport.models.database.interfaces.IPassengerStorage;
//import airport.models.database.interfaces.IPlaneStorage;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeParseException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author Jose
// */
//public class FlightService implements IFlightService {
//
//    // Using interfaces for dependencies (Dependency Inversion Principle)
//    private IFlightStorage flightStorage;
//    private IPlaneStorage planeStorage;
//    private ILocationStorage locationStorage;
//    private IPassengerStorage passengerStorage; // Add passenger storage dependency
//
//    public FlightService() {
//        this.flightStorage = Storage_Flight.getInstance();
//        this.planeStorage = Storage_Plane.getInstance();
//        this.locationStorage = Storage_Location.getInstance();
//        this.passengerStorage = Storage_Passenger.getInstance(); // Get instance for PassengerStorage
//    }
//
//    // Constructor for Dependency Injection (better for testing)
//    public FlightService(IFlightStorage flightStorage, IPlaneStorage planeStorage, ILocationStorage locationStorage, IPassengerStorage passengerStorage) {
//        this.flightStorage = flightStorage;
//        this.planeStorage = planeStorage;
//        this.locationStorage = locationStorage;
//        this.passengerStorage = passengerStorage;
//    }
//
//    @Override
//    public Responses getPlaneById(String id) {
//        // This method is in IFlightService, but logically belongs to IPlaneService.
//        // For quick fix, delegate to PlaneStorage directly.
//        try {
//            Plane plane = planeStorage.getPlane(id);
//            if (plane == null) {
//                return new Responses("Plane with ID " + id + " not found.", Status.NOT_FOUND);
//            }
//            return new Responses("Plane retrieved successfully.", Status.OK, plane.clone());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Responses("An unexpected error occurred while retrieving the plane by ID.", Status.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @Override
//    public Responses createFlight(String id, String planeId, String departureLocationId, String arrivalLocationId, String scaleLocationId, String year, String month, String day, String hour, String minutes, String hoursDurationArrival, String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {
//        Responses validationResponse = FlightValidator.validateCreate(id, planeId, departureLocationId, arrivalLocationId, scaleLocationId, year, month, day, hour, minutes, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
//        if (validationResponse != null) {
//            return validationResponse;
//        }
//
//        try {
//            if (flightStorage.getFlight(id) != null) {
//                return new Responses("Flight with this ID already exists.", Status.BAD_REQUEST);
//            }
//
//            Plane plane = planeStorage.getPlane(planeId);
//            if (plane == null) {
//                return new Responses("Plane ID not found: " + planeId, Status.BAD_REQUEST);
//            }
//
//            Location departure = locationStorage.getLocation(departureLocationId);
//            if (departure == null) {
//                return new Responses("Departure location not found: " + departureLocationId, Status.BAD_REQUEST);
//            }
//
//            Location arrival = locationStorage.getLocation(arrivalLocationId);
//            if (arrival == null) {
//                return new Responses("Arrival location not found: " + arrivalLocationId, Status.BAD_REQUEST);
//            }
//
//            Location scale = null;
//            // Check if scaleLocationId is provided and not the default "Location" string or "None"
//            if (scaleLocationId != null && !scaleLocationId.trim().isEmpty() && !scaleLocationId.equals("Location") && !scaleLocationId.equals("None")) {
//                scale = locationStorage.getLocation(scaleLocationId);
//                if (scale == null) {
//                    return new Responses("Scale location not found: " + scaleLocationId, Status.BAD_REQUEST);
//                }
//            }
//
//
//            int yearInt = Integer.parseInt(year);
//            int monthInt = Integer.parseInt(month);
//            int dayInt = Integer.parseInt(day);
//            int hourInt = Integer.parseInt(hour);
//            int minuteInt = Integer.parseInt(minutes);
//            LocalDateTime departureDate = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minuteInt);
//
//            int hoursArrival = Integer.parseInt(hoursDurationArrival);
//            int minutesArrival = Integer.parseInt(minutesDurationArrival);
//
//            int hoursScale = 0;
//            int minutesScale = 0;
//            if (scale != null) { // Only parse scale duration if a scale location is actually provided
//                hoursScale = Integer.parseInt(hoursDurationScale);
//                minutesScale = Integer.parseInt(minutesDurationScale);
//            }
//
//            Flight flight;
//            if (scale == null) {
//                flight = new Flight(id, plane, departure, arrival, departureDate, hoursArrival, minutesArrival);
//            } else {
//                flight = new Flight(id, plane, departure, scale, arrival, departureDate, hoursArrival, minutesArrival, hoursScale, minutesScale);
//            }
//
//            // Link flight to plane (bidirectional relationship)
//            plane.addFlight(flight); // Ensure Plane class has this method
//
//            boolean added = flightStorage.addFlight(flight);
//            if (!added) {
//                return new Responses("Flight could not be added to storage.", Status.INTERNAL_SERVER_ERROR);
//            }
//
//            return new Responses("Flight " + id + " created successfully.", Status.CREATED, flight.clone()); // Return a clone for consistency
//
//        } catch (NumberFormatException e) {
//            return new Responses("Invalid numeric input for date/time or duration: " + e.getMessage(), Status.BAD_REQUEST);
//        } catch (DateTimeParseException e) {
//            return new Responses("Invalid date format: " + e.getMessage(), Status.BAD_REQUEST);
//        } catch (Exception ex) {
//            ex.printStackTrace(); // Log the exception for debugging
//            return new Responses("An unexpected error occurred during flight creation: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @Override
//    public Responses delayFlight(String flightId, String hours, String minutes) {
//        Responses validationResponse = FlightValidator.validateDelay(flightId, hours, minutes);
//        if (validationResponse != null) {
//            return validationResponse;
//        }
//
//        try {
//            Flight flight = flightStorage.getFlight(flightId);
//            if (flight == null) {
//                return new Responses("Flight with ID " + flightId + " not found.", Status.NOT_FOUND);
//            }
//
//            int delayHours = Integer.parseInt(hours);
//            int delayMinutes = Integer.parseInt(minutes);
//
//            flight.delay(delayHours, delayMinutes); // Apply delay to the flight object
//
//            boolean updated = flightStorage.updateFlight(flight); // Update in storage
//            if (!updated) {
//                return new Responses("Failed to update flight after delay.", Status.INTERNAL_SERVER_ERROR);
//            }
//            return new Responses("Flight " + flightId + " delayed successfully by " + delayHours + " hours and " + delayMinutes + " minutes.", Status.OK, flight.clone());
//        } catch (NumberFormatException e) {
//            return new Responses("Invalid numeric format for delay hours/minutes.", Status.BAD_REQUEST);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Responses("An unexpected error occurred during flight delay: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @Override
//    public Responses addPassengerToFlight(String flightId, String passengerId) {
//        Responses validationResponse = FlightValidator.validateAddPassenger(flightId, passengerId);
//        if (validationResponse != null) {
//            return validationResponse;
//        }
//
//        try {
//            Flight flight = flightStorage.getFlight(flightId);
//            if (flight == null) {
//                return new Responses("Flight with ID " + flightId + " not found.", Status.NOT_FOUND);
//            }
//
//            Passenger passenger = passengerStorage.getPassenger(Long.parseLong(passengerId));
//            if (passenger == null) {
//                return new Responses("Passenger with ID " + passengerId + " not found.", Status.BAD_REQUEST);
//            }
//
//            if (flight.hasPassenger(passenger.getId())) {
//                return new Responses("Passenger with ID " + passengerId + " is already on flight " + flightId + ".", Status.BAD_REQUEST);
//            }
//
//            flight.addPassenger(passenger); // Add passenger to the flight model
//            passenger.addFlight(flight); // Add flight to passenger (bidirectional relationship)
//
//            boolean updated = flightStorage.updateFlight(flight); // Update flight in storage
//            if (!updated) {
//                return new Responses("Failed to add passenger to flight in storage.", Status.INTERNAL_SERVER_ERROR);
//            }
//            // If Passenger data is changed (e.g., adding a flight to their list), update it in storage
//            // This assumes PassengerStorage has an update method.
//            // new PassengerService().updatePassenger(passenger.getId().toString(), passenger.getFirstName(), passenger.getLastName(), String.valueOf(passenger.getBirthYear()), String.valueOf(passenger.getBirthMonth()), String.valueOf(passenger.getBirthDay()), passenger.getPhoneCode(), passenger.getPhoneNumber(), passenger.getCountry());
//            // A more direct update method for passenger (if available in PassengerStorage/Service) would be better
//            // passengerStorage.updatePassenger(passenger); // Assuming this method exists
//
//            return new Responses("Passenger " + passengerId + " added to flight " + flightId + " successfully.", Status.OK, flight.clone());
//
//        } catch (NumberFormatException e) {
//            return new Responses("Invalid passenger ID format.", Status.BAD_REQUEST);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Responses("An unexpected error occurred while adding passenger to flight: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @Override
//    public Responses getAllFlights() {
//        try {
//            List<Flight> flights = flightStorage.getAllFlights();
//            if (flights.isEmpty()) {
//                return new Responses("No flights found.", Status.NOT_FOUND);
//            }
//            return new Responses("Flights retrieved successfully", Status.OK, flights);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Responses("An unexpected error occurred while retrieving all flights.", Status.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @Override
//    public Responses getFlightsByPassenger(String passengerId) {
//        try {
//            long pId = Long.parseLong(passengerId);
//            // Get the actual passenger object to ensure it exists
//            Passenger passenger = passengerStorage.getPassenger(pId);
//            if (passenger == null) {
//                 return new Responses("Passenger with ID " + passengerId + " not found.", Status.NOT_FOUND);
//            }
//
//            // The passenger object itself should hold its flights, or we filter from all flights
//            // Assuming passenger.getFlights() returns a List<Flight>
//            List<Flight> flightsForPassenger = new ArrayList<>();
//            for (Flight f : passenger.getFlights()) { // Iterate through flights associated with the passenger
//                flightsForPassenger.add(f.clone());
//            }
//
//            // If passenger object does not directly hold flights (e.g., only Flight holds passengers)
//            // then iterate through all flights and check if passenger is on it.
//            if (flightsForPassenger.isEmpty()) { // If passenger.getFlights() was empty or didn't exist
//                 List<Flight> allFlights = flightStorage.getAllFlights();
//                 for (Flight flight : allFlights) {
//                     if (flight.hasPassenger(pId)) {
//                         flightsForPassenger.add(flight.clone());
//                     }
//                 }
//            }
//
//
//            if (flightsForPassenger.isEmpty()) {
//                return new Responses("No flights found for passenger ID " + passengerId + ".", Status.NOT_FOUND);
//            }
//            return new Responses("Flights for passenger " + passengerId + " retrieved successfully.", Status.OK, flightsForPassenger);
//        } catch (NumberFormatException e) {
//            return new Responses("Invalid passenger ID format.", Status.BAD_REQUEST);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Responses("An unexpected error occurred while retrieving flights for passenger: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
