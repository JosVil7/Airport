/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.interfaces;

import airport.controller.utils.Responses;

/**
 *
 * @author Jose
 */
public interface ILocationService {
    Responses createLocation(String id, String name, String city, String country, String latitude, String longitude);
    Responses getAllLocations(); 
    Responses getLocationById(String id);
}
