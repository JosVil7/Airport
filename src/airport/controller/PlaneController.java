/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.service.PlaneService;
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
        return PlaneService.createPlane(id, brand, model, maxCapacity, airline);
    }
}
