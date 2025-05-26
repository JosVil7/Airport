/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package airport.models.database.interfaces;

import airport.models.Location;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author USER
 */
public interface ILocationStorage {
    void cargarJSON(JSONArray array);
    Location getLocation(String locationId);
    List<Location> getLocations();
    boolean addLocation(Location location);
    boolean delLocation(String locationId);
}
