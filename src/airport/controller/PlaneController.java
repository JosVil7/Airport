/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.models.Plane;
import airport.models.database.Storage_Plane;

/**
 *
 * @author Jose
 */
public class PlaneController {

    public static Responses createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        try {

            if (id == null || !id.matches("^[A-Z]{2}\\d{5}$")) {
                return new Responses("ID must have exactly 2 uppercase letters followed by 5 digits", Status.BAD_REQUEST);
            }

            Storage_Plane storage = Storage_Plane.getInstance();

            if (storage.getPlane(id) != null) {
                return new Responses("Plane with this ID already exists", Status.BAD_REQUEST);
            }

            if (brand == null || brand.trim().isEmpty()) {
                return new Responses("Brand must be not empty", Status.BAD_REQUEST);
            }

            if (model == null || model.trim().isEmpty()) {
                return new Responses("Model must be not empty", Status.BAD_REQUEST);
            }

            if(maxCapacity == null || maxCapacity.trim().isEmpty()){
                return new Responses("Max capacity must be not empty.", Status.BAD_REQUEST);
            }

            if (!maxCapacity.matches("\\d+")) {
                return new Responses("Max capacity must contain only digits.", Status.BAD_REQUEST);
            }

            if (airline == null || airline.trim().isEmpty()) {
                return new Responses("Airline must be not empty", Status.BAD_REQUEST);
            }

            int max = Integer.parseInt(maxCapacity);

            Plane plane = new Plane(id, brand, model, max, airline);

            boolean added = storage.addPlane(plane);
            if (!added) {
                return new Responses("Plane with this ID already exists", Status.BAD_REQUEST);
            }

            return new Responses("Plane has been created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Responses("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
