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
    public boolean addLocation(Location location);
    public Location getLocation(String A_id);
    public boolean delLocation(String A_id); 
    public void cargarJSON(JSONArray array); 
    public List<Location> getAllLocations();
}
