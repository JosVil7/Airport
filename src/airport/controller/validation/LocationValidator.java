/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.validation;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;

/**
 *
 * @author Jose
 */
public class LocationValidator {

    
    public static Responses validateCreate(String id, String name, String city, String country, String latitude, String longitude) {
        
        if (id == null || id.trim().isEmpty()) {
            return new Responses("Airport ID must not be empty.", Status.BAD_REQUEST);
        }
        if (!id.matches("^[A-Z]{3}$")) {
            return new Responses("Airport ID must have exactly 3 uppercase letters.", Status.BAD_REQUEST);
        }

        
        if (name == null || name.trim().isEmpty()) {
            return new Responses("Airport name must not be empty.", Status.BAD_REQUEST);
        }

        
        if (city == null || city.trim().isEmpty()) {
            return new Responses("Airport city must not be empty.", Status.BAD_REQUEST);
        }

        
        if (country == null || country.trim().isEmpty()) {
            return new Responses("Airport country must not be empty.", Status.BAD_REQUEST);
        }

        
        if (latitude == null || latitude.trim().isEmpty()) {
            return new Responses("Latitude must not be empty.", Status.BAD_REQUEST);
        }
        
        latitude = latitude.trim().replace(",", ".");
        if (!latitude.matches("-?\\d+(\\.\\d{1,4})?")) {
            return new Responses("Latitude must be a valid number with up to 4 decimal places.", Status.BAD_REQUEST);
        }
        try {
            double lat = Double.parseDouble(latitude);
            if (lat < -90 || lat > 90) {
                return new Responses("Latitude must be in the range of -90 to 90.", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Responses("Invalid Latitude format. Must be a valid number.", Status.BAD_REQUEST);
        }

        
        if (longitude == null || longitude.trim().isEmpty()) {
            return new Responses("Longitude must not be empty.", Status.BAD_REQUEST);
        }
        
        longitude = longitude.trim().replace(",", ".");
        if (!longitude.matches("-?\\d+(\\.\\d{1,4})?")) {
            return new Responses("Longitude must be a valid number with up to 4 decimal places.", Status.BAD_REQUEST);
        }
        try {
            double lon = Double.parseDouble(longitude);
            if (lon < -180 || lon > 180) {
                return new Responses("Longitude must be in the range of -180 to 180.", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            // Should be caught by regex, but for robustness
            return new Responses("Invalid Longitude format. Must be a valid number.", Status.BAD_REQUEST);
        }

        return null; 
    }
}
