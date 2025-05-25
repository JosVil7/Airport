/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.service;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.controller.validation.PlaneValidator;
import airport.models.Plane;
import airport.models.database.Storage_Plane;

/**
 *
 * @author Jose
 */
public class PlaneService {
    public static Responses createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        Responses validationResponse = PlaneValidator.validateCreate(id, brand, model, maxCapacity, airline);
        if (validationResponse != null) return validationResponse;

        try {
            Storage_Plane storage = Storage_Plane.getInstance();

            if (storage.getPlane(id) != null) {
                return new Responses("Plane with this ID already exists", Status.BAD_REQUEST);
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
