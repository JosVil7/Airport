/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.interfaces.ILocationService;
import airport.controller.service.LocationService;
import airport.controller.utils.Responses;

/**
 *
 * @author Jose
 */

public class LocationController {

    private static ILocationService locationService = new LocationService(); // Dependency Injection

    // Setter for dependency injection (useful for testing)
    public static void setLocationService(ILocationService service) {
        locationService = service;
    }

    /**
     * Handles the creation of a new airport location.
     * @param id Airport ID.
     * @param name Airport name.
     * @param city City of the airport.
     * @param country Country of the airport.
     * @param latitude Latitude coordinate.
     * @param longitude Longitude coordinate.
     * @return A Responses object indicating the outcome of the operation.
     */
    public static Responses createLocation(String id, String name, String city, String country, String latitude, String longitude) {
        return locationService.createLocation(id, name, city, country, latitude, longitude);
    }

    /**
     * Retrieves a list of all airport locations.
     * @return A Responses object containing a List of Locations.
     */
    public static Responses getAllLocations() {
        return locationService.getAllLocations();
    }

    /**
     * Retrieves a specific location by its ID.
     * @param id Location ID.
     * @return A Responses object containing the Location if found, or an error message.
     */
    public static Responses getLocationById(String id) {
        return locationService.getLocationById(id);
    }
}
