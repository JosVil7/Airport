/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package airport.models.database.interfaces;

import airport.models.Plane;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author USER
 */
public interface IPlaneStorage {
    List<Plane> getPlanes();
    void cargarJSON(JSONArray array);
    boolean delPlane(String id);
    Plane getPlane(String id);
    boolean addPlane(Plane plane);
    List<Plane> getAllPlanes();
}
