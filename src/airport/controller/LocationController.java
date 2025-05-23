/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.models.Location;
import airport.models.database.Storage_Location;

/**
 *
 * @author Jose
 */
public class LocationController {

    public static Responses createLocation(String id, String name, String city, String country, String latitude, String longitude) {
        try {

            if (id == null || !id.matches("^[A-Z]{3}$")) {
                return new Responses("ID must have exactly 3 uppercase letters.", Status.BAD_REQUEST);
            }

            Storage_Location storage = Storage_Location.getInstance();

            if (storage.getLocation(id) != null) { 
                return new Responses("Location with this ID already exists", Status.BAD_REQUEST);
            }

            if (name == null || name.trim().isEmpty()) {
                return new Responses("Airport name must be not empty", Status.BAD_REQUEST);
            }

            if (city == null || city.trim().isEmpty()) {
                return new Responses("Airport city must be not empty", Status.BAD_REQUEST);
            }

            if (country == null || country.trim().isEmpty()) {
                return new Responses("Airport country must be not empty", Status.BAD_REQUEST);
            }

            if (latitude == null || latitude.trim().isEmpty()) {
                return new Responses("Latitude must not be empty.", Status.BAD_REQUEST);
            }

            //Por si se coloca latitud en decimal reconozca el punto y coma, ya que depende del pais
            latitude = latitude.trim().replace(",", ".");

            //Para que recenozca si hay letras, y el - para que el numero sea negativo
            if (!latitude.matches("-?\\d+(\\.\\d{1,4})?")) {
                return new Responses("Latitude must be a valid number with up to 4 decimal places.", Status.BAD_REQUEST);
            }

            double lat = Double.parseDouble(latitude);

            if (lat < -90 || lat > 90) {
                return new Responses("Latitude must be in the range of -90 to 90.", Status.BAD_REQUEST);
            }

            if (longitude == null || longitude.trim().isEmpty()) {
                return new Responses("Longitude must not be empty.", Status.BAD_REQUEST);
            }

            longitude = longitude.trim().replace(",", ".");

            if (!longitude.matches("-?\\d+(\\.\\d{1,4})?")) {
                return new Responses("Longitude must be a valid number with up to 4 decimal places.", Status.BAD_REQUEST);
            }

            double lon = Double.parseDouble(longitude);

            if (lon < -180 || lon > 180) {
                return new Responses("Longitude must be in the range of -180 to 180", Status.BAD_REQUEST);
            }

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
