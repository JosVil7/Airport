/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.service.LocationService;
import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.models.Location;
import airport.models.database.Storage_Location;

/**
 *
 * @author Jose
 */

/*
    Con esta forma en la que se organizan los controladores permite la opcion de open source, ya que en esta clase de Controller se podrian agregar nuevas 
    clases y paquetes para no cambiar el codigo que ya funciona. Ademas no se puede implementar de forma directa todo SOLID por falta de recursos como interfaces
    para todo tipo
    */
public class LocationController {

    public static Responses createLocation(String id, String name, String city, String country, String latitude, String longitude) {
        return LocationService.createLocation(id, name, city, country, latitude, longitude);
    }
}
