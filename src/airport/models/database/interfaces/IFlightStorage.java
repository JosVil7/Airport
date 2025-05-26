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

    public boolean addFlight(Flight flight);

    public Flight getFlight(String id);

    public boolean delFlight(String id);

    public boolean updateFlight(Flight flight);

    public void cargarJSON(JSONArray array);

    public List<Flight> getAllFlights();
}
