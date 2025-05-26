/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.service;

import airport.controller.interfaces.IPlaneService;
import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.controller.validation.PlaneValidator;
import airport.models.Plane;
import airport.models.database.Storage_Plane;
import airport.models.database.interfaces.IPlaneStorage;
import java.util.List;

/**
 *
 * @author Jose
 */
public class PlaneService implements IPlaneService {

    private IPlaneStorage planeStorage;

    public PlaneService() {
        this.planeStorage = Storage_Plane.getInstance();
    }

    public PlaneService(IPlaneStorage planeStorage) {
        this.planeStorage = planeStorage;
    }

    @Override
    public Responses createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        Responses validationResponse = PlaneValidator.validateCreate(id, brand, model, maxCapacity, airline);
        if (validationResponse != null) {
            return validationResponse;
        }

        try {
            if (planeStorage.getPlane(id) != null) {
                return new Responses("Plane with this ID already exists", Status.BAD_REQUEST);
            }

            int maxCap = Integer.parseInt(maxCapacity);
            if (maxCap <= 0) {
                return new Responses("Max capacity must be a positive number.", Status.BAD_REQUEST);
            }

            Plane plane = new Plane(id, brand, model, maxCap, airline);

            boolean added = planeStorage.addPlane(plane);
            if (!added) {
                return new Responses("Failed to add plane, possibly duplicate ID.", Status.BAD_REQUEST);
            }

            return new Responses("Plane has been created successfully", Status.CREATED);

        } catch (NumberFormatException e) {
            return new Responses("Max Capacity must be a valid number.", Status.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Responses("An unexpected error occurred during plane creation.", Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Responses getAllPlanes() {
        try {
            List<Plane> planes = planeStorage.getAllPlanes();
            if (planes.isEmpty()) {
                return new Responses("No planes found.", Status.NOT_FOUND);
            }
            return new Responses("Planes retrieved successfully", Status.OK, planes);
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses("An unexpected error occurred while retrieving all planes.", Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Responses getPlaneById(String id) { // Implement the method
        try {
            Plane plane = planeStorage.getPlane(id);
            if (plane == null) {
                return new Responses("Plane with ID " + id + " not found.", Status.NOT_FOUND);
            }
            return new Responses("Plane retrieved successfully.", Status.OK, plane.clone()); // Return a clone
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses("An unexpected error occurred while retrieving the plane by ID.", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
