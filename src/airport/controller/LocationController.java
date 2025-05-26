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

    private static ILocationService locationService = new LocationService(); 

    
    public static void setLocationService(ILocationService service) {
        locationService = service;
    }

    
    public static Responses createLocation(String id, String name, String city, String country, String latitude, String longitude) {
        return locationService.createLocation(id, name, city, country, latitude, longitude);
    }

    
    public static Responses getAllLocations() {
        return locationService.getAllLocations();
    }

    
    public static Responses getLocationById(String id) {
        return locationService.getLocationById(id);
    }
}
