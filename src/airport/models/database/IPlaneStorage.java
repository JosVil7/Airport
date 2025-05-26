/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import airport.models.Plane;
import java.util.List;

/**
 *
 * @author Jose
 */
public interface IPlaneStorage {
    boolean addPlane(Plane plane);
    Plane getPlane(String id);
    List<Plane> getAllPlanes();
}
