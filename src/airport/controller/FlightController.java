///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package airport.controller;
//
//import airport.controller.interfaces.IFlightService;
//import airport.controller.service.FlightService;
//import airport.controller.utils.Responses;
//
///**
// *
// * @author Jose
// */
//public class FlightController {
//
//   
//    private static IFlightService flightService = new FlightService();
//
//    
//    public static void setFlightService(IFlightService service) {
//        flightService = service;
//    }
//
//    public static Responses createFlight(String id, String planeId, String departureLocationId, String arrivalLocationId, String scaleLocationId, String year, String month, String day, String hour, String minutes, String hoursDurationArrival, String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {
//        return flightService.createFlight(id, planeId, departureLocationId, arrivalLocationId, scaleLocationId, year, month, day, hour, minutes, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
//    }
//
//    
//    public static Responses delayFlight(String flightId, String hours, String minutes) {
//        return flightService.delayFlight(flightId, hours, minutes);
//    }
//
//    
//    public static Responses addPassengerToFlight(String flightId, String passengerId) {
//        return flightService.addPassengerToFlight(flightId, passengerId);
//    }
//
//    
//    public static Responses getAllFlights() {
//        return flightService.getAllFlights();
//    }
//
//   
//    public static Responses getFlightsByPassenger(String passengerId) {
//        return flightService.getFlightsByPassenger(passengerId);
//    }
//}
