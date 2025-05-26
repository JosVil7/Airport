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
public class PlaneValidator {
    
    /**
     * Validates all input fields for plane creation.
     * @param id Plane ID string.
     * @param brand Plane brand string.
     * @param model Plane model string.
     * @param maxCapacity Maximum passenger capacity string.
     * @param airline Airline name string.
     * @return A Responses object with BAD_REQUEST status if validation fails, otherwise null.
     */
    public static Responses validateCreate(String id, String brand, String model, String maxCapacity, String airline) {
        
        
        if (id == null || id.trim().isEmpty()) {
            return new Responses("Plane ID must not be empty.", Status.BAD_REQUEST);
        }
        if (!id.matches("^[A-Z]{2}\\d{5}$")) {
            return new Responses("Plane ID must have exactly 2 uppercase letters followed by 5 digits (e.g., AB12345).", Status.BAD_REQUEST);
        }

        
        if (brand == null || brand.trim().isEmpty()) {
            return new Responses("Brand must not be empty.", Status.BAD_REQUEST);
        }

        
        if (model == null || model.trim().isEmpty()) {
            return new Responses("Model must not be empty.", Status.BAD_REQUEST);
        }
        
        if (maxCapacity == null || maxCapacity.trim().isEmpty()) {
            return new Responses("Max capacity must not be empty.", Status.BAD_REQUEST);
        }
        if (!maxCapacity.matches("\\d+")) {
            return new Responses("Max capacity must contain only digits.", Status.BAD_REQUEST);
        }
        try {
            int capacity = Integer.parseInt(maxCapacity);
            if (capacity <= 0) { // Capacity must be positive
                return new Responses("Max capacity must be a positive number.", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Responses("Invalid Max Capacity format. Must be a valid number.", Status.BAD_REQUEST);
        }
        
        if (airline == null || airline.trim().isEmpty()) {
            return new Responses("Airline must not be empty.", Status.BAD_REQUEST);
        }

        return null; // Todo bien :D
    }
}
