/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package airport.models.database.interfaces;

import airport.models.Flight;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author USER
 */
public interface IFlightStorage {
    List<Flight> getFlightss();
    void cargarJSON(JSONArray array);
    boolean delFlight(String id);
    Flight getFlight(String id);
    boolean addFlight(Flight flight);
}
