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
    
    public static Responses validateCreate(String id, String brand, String model, String maxCapacity, String airline) {
        if (id == null || !id.matches("^[A-Z]{2}\\d{5}$")) {
            return new Responses("ID must have exactly 2 uppercase letters followed by 5 digits", Status.BAD_REQUEST);
        }
        if (brand == null || brand.trim().isEmpty()) {
            return new Responses("Brand must be not empty", Status.BAD_REQUEST);
        }
        if (model == null || model.trim().isEmpty()) {
            return new Responses("Model must be not empty", Status.BAD_REQUEST);
        }
        if (maxCapacity == null || maxCapacity.trim().isEmpty()) {
            return new Responses("Max capacity must be not empty.", Status.BAD_REQUEST);
        }
        if (!maxCapacity.matches("\\d+")) {
            return new Responses("Max capacity must contain only digits.", Status.BAD_REQUEST);
        }
        if (airline == null || airline.trim().isEmpty()) {
            return new Responses("Airline must be not empty", Status.BAD_REQUEST);
        }
        return null; 
    }
}
