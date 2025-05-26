/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.interfaces.IPlaneService;
import airport.controller.service.PlaneService;
import airport.controller.utils.Responses;

/**
 *
 * @author Jose
 */
public class PlaneController {

    private static IPlaneService planeService = new PlaneService(); 

    
    public static void setPlaneService(IPlaneService service) {
        planeService = service;
    }

    /**
     * Handles the creation of a new plane.
     *
     * @param id Plane ID.
     * @param brand Plane brand.
     * @param model Plane model.
     * @param maxCapacity Maximum passenger capacity.
     * @param airline Airline name.
     * @return A Responses object indicating the outcome of the operation.
     */
    public static Responses createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        return planeService.createPlane(id, brand, model, maxCapacity, airline);
    }

    /**
     * Retrieves a list of all planes.
     *
     * @return A Responses object containing a List of Planes.
     */
    public static Responses getAllPlanes() {
        return planeService.getAllPlanes();
    }
}
