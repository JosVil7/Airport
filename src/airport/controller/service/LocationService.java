/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.service;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.controller.validation.LocationValidator;
import airport.models.Location;
import airport.models.database.Storage_Location;

/**
 *
 * @author Jose
 */
public class LocationService {
    public static Responses createLocation(String id, String name, String city, String country, String latitude, String longitude) {
        try {
            // Validación
            Responses validationResponse = LocationValidator.validateCreate(id, name, city, country, latitude, longitude);
            if (validationResponse != null) {
                return validationResponse;
            }

            Storage_Location storage = Storage_Location.getInstance();

            // Verificar si ya existe
            if (storage.getLocation(id) != null) {
                return new Responses("Location with this ID already exists", Status.BAD_REQUEST);
            }

            // Parsear lat y lon
            double lat = Double.parseDouble(latitude.trim().replace(",", "."));
            double lon = Double.parseDouble(longitude.trim().replace(",", "."));

            Location location = new Location(id, name, city, country, lat, lon);

            boolean added = storage.addLocation(location);
            if (!added) {
                return new Responses("Location with this ID already exists", Status.BAD_REQUEST);
            }

            return new Responses("Location has been created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Responses("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
