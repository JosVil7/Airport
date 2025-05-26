/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.interfaces;

import airport.controller.utils.Responses;

/**
 *
 * @author Jose
 */
public interface IPlaneService {

    Responses createPlane(String id, String brand, String model, String maxCapacity, String airline);
    Responses getAllPlanes();
     Responses getPlaneById(String id); 

}
